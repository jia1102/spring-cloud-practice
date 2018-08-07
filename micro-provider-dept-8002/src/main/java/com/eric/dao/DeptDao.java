package com.eric.dao;

import com.eric.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {

    public Dept findById(Long id);

    public List<Dept> findAll();

    public boolean addDept(Dept dept);
}
