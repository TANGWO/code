package com.epxing.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.epxing.entity.WebUser;

public interface UserMapper {
	
	 /**
	  * 用户登录（用户名，密码）
	  */
	 @Select("select * from WebUser where account=#{ACCOUNT} and password=#{PASSWORD}")
	 WebUser findByAccountAndPassword(@Param("ACCOUNT")String ACCOUNT, @Param("PASSWORD")String PASSWORD) ;
	 
	 
	 /**
	  * 用户注册
	  * @param user
	  * @return
	  */
	 
	 @Insert("insert into WebUser (wuid,account,password) values(VERSIONING_PM_SEQ_NAMES_ID.Nextval,#{0},#{1})")
	 int insertUser(String account ,String password);
	 
	 
	 /**
	  * 用户注册账户验证
	  */
	 @Select("select count(*) from WebUser where account=#{0} ")
	 int findByAccount(String account);
	 
	 
	 
	 /**
	  * 用户查询
	  */
	 @Select("select * from WebUser where account=#{0} ")
	 WebUser queryByAccount(String account);
	 
	 
	 /**
	  * 找回密码（直接找回）
	  */
	 int updateAccountAndPassword(@Param("realName")String realName, @Param("password")String password, @Param("idCard")String idCard);

    /**
     * 完善信息
     * @param user
     * @return
     */
	int updateInfo(@Param("user")WebUser user );
	
	/**
	 * 查询修好的次数（每天最多能修改5次）
	 */
	 @Select("select count(*) from UpdatePasswordRecord where web_user=#{0} and update_date=#{1} ")
	 int  updatePasswordTimes(String userId,Date date);
}
