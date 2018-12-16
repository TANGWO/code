package com.epxing.service;

import com.epxing.entity.FM_BLOB;

public interface AttachmentService {
	
	//通过ID查询附件
	FM_BLOB findAttachmentById(String id);
}
