package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.GoodsReceiptInfo;

public interface IGoodsReceiptInfoDao extends JpaRepository<GoodsReceiptInfo, Long> {

}