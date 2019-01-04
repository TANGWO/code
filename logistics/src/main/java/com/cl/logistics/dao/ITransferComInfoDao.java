package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.TransferComInfo;

public interface ITransferComInfoDao extends JpaRepository<TransferComInfo, Long> {

	public TransferComInfo findByCity(String city);

}
