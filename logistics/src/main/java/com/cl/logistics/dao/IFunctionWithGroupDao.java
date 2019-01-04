package com.cl.logistics.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.logistics.bean.FunctionWithGroup;

public interface IFunctionWithGroupDao extends JpaRepository<FunctionWithGroup, Long> {
	
	public List<FunctionWithGroup> findByGroupId(int groupId);
	
	public FunctionWithGroup findByFunctionIdAndGroupId(int funtionId, int groupId);

}
