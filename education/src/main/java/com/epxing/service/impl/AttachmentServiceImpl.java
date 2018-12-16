package com.epxing.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.epxing.entity.FM_BLOB;
import com.epxing.mapper.AttachmentMapper;
import com.epxing.service.AttachmentService;


@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	
	@Resource
	private AttachmentMapper attachmentMapper;
	
	@Override
	public FM_BLOB findAttachmentById(String id) {
		
		return attachmentMapper.findAttachmentById(id);
	}

}
