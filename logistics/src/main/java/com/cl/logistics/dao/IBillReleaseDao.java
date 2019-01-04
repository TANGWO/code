package com.cl.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.BillRelease;

public interface IBillReleaseDao extends JpaRepository<BillRelease, Long> {

}
