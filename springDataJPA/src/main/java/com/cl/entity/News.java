 package com.cl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author chenling
 * @date 2019/01/02
 */
@Data
@Entity(name="news")
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String title;
	
	@Column
	private Date creatTime;
	
	@Column
	private String source;
	
	@Column
	private String context;
	
	@Column
	private String autor;
	
	@Column
	private int isPublish;
	

	
	
	
}
