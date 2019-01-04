package com.cl.logistics.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cl.logistics.bean.BillInfo;
import com.cl.logistics.bean.BillRelease;
import com.cl.logistics.bean.GoodsReceiptInfo;

public interface IBillService {
	
	public Page<BillInfo> findAllByPage(Pageable pageable);
	
	public Page<BillInfo> findNotRelease(Pageable pageable);
	
	public boolean addRelease(BillRelease billRelease);
	
	public boolean addGoodsReceipt(GoodsReceiptInfo goodsReceiptInfo);

}
