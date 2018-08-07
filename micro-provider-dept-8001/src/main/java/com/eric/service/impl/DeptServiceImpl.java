package com.eric.service.impl;


import com.eric.dao.DeptDao;
import com.eric.entities.Dept;
import com.eric.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao dao;
	
	@Override
	public boolean add(Dept dept)
	{
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id) throws Exception {

		Dept dept = dao.findById(id);

		if (StringUtils.isEmpty(dept)) {
			throw new Exception();
		}
		return dept;
	}

	@Override
	public List<Dept> list()
	{
		return dao.findAll();
	}

}
