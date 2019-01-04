 package com.cl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cl.entity.News;

/**
 * @author chenling
 * @date 2019/01/02
 */
//@Repository
//public interface NewsRepository extends JpaRepository<News, Long> {
public interface NewsDao extends JpaRepository<News, Long> {

	/**
	 * @param news
	 */
	@Modifying
	@Query(value="update news set context =?1 where title=?2",nativeQuery=true)
	int updateByTitle(String context,String title);

	/**
	 * @param title
	 */
	@Modifying
	@Query("select n from news n where title=?1")
	List<News> findAllNews(String title);

	/**
	 * @param id
	 * @param context
	 */
	@Modifying
	@Query(value="update news set context =?2 where id=?1",nativeQuery=true)
	int updateByValue(Long id, String context);

	

	
	
}
