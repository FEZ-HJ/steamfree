package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.dto.FreeGameDTO;
import com.dream.steam.free.freesteam.entity.LotteryContent;
import com.dream.steam.free.freesteam.entity.LotteryRecord;
import com.dream.steam.free.freesteam.entity.PrizeContent;
import com.dream.steam.free.freesteam.service.FreeGameService;
import com.dream.steam.free.freesteam.service.LotteryContentService;
import com.dream.steam.free.freesteam.service.LotteryRecordService;
import com.dream.steam.free.freesteam.service.PrizeContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by H.J
 * 2019/9/10
 */
@Controller
@RequestMapping("console")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConsoleController {

    @Autowired
    private FreeGameService freeGameService;

    @Autowired
    private LotteryContentService lotteryContentService;

    @Autowired
    private LotteryRecordService lotteryRecordService;

    @Autowired
    private PrizeContentService prizeContentService;

    @RequestMapping("prize")
    public String prize(){
        return "prize/prize";
    }

    @RequestMapping("prizeAdd")
    public String prizeAdd(Long id,Model model){
        if(id != 0){
            PrizeContent prizeContent = prizeContentService.findById(id);
            model.addAttribute("prizeContent",prizeContent);
        }
        return "prize/prizeAdd";
    }

    @RequestMapping("console")
    public String console(){
        return "freeSteam/console";
    }

    @RequestMapping("freeGame")
    public String freeGame(){
        return "freeSteam/freeGame";
    }

    @RequestMapping("freeGameAdd")
    public String freeGameAdd(Long id,Model model){
        if(id != 0){
            FreeGameDTO freeGameDTO = freeGameService.findById(id);
            model.addAttribute("freeGameDTO",freeGameDTO);
        }
        return "freeSteam/freeGameAdd";
    }

    @RequestMapping("lottery")
    public String lottery(){
        return "freeSteam/lotteryContent";
    }

    @RequestMapping("lotteryAdd")
    public String lotteryAdd(Long id,Model model){
        if(id != 0){
            LotteryContent lotteryContent = lotteryContentService.findById(id);
            model.addAttribute("lotteryContent",lotteryContent);
        }
        return "freeSteam/lotteryContentAdd";
    }

    @RequestMapping("lotteryRecord")
    public String lotteryRecord(Long id,Model model){
        model.addAttribute("id",id);
        return "freeSteam/lotteryRecord";
    }

    @RequestMapping("lotteryRecordAdd")
    public String lotteryRecordAdd(Long id,Model model,Long uid){
        if(id != 0){
            LotteryRecord lotteryRecord = lotteryRecordService.findById(id);
            model.addAttribute("lotteryRecord",lotteryRecord);
        }
        model.addAttribute("uid",uid);
        return "freeSteam/lotteryRecordAdd";
    }

    @RequestMapping("signIn")
    public String signIn(){
        return "freeSteam/signIn";
    }
}
