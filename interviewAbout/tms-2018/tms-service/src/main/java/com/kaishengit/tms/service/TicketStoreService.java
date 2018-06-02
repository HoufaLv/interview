package com.kaishengit.tms.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.StoreLoginLog;
import com.kaishengit.tms.entity.TicketStore;

import java.util.List;
import java.util.Map;

/**
 * 年票售票点业务类
 * @author drm
 * @date 2018/4/19
 */
public interface TicketStoreService {

    /**  
     * 创建新的售票点
     * @date 2018/4/19
     * @param [ticketStore]  
     * @return void  
     */ 
    void saveNewTicketStore(TicketStore ticketStore);

    /**
     * 根据当前页面和查询参数查询销售点
     * @date 2018/4/20
     * @param [pageNo, queryParam]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketStore>
     */
    PageInfo<TicketStore> findAllTicketStoreByPage(Integer pageNo, Map<String,Object> queryParam);

    /**  
     * 根据id查找对应售票点
     * @date 2018/4/20
     * @param [id]  
     * @return com.kaishengit.tms.entity.TicketStore  
     */ 
    TicketStore findTicketStoreById(Integer id);
    /**  
     * 根据主键查找售票点账号对象
     * @date 2018/4/20
     * @param [id]  
     * @return com.kaishengit.tms.entity.StoreAccount  
     */ 
    StoreAccount findStoreAccountById(Integer id);

    /**
     * 修改售票点对象
     * @date 2018/4/20
     * @param [ticketStore]
     * @return void
     */
    void updateTicketStore(TicketStore ticketStore);

    /**
     * 根据id删除售票点
     * @date 2018/4/20
     * @param [id]
     * @return void
     */
    void delTicketStoreById(Integer id);

    /**
     * 查找所有售票点
     * @date 2018/4/23
     * @param []
     * @return java.util.List<com.kaishengit.tms.entity.TicketStore>
     */
    List<TicketStore> findAllTicketStore();

    /**
     * 根据账号（手机号码）查找售票点账号对象
     * @date 2018/4/29
     * @param userMobile 账号（手机号）
     * @return com.kaishengit.tms.entity.StoreAccount
     */
    StoreAccount findStoreAccountByName(String userMobile);

    /**
     * 保存售票点登录日志
     * @date 2018/4/29
     * @param storeLoginLog 售票点登录日志
     * @return void
     */
    void saveStoreAccountLoginLog(StoreLoginLog storeLoginLog);
}
