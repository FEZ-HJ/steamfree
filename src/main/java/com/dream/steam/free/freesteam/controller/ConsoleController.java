package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.dto.FreeGameDTO;
import com.dream.steam.free.freesteam.entity.*;
import com.dream.steam.free.freesteam.service.*;
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

    @Autowired
    private GiftService giftService;

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

    @RequestMapping("prizeRecord")
    public String prizeRecord(Long id,Model model){
        model.addAttribute("id",id);
        return "prize/prizeRecord";
    }

    @RequestMapping("gift")
    public String gift(){
        return "gift/gift";
    }

    @RequestMapping("cdk")
    public String cdk(Long id,Long cdkId,Model model){
        if(cdkId != 0){
            GiftCdk giftCdk = giftService.findOneByCdkId(cdkId);
            model.addAttribute("cdk",giftCdk.getCdk());
            model.addAttribute("cdkId",cdkId);
        }
        model.addAttribute("id",id);
        return "gift/cdk";
    }

    @RequestMapping("giftAdd")
    public String giftAdd(Long id,Model model){
        if(id != 0){
            GiftContent giftContent = giftService.findOneById(id);
            model.addAttribute("giftContent",giftContent);
        }
        return "gift/giftAdd";
    }

    @RequestMapping("giftRecord")
    public String giftRecord(Long id,Model model){
        model.addAttribute("id",id);
        return "gift/giftRecord";
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
