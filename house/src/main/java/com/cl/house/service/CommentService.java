 package com.cl.house.service;

import java.util.List;

import com.cl.house.common.model.Comment;


/**
 * @author chenling
 * @date 2018/12/17
 */
public interface CommentService {

	/**
	 * @param id
	 * @param i
	 * @return
	 */
	List<Comment> getHouseComments(Long id, int i);

	/**
	 * @param houseId
	 * @param content
	 * @param userId
	 */
	void addHouseComment(Long houseId, String content, Long userId);

	/**
	 * @param blogId
	 * @param content
	 * @param userId
	 */
	void addBlogComment(Integer blogId, String content, Long userId);

	/**
	 * @param id
	 * @param i
	 * @return
	 */
	List<Comment> getBlogComments(long blogId, int size);



}
