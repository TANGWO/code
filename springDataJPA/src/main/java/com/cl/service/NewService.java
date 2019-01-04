 package com.cl.service;

import com.cl.entity.News;

/**
 * @author chenling
 * @date 2019/01/02
 */
public interface NewService {

	/**
	 * @param news
	 * @return
	 */
	String saveNews(News news);

	/**
	 * @param id
	 */
	String updateById(Long id,News news);

	/**
	 * @param news
	 * @return
	 */
	String updateByTitle(News news);

	/**
	 * @param id
	 * @param news
	 * @return
	 */
	String updateByIdForOne(Long id, News news);

	/**
	 * @param id
	 * @param context
	 * @return
	 */
	String updateByValue(Long id, String context);

	/**
	 * @param id
	 * @return
	 */
	String deleteNews(Long []  id);

}
