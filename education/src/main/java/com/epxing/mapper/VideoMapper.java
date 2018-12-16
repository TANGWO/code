package com.epxing.mapper;

import org.apache.ibatis.annotations.Select;

import com.epxing.entity.FM_BLOB;

public interface VideoMapper {
    
	@Select("select * from FM_BLOB where ID =#{0}")
	FM_BLOB getPathById(String videoId);

}
