 package com.cl.house.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cl.house.common.model.Comment;
import com.cl.house.common.model.Community;
import com.cl.house.common.model.House;
import com.cl.house.common.model.UserMsg;
import com.cl.house.common.page.PageParams;

/**
 * @author chenling
 * @date 2018/12/17
 */
public interface CommentMapper {
	

	  List<House> selectHouse(@Param("house") House house, @Param("pageParams")  PageParams pageParams);

	  Long selectHouseCount(@Param("house")House house);

	  List<Community> selectCommunity(Community community);

	  int insertUserMsg(UserMsg userMsg);

	  int updateHouse(House house);

	  int insert(Comment comment);

	  List<Comment> selectComments(@Param("houseId")long houseId, @Param("size")int size);

	  List<Comment> selectBlogComments(@Param("blogId")long blogId, @Param("size")int size);
	
	
}
