package com.ksit.tms.controller;

import com.ksit.tms.entity.TicketStore;
import com.ksit.tms.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 新增售票点业务处理器
 *
 * @author Lvhoufa
 */
@Controller
@RequestMapping("/manage/storeAcc")
public class TicketStoreController {

    @Autowired
    TicketStoreService ticketStoreService;

    /**
     * 跳到售票点管理的home 页面去
     * 在进入home 页面的时候,将后台的数据库中的数据查出来,带到前端去
     *
     * @return
     */
    @GetMapping(value = "/home")
    public String home(Model model) {
        //去数据库查数据,查出所有售票点信息,将list 传给前端
        List<TicketStore> ticketStoreList = ticketStoreService.selectAllTicketStore();
        model.addAttribute("ticketStoreList", ticketStoreList);

        return "manage/storeAccount/home";
    }


    @GetMapping(value = "/new")
    public String newStoreAccount() {

        return "manage/storeAccount/newStoreAccount";
    }

    /**
     * 跳转到新增销售点账户页面
     * 需要两个参数,一个是实体类 ticketStore 实体类,spring 会尝试尽量封装一个和前端字段相同的对象过来
     * 另一个是RedirectAttribute 用于向前端传递信息
     * 添加一个销售点信息,就对应添加一个销售账户出来
     *
     * @return
     */
    @PostMapping(value = "/new")
    public String newStoreAccount(TicketStore ticketStore, RedirectAttributes redirectAttributes) {
        //接收信息,将ticketStore 对象插入到数据库中去,剩下的业务处理放到service 层去做

        ticketStoreService.insertTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message", "新增销售点信息成功");

        return "redirect:/manage/storeAcc/home";
    }

    /**
     * 更新销售点信息,使用前端传过来的id 去数据库中查出对应的对象,传给前端
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/update")
    public String updateStoreAccount(@PathVariable Integer id, Model model) {

        TicketStore ticketStore = ticketStoreService.selectTIcketStoreById(id);

        //通过Model 对象将查出来的数据传给前端
        model.addAttribute("ticketStore", ticketStore);

        //跳转到更新页面去
        return "/manage/storeAccount/updateStoreAccount";
    }

    /**
     * 对销售点做更新操作
     * 接收前端传过来的TicketStore 对象, 在数据库中做更新操作,然后重新定位到home 页面去
     *
     * @param ticketStore
     * @return
     */
    @PostMapping("/{id:\\d+}/update")
    public String updateStoreAccount(TicketStore ticketStore, RedirectAttributes redirectAttributes) {
        //更新逻辑,如果用户要修改电话号码,则要去修改store表中的store_tel 这个字段
        ticketStoreService.updateTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message", "修改售票点信息成功");
        return "redirect:/manage/storeAcc/home";
    }

    @GetMapping("/{id:\\d}/del")
    public String deleteStoreAccount(@PathVariable  Integer id){

        return null;
    }
}
