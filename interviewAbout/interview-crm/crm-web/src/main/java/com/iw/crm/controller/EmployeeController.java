package com.iw.crm.controller;

import com.iw.crm.service.AccountService;
import com.iw.web.dto.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public String listEmployee(){
        return "employee/list";
    }

    /**
     * 添加部门,ajax请求,可能需要返回Json 数据
     * @param deptName
     * @return
     */
    @PostMapping("/dept/new")
    @ResponseBody
    public ResponseBean newDept(String deptName){
        try {
            accountService.insertDept(deptName);
            return ResponseBean.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("部门不合法");
        }
    }
}
