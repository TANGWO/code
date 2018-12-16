package com.epxing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.epxing.entity.FM_BLOB;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebsitePicture;

public interface AttachmentMapper {
	
	
	@Select("select * from FM_BLOB where ID =#{0}")
	FM_BLOB findAttachmentById(String id);
	
	@Select("select count(*) from WebsitePicture")
    int getPhotoCount();
	
	List<WebsitePicture> getPhotoList(@Param("p")Pagination<WebsitePicture> p);

}
