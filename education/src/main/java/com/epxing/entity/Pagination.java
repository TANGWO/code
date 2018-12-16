package com.epxing.entity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Pagination<T> {
	
	private int page;//当前页
	private int size;//当前页面显示个数
	private int totalSize;//总记录数
	private int totalPage;//总页数
	private int first;//当前的所在位置
	private int last;//网页显示的截止位置
	private boolean hasPrev;//是否有上一页
	private int prevPage;//上一页
	

	private boolean hasNext;//是否有 下一页
	private int nextPage;//是否有下一页
	private int firstPage;//首页
	private int lastPage;//尾页
	private int envCount;//导航页显示的个数
	private int envBegin;//导航页的初始位置
	private int envEnd;//导航页的结束位置
	private List<T> list;
	

	public Pagination(String page,String size,int totalSize) {
		//当前页
		this.page=null != page?Integer.parseInt(page):1;
		//当前页面的显示个数
		this.size=null != size?Integer.parseInt(size):5;
		//总记录数
		this.totalSize=totalSize;
		//总页数
		this.totalPage=(int)Math.ceil(totalSize * 1.0 / this.size);
		if(this.totalPage < 1){
			this.totalPage=1;
		}
		if(this.page>this.totalPage){
			this.page=this.totalPage;
		}
		//当前的所在位置
		this.first=(this.page-1)*this.size;
		//网页显示的截止位置
		this.last = this.first+this.size;
		//是否有上一页
		if(this.page>1){
			this.hasPrev=true;
			this.prevPage=this.page-1;
		}
		if(this.page<totalPage){
			this.hasNext=true;
			this.nextPage=this.page+1;
		}
		//首页
		this.firstPage=1;
		//尾页
		this.lastPage=totalPage;
		//导航页显示的个数
		this.envCount=10;
		//导航页的初始位置
		this.envBegin=this.page-5>1?this.page-5:1;
		//导航页的结束位置
		this.envEnd=this.page+4>10?this.page+4:10;
		if(this.envEnd > this.totalPage) {
			this.envEnd = this.totalPage;
		}
	}
	
	

}
