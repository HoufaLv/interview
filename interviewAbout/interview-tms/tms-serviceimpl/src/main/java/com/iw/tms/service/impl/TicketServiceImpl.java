package com.iw.tms.service.impl;

import com.iw.tms.entity.Account;
import com.iw.tms.entity.Ticket;
import com.iw.tms.entity.TicketInRecord;
import com.iw.tms.mapper.TicketInRecordMapper;
import com.iw.tms.mapper.TicketMapper;
import com.iw.tms.service.TicketService;
import com.iw.tms.util.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);


    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private TicketInRecordMapper ticketInRecordMapper;
    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 新增年票入库
     *
     * @param ticketInRecord
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicketInRecord(TicketInRecord ticketInRecord) {

        //设置ticketInRecord 的时间
        ticketInRecord.setCreateTime(new Date());

        //获取总数量 = 截至票号-起始票号+1
        BigInteger startNumber = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger endNumber = new BigInteger(ticketInRecord.getEndTicketNum());

        BigInteger totalNumber = endNumber.subtract(startNumber).add(new BigInteger(String.valueOf(1)));

        //设置ticketInRecord的其他属性
        Account currentAccount = shiroUtil.getCurrentAccount();
        ticketInRecord.setAccountId(currentAccount.getId());
        ticketInRecord.setAccountName(currentAccount.getAccountName());

        //设置入库的内容(票号段)
        ticketInRecord.setContent(ticketInRecord.getBeginTicketNum() + "--" + ticketInRecord.getEndTicketNum());

        ticketInRecordMapper.insertSelective(ticketInRecord);
        logger.info("新增年票入库{}", ticketInRecord);

        //添加totalNum条年票记录
        List<Ticket> ticketList = new ArrayList<>();

        for (int i = 0; i < totalNumber.intValue(); i++) {
            Ticket ticket = new Ticket();
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());
            ticket.setTicketNum(startNumber.add(new BigInteger(String.valueOf(i))).toString());
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);
            ticketList.add(ticket);
        }

        //批量添加已入库的年票
        ticketMapper.batchInsert(ticketList);
    }
}
