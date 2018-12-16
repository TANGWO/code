package com.itmayiedu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itmayiedu.model.User;

@Mapper
public interface UserMapper {
	
	@Select("select* from user where id = #{id}")
	User findByName(@Param("id") int id);
	
	@Insert("insert into user(id,userName,passWord,realName) values(#{id},#{userName},#{passWord},#{realName})")
	int insert(@Param("id") int id,@Param("userName") String userName,@Param("passWord") String passWord,@Param("realName") String realName);

}
