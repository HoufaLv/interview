package com.iw.crm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.iw.crm.entity.Account;
import com.iw.crm.entity.Dept;
import com.iw.crm.service.AccountService;
import com.iw.crm.service.DeptService;
import com.iw.web.dto.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 账号相关的控制器
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private DeptService deptService;

    /**
     * 加载Ztree 数据
     * @return
     */
    @PostMapping("/depts.json")
    @ResponseBody
    public List<ZTreeNode> loadDeptData(){

        //获得所有部门列表
        List<Dept> deptList = deptService.listDept();

        //使用Guava
        List<ZTreeNode> zTreeNodeList = Lists.newArrayList(Collections2.transform(deptList, new Function<Dept, ZTreeNode>() {
            @Nullable
            @Override
            public ZTreeNode apply(@Nullable Dept dept) {
                ZTreeNode zTreeNode = new ZTreeNode();
                zTreeNode.setId(dept.getId());
                zTreeNode.setName(dept.getDeptName());
                zTreeNode.setpId(dept.getpId());
                return zTreeNode;
            }
        }));

        return zTreeNodeList;
    }


    /**
     * 添加账户
     * @param account
     * @param deptId
     * @return
     */
    @PostMapping("/new")
    public String insertAccount(Account account,Integer[] deptId){
        try {
            accountService.insertAccount(account,deptId);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
