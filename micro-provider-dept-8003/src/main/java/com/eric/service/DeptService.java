package com.eric.service;


import com.eric.entities.Dept;

import java.util.List;

public interface DeptService {
	public boolean add(Dept dept);

	public Dept get(Long id) throws Exception;

	public List<Dept> list();
}
