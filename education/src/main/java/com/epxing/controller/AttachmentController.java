package com.epxing.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.epxing.entity.FM_BLOB;
import com.epxing.service.AttachmentService;
import com.epxing.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentServiceImpl;
	/**
     * 单个文件上传
     * @param request
     * @return
     */
    @RequestMapping(value="upload",produces="text/html;charset=utf-8")
    @ResponseBody
    private String upload(@RequestParam("file")CommonsMultipartFile partFile,HttpServletRequest request) {
        try {
            String path = request.getServletContext().getRealPath("/upload");
            String name = request.getParameter("name");
            log.info("其他的参数{}",name);
            log.info("upload2---------------start---------------------");
            log.info("这个临时文件的路径是[{}]", path);
            String filename = partFile.getOriginalFilename();
            log.info("文件的名字：{}",filename);
            File file = new File(path+"/"+filename);
            InputStream inputStream = partFile.getInputStream();
            FileUtils.copyInputStreamToFile(inputStream, file);
            if(inputStream!=null){
                inputStream.close();
            }
            return "文件上传成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败！";
        } 
    }
	
    /**
     * 文件下载
     * 单个文件下载
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping("down")
    @ResponseBody
    private void down(HttpServletRequest request,HttpServletResponse response,String id) throws IOException {
    	FM_BLOB blob = attachmentServiceImpl.findAttachmentById(id);
    	if(StringUtil.isNull(blob.getFILE_PATH_())){
    		String name = blob.getFILE_NAME();
    		byte[] data = blob.getFILE_DATA();
    		response.setHeader("content-disposition", "attachment;filename="+name);
 	        response.setContentType("application/octet-stream");
    		ServletOutputStream outputStream = response.getOutputStream();
    		outputStream.write(data);
    		outputStream.flush();
    		outputStream.close();
    	}else{
	    	File file = new File(blob.getFILE_PATH_());
	        String name = blob.getFILE_NAME();
	        System.out.println("文件的名字："+name);
	        response.setHeader("content-disposition", "attachment;filename="+name);
	        response.setContentType("application/octet-stream");
	        FileUtils.copyFile(file, response.getOutputStream());
    	}
    }
    
    /**
     * 多个文件上载
     * @param request
     * @return
     */
    @RequestMapping(value="uploads",produces="text/html;charset=utf-8")
    @ResponseBody
    private String uploads(@RequestParam("file")CommonsMultipartFile[] partFiles,HttpServletRequest request) {
        InputStream inputStream = null;    
        try {
            String path = request.getServletContext().getRealPath("/upload");
            String name = request.getParameter("name");
            log.info("其他的参数{}",name);
            log.info("upload2---------------start---------------------");
            log.info("这个临时文件的路径是[{}]", path);
            for (int i = 0; i < partFiles.length; i++) {
                String filename = partFiles[i].getOriginalFilename();
                log.info("文件的名字：{}",filename);
                File file = new File(path+"/"+filename);
                inputStream = partFiles[i].getInputStream();
                FileUtils.copyInputStreamToFile(inputStream, file);
            }
            if(inputStream!=null){
                inputStream.close();
            }
            return "文件上传成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败！";
        } 
    }
    
    /**
     * 文件下载，一下次下载多个文件
     * 思路：先将多个文件压缩到一个压缩包里去，然后传到前台
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping("downs")
    private void downs(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String path = request.getServletContext().getRealPath("/upload");
        File file = new File(path);
        File[] files = file.listFiles();
        File zipFile =new File("test.zip");
        if(!zipFile.exists()){
            zipFile.createNewFile();
        }
        String zipName = zipFile.getName();
        log.info("压缩文件的名字：{}",zipName);
        response.addHeader("Content-Disposition", "attachment;filename="+zipName);
        //定义输出类型
//        response.setContentType("application/zip");
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(zipFile));
        BufferedInputStream in  =null;
        log.info("文件的个数{}",files.length);
        for(int i = 0;i<files.length;i++){
            String name = files[i].getName();
            System.out.println("文件的名字："+name);
            ZipEntry zipEntry = new ZipEntry(name);
            zip.putNextEntry(zipEntry);
            in = new BufferedInputStream(new FileInputStream(files[i]));
            int len = 0;
            byte [] btyes = new byte[1024*4];
            while((len=in.read(btyes))!=-1){
                zip.write(btyes, 0, len);
            }
        }
        zip.flush();
        zip.close();
        in.close();
        FileUtils.copyFile(zipFile, response.getOutputStream());
        if(zipFile.exists()){
            if(zipFile.delete()){
                log.info("压缩包删成功！！");
            }else{
                log.info("压缩包产出失败！！");
            }
            
        }
    } 
}
