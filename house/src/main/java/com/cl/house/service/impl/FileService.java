package com.cl.house.service.impl;


import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

@Service
public class FileService {
	
	@Value("${file.path}")
	private String filePath;
	
	
	/**
	  * 获取文件保存后的路径
	 * @param files
	 * @return
	 */
	public List	<String> getImgPath(List<MultipartFile> files){
		List <String> paths = Lists.newArrayList();
		files.forEach(file ->{
			File localFile= null;
			if(!file.isEmpty()) {
				try {
					localFile = saveToLocal(file);
					String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
					paths.add(path);
				}catch (Exception e) {
					throw new IllegalArgumentException();
				}
			}
		});
		
		return paths;
	}

	
	/**
	  * 保存文件到服务器指定路径
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private File saveToLocal(MultipartFile file) throws IOException {
		
		File  newFile = new  File(filePath+"/"+Instant.now().getEpochSecond()+"/"+file.getOriginalFilename());
		if(!newFile.exists()) {
			newFile.getParentFile().mkdirs();
			newFile.createNewFile();
		}
		Files.write(file.getBytes(),newFile);
		return newFile;
	}
}
