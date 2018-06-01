package com.iw.crm.service;

import com.iw.crm.entity.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 获得所有Dept信息
     * @return
     */
    List<Dept> listDept();
}
