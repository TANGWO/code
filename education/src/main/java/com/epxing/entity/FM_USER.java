package com.epxing.entity;

import java.sql.Timestamp;


/**
 * 用户
 * @author chenl
 */
public class FM_USER {
	  private String NAME;           
	  private String PASSWORD;         
	  private String ACCOUNT;         
	  private Integer  ID_;             
	  private String EMP_ID;           
	  private  Timestamp   LAST_LOGIN_TIME_;
	  private String  DEPT_ID_ ;    
	  private String  USER_TYPE_ ;
	  private String STATUS_  ;
	  private String PASSWORD_FLAG_ ;
	  
	  
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getACCOUNT() {
		return ACCOUNT;
	}
	public void setACCOUNT(String aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}
	public Integer getID_() {
		return ID_;
	}
	public void setID_(Integer iD_) {
		ID_ = iD_;
	}
	public String getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(String eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public Timestamp getLAST_LOGIN_TIME_() {
		return LAST_LOGIN_TIME_;
	}
	public void setLAST_LOGIN_TIME_(Timestamp lAST_LOGIN_TIME_) {
		LAST_LOGIN_TIME_ = lAST_LOGIN_TIME_;
	}
	public String getDEPT_ID_() {
		return DEPT_ID_;
	}
	public void setDEPT_ID_(String dEPT_ID_) {
		DEPT_ID_ = dEPT_ID_;
	}
	public String getUSER_TYPE_() {
		return USER_TYPE_;
	}
	public void setUSER_TYPE_(String uSER_TYPE_) {
		USER_TYPE_ = uSER_TYPE_;
	}
	public String getSTATUS_() {
		return STATUS_;
	}
	public void setSTATUS_(String sTATUS_) {
		STATUS_ = sTATUS_;
	}
	public String getPASSWORD_FLAG_() {
		return PASSWORD_FLAG_;
	}
	public void setPASSWORD_FLAG_(String pASSWORD_FLAG_) {
		PASSWORD_FLAG_ = pASSWORD_FLAG_;
	}
	@Override
	public String toString() {
		return "User [NAME=" + NAME + ", PASSWORD=" + PASSWORD + ", ACCOUNT=" + ACCOUNT + ", ID_=" + ID_ + ", EMP_ID="
				+ EMP_ID + ", LAST_LOGIN_TIME_=" + LAST_LOGIN_TIME_ + ", DEPT_ID_=" + DEPT_ID_ + ", USER_TYPE_="
				+ USER_TYPE_ + ", STATUS_=" + STATUS_ + ", PASSWORD_FLAG_=" + PASSWORD_FLAG_ + "]";
	}
	public FM_USER(String nAME, String pASSWORD, String aCCOUNT, Integer iD_, String eMP_ID, Timestamp lAST_LOGIN_TIME_,
			String dEPT_ID_, String uSER_TYPE_, String sTATUS_, String pASSWORD_FLAG_) {
		super();
		NAME = nAME;
		PASSWORD = pASSWORD;
		ACCOUNT = aCCOUNT;
		ID_ = iD_;
		EMP_ID = eMP_ID;
		LAST_LOGIN_TIME_ = lAST_LOGIN_TIME_;
		DEPT_ID_ = dEPT_ID_;
		USER_TYPE_ = uSER_TYPE_;
		STATUS_ = sTATUS_;
		PASSWORD_FLAG_ = pASSWORD_FLAG_;
	}
	public FM_USER() {
		super();
	}
	
	  
	  
	
	
	
	
	
}
