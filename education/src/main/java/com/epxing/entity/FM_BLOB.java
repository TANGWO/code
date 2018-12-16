package com.epxing.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oracle.sql.BLOB;

/**
 * 附件
 * @author chenl
 *
 */
@Setter
@Getter
@ToString
public class FM_BLOB {
	
  private   String   ID;
  private   String   FILE_NAME;
  private   String   FILE_TYPE;
  private   Integer  FILE_SIZE;
  private   String   ENTITY_ID;
  private   byte[]   FILE_DATA;
  private   String   USER_ID;
  private   String   FILE_CODE_;
  private   String   FILE_PATH_;
  private   Integer  OLD_ID_;
}
