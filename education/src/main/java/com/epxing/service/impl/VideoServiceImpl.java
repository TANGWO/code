package com.epxing.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.epxing.entity.FM_BLOB;
import com.epxing.mapper.VideoMapper;
import com.epxing.service.VideoService;


@Service
public class VideoServiceImpl  implements VideoService{

	@Resource
	private VideoMapper videoMapper;

	@Override
	public String getPathById(String videoId) {
		FM_BLOB blob = videoMapper.getPathById(videoId);
		if(null !=blob){
			return blob.getFILE_PATH_();
		}else{
			return null;
		}
	}
	
	
}
