package com.cl.logistics.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cl.logistics.bean.Function;
import com.cl.logistics.bean.FunctionWithGroup;
import com.cl.logistics.bean.UserGroup;
import com.cl.logistics.dao.IEmployeeDao;
import com.cl.logistics.dao.IFunctionDao;
import com.cl.logistics.dao.IFunctionWithGroupDao;
import com.cl.logistics.dao.IGroupDao;
import com.cl.logistics.dao.IUserWithGroupDao;
import com.cl.logistics.service.IGroupService;

@Service(value = "groupService")
public class GroupServiceImpl implements IGroupService {
	
	@Autowired
	private IGroupDao groupDao;
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Autowired
	private IFunctionDao functionDao;
	
	@Autowired
	private IFunctionWithGroupDao functionWithGroupDao;
	
	@Autowired
	private IUserWithGroupDao userWithGroupDao;

	@Override
	public boolean save(UserGroup userGroup) {
		// TODO Auto-generated method stub
		try {
			groupDao.save(userGroup);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("用户组表插入失败！");
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			UserGroup userGroup = groupDao.findById(id);
			groupDao.delete(userGroup);
			employeeDao.updateDepartment("临时组", userGroup.getGroupName());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("用户组表删除 | 职工部门更新 失败！");
			return false;
		}
	}

	@Override
	public Page<UserGroup> selectAllGroup(Pageable pageable) {
		// TODO Auto-generated method stub
		return groupDao.findAll(pageable);
	}

	@Override
	public UserGroup findById(int id) {
		// TODO Auto-generated method stub
		return groupDao.findById(id);
	}

	@Override
	public boolean update(int id, String description) {
		// TODO Auto-generated method stub
		UserGroup userGroup = groupDao.findById(id);
		userGroup.setDescription(description);
		try {
			groupDao.save(userGroup);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("用户组描述更新失败！");
			return false;
		}
	}

	@Override
	public List<UserGroup> findAll() {
		// TODO Auto-generated method stub
		return groupDao.findAll();
	}

	@Override
	public List<Function> findAllFunction() {
		// TODO Auto-generated method stub
		return functionDao.findAll();
	}

	@Override
	public List<FunctionWithGroup> findAllFunctionWithGroups(int groupId) {
		// TODO Auto-generated method stub
		return functionWithGroupDao.findByGroupId(groupId);
	}

	@Override
	public boolean addFuncGro(int groupId, int[] functionId) {
		// TODO Auto-generated method stub
		System.out.println(groupId);
		System.out.println(functionId);
		List<Integer> list = new LinkedList<>();
		for (int i : functionId) {
			list.add(i);
		}
		System.out.println(list);
		for (int i = 0; i < 11; i++) {
			if (functionWithGroupDao.findByFunctionIdAndGroupId(i+1, groupId) == null) {
				System.out.println(i);
				if (list.contains(i+1)) {
					FunctionWithGroup functionWithGroup = new FunctionWithGroup();
					functionWithGroup.setFunctionId(i+1);
					functionWithGroup.setGroupId(groupId);
					functionWithGroupDao.save(functionWithGroup);
				}
			}
		}
		return true;
	}

	@Override
	public List<FunctionWithGroup> findByLoginId(String loginId) {
		// TODO Auto-generated method stub
		int groupId = userWithGroupDao.findByUserId(loginId).getGroupId();
		return functionWithGroupDao.findByGroupId(groupId);
	}
	
}
