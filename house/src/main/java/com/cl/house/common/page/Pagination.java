package com.cl.house.common.page;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

/**
 *  封装分页信息
 *  @author 臣不二
 *  2018年12月17日 下午6:24:01
 *
 */

@Data
public class Pagination {
		
	private int pageNum;
	private int pageSize;
	private long totalCount;
	private List<Integer> pages = Lists.newArrayList();

	public Pagination(Integer pageSize,Integer pageNum,Long totalCount) {
	   this.totalCount = totalCount;
	   this.pageNum = pageNum;
	   this.pageSize = pageSize;
	   for(int i=1;i<=pageNum;i++){
		   pages.add(i);
	   }
	   Long pageCount = totalCount/pageSize + ((totalCount % pageSize == 0 ) ? 0: 1);
	   if (pageCount > pageNum) {
		  for(int i= pageNum + 1; i<= pageCount ;i ++){
			  pages.add(i);
		  }
	   }
	}
	
	
}
