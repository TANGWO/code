package com.cl.source02.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cl.entity.FM_USER;

public interface User2Mapper {
	
	@Select("select * from FM_USER where ID_=#{id}")
	FM_USER findUserById(@Param("id") int id);

}
