package com.epxing.controller;

import static java.nio.file.StandardOpenOption.READ;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.epxing.service.VideoService;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class VideoController {
	
	
	  private static final long serialVersionUID = 1L;
	  private static final int BUFFER_LENGTH = 1024 * 200;
	  private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
	  private static final Pattern RANGE_PATTERN = Pattern.compile("bytes=(?<start>\\d*)-(?<end>\\d*)");
	  private String videoPath="";
	  
	  @Autowired
	  private VideoService videoServiceImpl;
	  
	  @RequestMapping(value="getVideo",method=RequestMethod.GET)
	  private void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		  String ID = request.getParameter("id");
		  System.out.println("********视频ID************"+ID);
		  String path = videoServiceImpl.getPathById(ID);
		  if(null !=path){
			    String videoFilename = URLDecoder.decode(path, "UTF-8");
			    Path video = Paths.get(videoPath, videoFilename);
			    int length = (int) Files.size(video);
			    int start = 0;
			    int end = length - 1;
			   
			    String range = request.getHeader("Range");
			    range=range==null?"":range;
			    Matcher matcher = RANGE_PATTERN.matcher(range);

			    if (matcher.matches()) {
			      String startGroup = matcher.group("start");
			      start = startGroup.isEmpty() ? start : Integer.valueOf(startGroup);
			      start = start < 0 ? 0 : start;

			      String endGroup = matcher.group("end");
			      end = endGroup.isEmpty() ? end : Integer.valueOf(endGroup);
			      end = end > length - 1 ? length - 1 : end;
			    }

			    int contentLength = end - start + 1;
			    response.setBufferSize(BUFFER_LENGTH);
			    response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", videoFilename));
			    response.setHeader("Accept-Ranges", "bytes");
			    response.setHeader("Connection", "Keep-Alive");
			    response.setDateHeader("Last-Modified", Files.getLastModifiedTime(video).toMillis());
			    response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
			    response.setContentType(Files.probeContentType(video));
			    response.setHeader("Content-Length", String.format("%s", contentLength));
			    response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
			    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
			    int bytesRead;
			    int bytesLeft = contentLength;
			    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);
			    
			    try (
		    		   SeekableByteChannel input = Files.newByteChannel(video, READ);
			    	   OutputStream output = response.getOutputStream();
		           ){
			      input.position(start);
			      while ((bytesRead = input.read(buffer)) != -1 && bytesLeft > 0) {
			        buffer.clear();
			        output.write(buffer.array(), 0, bytesLeft < bytesRead ? bytesLeft : bytesRead);
			        bytesLeft -= bytesRead;
			      }
			      output.close();
			      input.close();
			    }catch (Exception e) {
			    	System.out.println("************传输视频时异常*********");
			    	e.printStackTrace();
				}
		
		  }
	}
	  	
	  
	  
	  /**
	     * 大文件分块下载
	     * 
	     * @param request
	     * @param response
	     * @throws IOException
	     */
	    @RequestMapping("videoFile")
	    public void bigFileDownload(HttpServletRequest request,HttpServletResponse response) throws IOException {
	       
	    	String uri = URLDecoder.decode(request.getRequestURI(), "UTF-8");
	        String ID = request.getParameter("id");
			System.out.println("********视频ID************"+ID);
			String path = videoServiceImpl.getPathById(ID);
			String filename = path.substring(path.lastIndexOf(".")+1, path.length());
	        File downloadFile = new File(path); // 要下载的文件
	        long fileLength = downloadFile.length();// 记录文件大小
	        long pastLength = 0;// 记录已下载文件大小
	        long toLength = 0;// 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
	        long contentLength = 0;// 客户端请求的字节总量
	        String rangeBytes = "";// 记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容

	        // ETag header
	        // The ETag is contentLength + lastModified
	        response.setHeader("ETag","W/\"" + fileLength + "-" + downloadFile.lastModified() + "\"");
	        // Last-Modified header
	        response.setHeader("Last-Modified",new Date(downloadFile.lastModified()).toString());

	        if (request.getHeader("Range") != null) {
	        	// 客户端请求的下载的文件块的开始字节
	        	response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
	            log.info("request.getHeader(\"Range\")="+ request.getHeader("Range"));
	            rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
	            if (rangeBytes.indexOf('-') == rangeBytes.length() - 1) {// bytes=969998336-
	                rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
	                pastLength = Long.parseLong(rangeBytes.trim());
	                toLength = fileLength - 1;
	            } else {
	            	// bytes=1275856879-1275877358
	                String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
	                String temp2 = rangeBytes.substring( rangeBytes.indexOf('-') + 1, rangeBytes.length());
	                // bytes=1275856879-1275877358，从第 1275856879个字节开始下载
	                pastLength = Long.parseLong(temp0.trim());
	                // bytes=1275856879-1275877358，到第 1275877358 个字节结束
	                toLength = Long.parseLong(temp2);
	            }
	        } else {// 从开始进行下载
	            toLength = fileLength - 1;
	        }
	        // 客户端请求的是1275856879-1275877358 之间的字节
	        contentLength = toLength - pastLength + 1;
	        if (contentLength < Integer.MAX_VALUE) {
	            response.setContentLength((int) contentLength);
	        } else {
	            // Set the content-length as String to be able to use a long
	            response.setHeader("content-length", "" + contentLength);
	        }
	        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	        ServletContext servletContext = webApplicationContext.getServletContext();
	        String contentType = servletContext.getMimeType(filename);
	        if (null != contentType) {
	            response.setContentType(contentType);
	        }

	        // 告诉客户端允许断点续传多线程连接下载,响应的格式是:Accept-Ranges: bytes
	        response.setHeader("Accept-Ranges", "bytes");
	        // 必须先设置content-length再设置header
	        response.addHeader("Content-Range", "bytes " + pastLength + "-" + toLength + "/" + fileLength);

	        response.setBufferSize(2048);

	        InputStream istream = null;
	        OutputStream os = null;
	        try {
	            os = response.getOutputStream();
	            istream = new BufferedInputStream(
	                    new FileInputStream(downloadFile), 2048);
	            try {
	                copyRange(istream, os, pastLength, toLength);
	            } catch (IOException ie) {
	                /**
	                 * 在写数据的时候， 对于 ClientAbortException 之类的异常，
	                 * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。
	                 * 尤其是对于迅雷这种吸血的客户端软件， 明明已经有一个线程在读取 bytes=1275856879-1275877358，
	                 * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段， 直到有一个线程读取完毕，迅雷会 KILL
	                 * 掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
	                 * 所以，我们忽略这种异常
	                 */
	            }
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	        } finally {
	            if (istream != null) {
	                try {
	                    istream.close();
	                } catch (IOException e) {
	                    log.error(e.getMessage(), e);
	                }
	            }
	        }
	    }

	    protected void copyRange(InputStream istream, OutputStream ostream,
	            long start, long end) throws IOException {

	        long skipped = 0;
	        skipped = istream.skip(start);

	        if (skipped < start) {
	            throw new IOException("skip fail: skipped=" + Long.valueOf(skipped)
	                    + ", start=" + Long.valueOf(start));
	        }

	        long bytesToRead = end - start + 1;

	        byte buffer[] = new byte[2048];
	        int len = buffer.length;
	        while ((bytesToRead > 0) && (len >= buffer.length)) {
	            try {
	                len = istream.read(buffer);
	                if (bytesToRead >= len) {
	                    ostream.write(buffer, 0, len);
	                    bytesToRead -= len;
	                } else {
	                    ostream.write(buffer, 0, (int) bytesToRead);
	                    bytesToRead = 0;
	                }
	            } catch (IOException e) {
	                len = -1;
	                throw e;
	            }
	            if (len < buffer.length)
	                break;
	        }

	    }
    
	  

}
