package com.cl.house.common.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class House {
		
	  private Long id;
	  private Integer type;
	  private Integer price;
	  private String  name;
	  private String images;
	  private Integer area;
	  private Integer beds;
	  private Integer baths;
	  private Double  rating;
	  
	  private Integer roundRating = 0;
	  private String  remarks;
	  private String  properties;
	  private String  floorPlan;
	  private String  tags;
	  private Date    createTime;
	  private Integer cityId;
	  private Integer    communityId;
	  private String     address;
	  
	  private String     firstImg;
	  
	  private List<String> imageList = Lists.newArrayList();
	  
	  private List<String> floorPlanList = Lists.newArrayList();
	  private List<String> featureList   = Lists.newArrayList();
	  
	  private List<MultipartFile> houseFiles;
	  
	  private List<MultipartFile> floorPlanFiles;
	  
	  
	  private String priceStr;
	  
	  private String typeStr;
	  
	  
	  private Long userId;
	  
	  private boolean bookmarked;
	  
	  private Integer state;
	  
	  private List<Long> ids;
	  
	  private String  sort = "time_desc";//price_desc,price_asc,time_desc
	  
	  
	  public void setType(Integer type) {
		    this.type = type;
		    if (Objects.equal(type, 1) && Objects.equal(type, 0)) {
		      this.typeStr = "For Sale";
		    }else {
		      this.typeStr = "For Rent";
		    }
		}
	  
	  
	  public void setProperties(String properties) {
		    this.properties = properties;
		    this.featureList = Splitter.on(",").splitToList(properties);
		  }
	  
	  
	  public void setFloorPlan(String floorPlan) {
	    this.floorPlan = floorPlan;
	    if (!Strings.isNullOrEmpty(floorPlan)) {
	      this.floorPlanList = Splitter.on(",").splitToList(floorPlan);
	    }
	  }
	  
  
	  public void setFeatureList(List<String> featureList) {
		    this.featureList = featureList;
		    this.properties = Joiner.on(",").join(featureList);
	   }
	  
	  
	  public void setImages(String images) {
		    this.images = images;
		    if (!Strings.isNullOrEmpty(images)) {
		       List<String> list =  Splitter.on(",").splitToList(images);
		       if (list.size() > 0) {
		          this.firstImg = list.get(0);
		          this.imageList = list;
		       }
		    }
	 }
	  
    public void setRating(Double rating) {
	    this.rating = rating;
	    this.roundRating = (int) Math.round(rating);
	  }
	  
}
