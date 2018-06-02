package com.kaishengit.tms.quartzJobs;


import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.service.TicketService;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 检查年票是否过期定时任务,每天0：0：1
 * @author drm
 * @date 2018/5/9
 */
public class CheckTicketJob implements Job{

    private Logger logger = LoggerFactory.getLogger(CheckTicketJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try{
            //1.获取Spring容器对象
            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            //2.从Spring容器中获取TicketService对象
            TicketService ticketService = applicationContext.getBean(TicketService.class);
            //3. 获取所有状态为[已销售]的年票
            List<Ticket> ticketList = ticketService.findTicketByState(Ticket.TICKET_STATE_SALE);
                //定义一个集合存放当前已过期的年票
                List<Ticket> outTimeTicketList = new ArrayList<>();

            for (Ticket ticket:ticketList){
                //获取当前年票的过期时间
                Date date = ticket.getTicketValidityEnd();

                DateTime dateTime = new DateTime(date.getTime());
                dateTime = dateTime.withTime(0,0,0,0);
                dateTime = dateTime.plusDays(1);

                if (dateTime.isBeforeNow()){
                    //该年票已过期，应设置状态为[已过期]
                    ticket.setTicketState(Ticket.TICKET_STATE_OUT_DATE);
                    outTimeTicketList.add(ticket);
                }
            }

            if (!outTimeTicketList.isEmpty()){
                ticketService.batchUpdateTicketState(outTimeTicketList);
                logger.info("今日过期年票{}张",outTimeTicketList.size());
            }


        }  catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("执行定时任务异常",e);
        }
    }
}
