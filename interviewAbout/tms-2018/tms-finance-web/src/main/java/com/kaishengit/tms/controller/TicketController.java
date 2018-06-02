package com.kaishengit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.joda.time.DateTime;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 *
 * @author drm
 * @date 2018/4/21
 */
@Controller
@RequestMapping("/ticket")
public class TicketController  {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketStoreService ticketStoreService;

    /**
     * 年票入库首页，入库记录
     * @date 2018/4/21
     * @param
     * @return java.lang.String
     */
    @GetMapping("/storage")
    public String ticketIn(Model model,@RequestParam(required = false,defaultValue = "1",name = "p") Integer pageNo){

        PageInfo<TicketInRecord> pageInfo = ticketService.findTicketRecordByPageNo(pageNo);


        model.addAttribute("pageInfo",pageInfo);
        return "ticket/storage/home";
    }

    /**
     * 新增入库记录
     * @date 2018/4/21
     * @param
     * @return java.lang.String
     */
    @GetMapping("/storage/new")
    public String newTicketStorage(Model model){
        String today = DateTime.now().toString("YYYY-MM-dd");

        model.addAttribute("today",today);
        return "ticket/storage/new";
    }


    @PostMapping("/storage/new")
    public String newTicketStorage(TicketInRecord ticketInRecord, RedirectAttributes redirectAttributes){

        try {
            ticketService.saveTicketInRecord(ticketInRecord);
            redirectAttributes.addFlashAttribute("message","新增成功");
        } catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/ticket/storage";
    }

    //入库记录删除
    @GetMapping("/storage/{id:\\d+}/del")
    public String delTicketStorage(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        try {
            ticketService.delTicketInRecordById(id);
            redirectAttributes.addFlashAttribute("message","删除成功");
        } catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/ticket/storage";
    }

    /**
     * 入库记录编辑
     * @date 2018/4/23
     * @param id, model
     * @return java.lang.String
     */
    @GetMapping("/storage/{id:\\d+}/edit")
    public String updateTicketStorage(@PathVariable Integer id,Model model){
        TicketInRecord ticketInRecord = ticketService.findTicketInRecordById(id);

        String today = DateTime.now().toString("YYYY-MM-dd");

        model.addAttribute("ticketInRecord",ticketInRecord);
        model.addAttribute("today",today);
        return "ticket/storage/edit";
    }


    @PostMapping("/storage/{id:\\d+}/edit")
    public String updateTicketStorage(TicketInRecord ticketInRecord,RedirectAttributes redirectAttributes){
        ticketService.updateTicketInRecord(ticketInRecord);
        return "redirect:/ticket/storage";
    }

    /*--------------------------年票统计----------------------------*/

    /**
     * 年票统计首页
     * @date 2018/4/23
     * @param []
     * @return java.lang.String
     */
    @GetMapping("/chart")
    public String ticketChart(Model model){
        Map<String,Long> resultMap = ticketService.countTicketByState();
        model.addAttribute("resultMap",resultMap);
        return "ticket/chart/home";
    }

    /*--------------------------年票下发----------------------------*/

    /**
     * 年票下发首页
     * @date 2018/4/23
     * @param []
     * @return java.lang.String
     */
    @GetMapping("/out")
    public String ticketOut(Model model,
                            @RequestParam(name = "p",required = false,defaultValue = "1")Integer pageNo){

        PageInfo<TicketOutRecord> pageInfo = ticketService.findTicketOutRecordByPageNo(pageNo);

        model.addAttribute("pageInfo",pageInfo);
        return "ticket/out/home";
    }

    /**
     * 新增年票下发
     * @date 2018/4/23
     * @param model
     * @return java.lang.String
     */
    @GetMapping("/out/new")
    public String newTicketOut(Model model){
        String today = DateTime.now().toString("YYYY-MM-dd");
        //查找所有售票点
        List<TicketStore> ticketStoreList = ticketStoreService.findAllTicketStore();

        model.addAttribute("today",today);
        model.addAttribute("ticketStoreList",ticketStoreList);
        return "ticket/out/new";
    }

    @PostMapping("/out/new")
    public String newTicketOut(TicketOutRecord ticketOutRecord,RedirectAttributes redirectAttributes){

        try {
            ticketService.saveTicketOutRecord(ticketOutRecord);
            redirectAttributes.addFlashAttribute("message","Congratulations!新增年票下发成功");
        } catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/ticket/out";
    }

    /**
     * 删除下发单
     * @date 2018/4/23
     * @param id
     * @return java.lang.String
     */
    @GetMapping("/out/{id:\\d+}/del")
    public String delTicketOut(@PathVariable Integer id,RedirectAttributes redirectAttributes){

        try {
            ticketService.delTicketOutRecordById(id);
            redirectAttributes.addFlashAttribute("message","Congratulations!删除成功");
        } catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }


        return "redirect:/ticket/out";
    }

}
