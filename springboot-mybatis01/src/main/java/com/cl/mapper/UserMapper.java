package com.cl.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cl.entity.FM_USER;

public interface UserMapper {
	
	@Select("select * from FM_USER where ID_=#{id}")
	FM_USER findUserById(@Param("id") int id);

}
