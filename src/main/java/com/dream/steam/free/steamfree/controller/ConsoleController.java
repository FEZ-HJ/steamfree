package com.dream.steam.free.steamfree.controller;

import com.dream.steam.free.steamfree.dto.FreeGameDTO;
import com.dream.steam.free.steamfree.service.FreeGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by H.J
 * 2019/9/10
 */
@Controller
@RequestMapping("console")
public class ConsoleController {

    @Autowired
    private FreeGameService freeGameService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("freeGame")
    public String freeGame(){
        return "freeSteam/freeGame";
    }

    @RequestMapping("freeGameAdd")
    public String freeGameAdd(Long id, Model model){
        FreeGameDTO freeGameDTO = freeGameService.findById(id);
        model.addAttribute("freeGameDTO",freeGameDTO);
        return "freeSteam/freeGameAdd";
    }
}
