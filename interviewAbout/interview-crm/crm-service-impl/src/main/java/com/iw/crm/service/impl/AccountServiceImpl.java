package com.iw.crm.service.impl;

import com.iw.crm.entity.Account;
import com.iw.crm.entity.AccountExample;
import com.iw.crm.entity.Dept;
import com.iw.crm.entity.DeptExample;
import com.iw.crm.exception.ServiceException;
import com.iw.crm.mapper.AccountMapper;
import com.iw.crm.mapper.DeptMapper;
import com.iw.crm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务控制器类
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private DeptMapper deptMapper;


    /**
     * 根据userMobile 查询账户是否存在
     *
     * @param userMobile
     * @return
     */
    @Override
    public Account selectByMobile(String userMobile) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andMobileEqualTo(userMobile);

        List<Account> accounts = accountMapper.selectByExample(accountExample);
        if (accounts!=null && !accounts.isEmpty()){
            return accounts.get(0);
        }
        return null;
    }

    /**
     * 添加部门
     *
     * @param deptName
     */
    @Override
    public void insertDept(String deptName) {
        //需要判断部门是否重复
        DeptExample deptExample = new DeptExample();
        deptExample.createCriteria().andDeptNameEqualTo(deptName);

        List<Dept> depts = deptMapper.selectByExample(deptExample);

        if (depts != null&& !depts.isEmpty()) {
            throw new ServiceException("部门已经存在");
        }
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setpId(Integer.valueOf(Dept.TOP_DEPT));

        deptMapper.insertSelective(dept);
    }
}
