package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.dto.FreeGameDTO;
import com.dream.steam.free.freesteam.dto.ReturnJson;
import com.dream.steam.free.freesteam.entity.FreeGame;
import com.dream.steam.free.freesteam.service.FreeGameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by H.J
 * 2019/9/10
 */
@RestController
@RequestMapping("freeGame")
public class FreeGameController {

    @Autowired
    private FreeGameService service;

    @PostMapping("insert")
    public FreeGame insert(FreeGameDTO freeGameDTO){
        FreeGame freeGame = new FreeGame();
        BeanUtils.copyProperties(freeGameDTO,freeGame);
        freeGame.setTag(listToString(freeGameDTO.getTag(),','));
        freeGame.setType(listToString(freeGameDTO.getType(),','));
        freeGame.setImages(listToString(freeGameDTO.getImages(),' '));
        return service.insert(freeGame);
    }

    @DeleteMapping("delete")
    public int delete(Long id){
        return service.delete(id);
    }

    @GetMapping("findAll")
    public Map<String,Object> findAll(int page, int limit){
        int count = service.count();
        if(limit != 0){
            List<FreeGameDTO> list = service.findAll(page - 1,limit);
            return ReturnJson.RETURN("成功",list,count,0);
        }else {
            List<FreeGameDTO> list = service.findAll(page);
            return ReturnJson.RETURN("成功",list,count,0);
        }
    }

    @GetMapping("findById")
    public FreeGameDTO findById(Long id){
        return service.findById(id);
    }

    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
