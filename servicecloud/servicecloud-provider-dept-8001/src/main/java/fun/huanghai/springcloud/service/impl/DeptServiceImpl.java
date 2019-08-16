package fun.huanghai.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.huanghai.springcloud.dao.DeptDao;
import fun.huanghai.springcloud.entity.Dept;
import fun.huanghai.springcloud.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptDao dao;
	
	@Override
	public boolean add(Dept dept) {
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Dept> list() {
		return dao.findAll();
	}

	
}
