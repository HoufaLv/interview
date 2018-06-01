package com.iw.crm.service.impl;

import com.iw.crm.entity.Dept;
import com.iw.crm.entity.DeptExample;
import com.iw.crm.mapper.DeptMapper;
import com.iw.crm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门相关的业务层
 */
@Service
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptMapper deptMapper;


    /**
     * 获得所有Dept信息
     *
     * @return
     */
    @Override
    public List<Dept> listDept() {
        return deptMapper.selectByExample(new DeptExample());
    }
}
