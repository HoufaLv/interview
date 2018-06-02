package com.ksit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.ksit.tms.com.ksit.tms.dto.ResponseBean;
import com.ksit.tms.entity.TicketInRecord;
import com.ksit.tms.entity.TicketOutRecord;
import com.ksit.tms.entity.TicketStore;
import com.ksit.tms.exception.ServiceException;
import com.ksit.tms.service.TicketService;
import com.ksit.tms.service.TicketStoreService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 票务相关业务控制器
 * 公共前缀为 /ticket
 *
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketStoreService ticketStoreService;


    /**
     * 跳转到票务管理首页
     * @return
     */
    @GetMapping("/storage")
    public String ticketHome(Model model, @RequestParam(required = false,defaultValue = "1",name = "p")Integer pageNo){
        PageInfo<TicketInRecord> pageInfo = ticketService.selectTicketRecordByPageNo(pageNo);

        //将分页数据传到前端
        model.addAttribute("pageInfo",pageInfo);
        return "ticket/storage/home";
    }

    /**
     * 跳转到年票入库页面,将时间固定,传到前端去
     * @return
     */
    @GetMapping("/storage/new")
    public String insertTicket(Model model){
        //使用服务器时间做标准,传给前端,在页面上做固定展示
        String time =DateTime.now().toString("YYYY-MM-dd");
        model.addAttribute("time",time);

        return "/ticket/storage/insertTicket";
    }
    @PostMapping("/storage/new")
    public String insertTicket(TicketInRecord ticketInRecord, RedirectAttributes redirectAttributes){
        //去业务层去处理业务,将数据进行保存
        ticketService.insertTicketInRecord(ticketInRecord);
        redirectAttributes.addFlashAttribute("message","年票入库成功");

        //跳转到展示页面去
        return "/ticket/storage/home";
    }

    /**
     * 删除年票入库记录
     *
     * @return
     */
    @GetMapping("/storage/{id:\\d+}/del")
    public String deleteTicketInRecord(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        //在service 层去做删除业务,想前端传回状态,如果抛出异常,则代表删除不成功

        try {
            ticketService.deleteTicketInRecord(id);
            redirectAttributes.addFlashAttribute("message","删除成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","年票入库记录删除失败");
        }

        //跳转到home 页面去
        return "redirect:/ticket/storage";
    }


    /*年票下发相关*/

    /**
     * 跳转到年票下发页面
     * 顺便做一下分页功能
     * @return
     */
    @GetMapping("/out")
    public String ticketOutHome(@RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,Model model){
        // TODO: 2018/4/27 0027 跳转到下发列表页面,传过去分页数据做展示

        PageInfo<TicketOutRecord> pageInfo = ticketService.selectTicketOutRecordByPageNo(pageNo);

        //将pageInfo 对象发送到前端去
        model.addAttribute("page",pageInfo);
        return "ticket/out/home";
    }

    /**
     * 跳转到年票下发页面
     * @param model
     * @return
     */
    @GetMapping("/out/new")
    public String ticketOut(Model model){
        //将当前时间所有销售点信息查出来传给前端页面展示
        String time = DateTime.now().toString("YYYY-MM-dd");
        List<TicketStore> ticketStoreList = ticketStoreService.selectAllTicketStore();

        model.addAttribute("time",time);
        model.addAttribute("ticketStoreList",ticketStoreList);

        return "ticket/out/insertOut";
    }

    /**
     * 从页面提交下发数据
     * @param ticketOutRecord
     * @return
     */
    @PostMapping("/out/new")
    public String ticketOut(TicketOutRecord ticketOutRecord,RedirectAttributes redirectAttributes){
        try {
            ticketService.saveTicketOutRecord(ticketOutRecord);
            redirectAttributes.addFlashAttribute("message","年票下发成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }

        return "redirect:/ticket/out";
    }

    /**
     * 删除下发记录
     * @return
     */
    @GetMapping("/out/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean delTicketOutRecord(@PathVariable Integer id){
        ticketService.deleteTicketOutRecord(id);
        return ResponseBean.success();
    }

    /**
     * 跳转到盘点统计页面
     * 并将年票做一下总结
     * @return
     */
    @GetMapping("/chart")
    public String chartTicket(Model model){
        // TODO: 2018/4/27 0027 将票的状态统计一下,封装一个Map 发送到前端
        Map<String,Long> resultMap = ticketService.countTicketByState();

        model.addAttribute("resultMap",resultMap);
        return "ticket/chart/home";
    }

}