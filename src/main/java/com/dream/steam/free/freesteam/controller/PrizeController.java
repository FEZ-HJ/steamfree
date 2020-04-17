package com.dream.steam.free.freesteam.controller;

import com.dream.steam.free.freesteam.dto.PrizeRecordDTO;
import com.dream.steam.free.freesteam.dto.ReturnJson;
import com.dream.steam.free.freesteam.entity.LotteryContent;
import com.dream.steam.free.freesteam.entity.PrizeContent;
import com.dream.steam.free.freesteam.entity.PrizeRecord;
import com.dream.steam.free.freesteam.service.PrizeContentService;
import com.dream.steam.free.freesteam.service.PrizeRecordService;
import com.dream.steam.free.freesteam.utils.DateUtil;
import com.dream.steam.free.freesteam.vo.PrizeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by H.J
 * 2020/4/16
 */
@RestController
@RequestMapping("prize")
public class PrizeController {

    @Autowired
    private PrizeContentService prizeContentService;

    @Autowired
    private PrizeRecordService prizeRecordService;

    /**
     * 保存奖品信息
     */
    @PostMapping("insert")
    public PrizeContent insert(@RequestBody PrizeContent prizeContent){
        prizeContent.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        return prizeContentService.insert(prizeContent);
    }

    /**
     * layui查询奖品信息接口
     */
    @GetMapping("list")
    public Map<String,Object> content(int page, int limit){
        List<PrizeContent> list = prizeContentService.findAll(page - 1,limit);
        int count = prizeContentService.count();
        return ReturnJson.RETURN("成功",list,count,0);
    }

    /**
     * 删除奖品
     */
    @DeleteMapping("delete")
    @Transactional
    public int delete(Long id){
//        prizeContentService.deleteAllByUid(id);
        return prizeContentService.delete(id);
    }

    /**
     * 查询所有抽奖信息
     */
    @GetMapping("findAllContent")
    public List<PrizeContent> findAllContent(int page,int size){
        return prizeContentService.findAll(page,size);
    }

    /**
     * 进入抽奖详情页面
     * 查询奖品信息
     * 查询本人抽奖信息
     * 查询全部抽奖信息
     */
    @GetMapping("prizeDetail")
    public PrizeVo prizeDetail(Long id){
//        查询前十个记录
        List<PrizeRecordDTO> recordDTOList = prizeRecordService.findAllByPrizeId(id,0,10);
//        查询抽奖总人数
        int count = prizeRecordService.count(id);
//        查询奖品详情
        PrizeContent prizeContent = prizeContentService.findById(id);

        PrizeVo prizeVo = new PrizeVo();
        prizeVo.setCount(count);
        prizeVo.setList(recordDTOList);
        prizeVo.setPrizeContent(prizeContent);

        return prizeVo;
    }

    /**
     * 查询全部抽奖人员信息
     */
    @GetMapping("prizeRecord")
    public List<PrizeRecordDTO> prizeRecord(Long id,int page,int size){
        return prizeRecordService.findAllByPrizeId(id,page,size);
    }

    /**
     * 保存抽奖记录
     */
    @PostMapping("insertRecord")
    public PrizeVo insertRecord(@RequestBody PrizeRecord prizeRecord){
        prizeRecord.setModifyTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        prizeRecordService.insert(prizeRecord);
//        查询前十个记录
        List<PrizeRecordDTO> recordDTOList = prizeRecordService.findAllByPrizeId(prizeRecord.getPrizeId(),0,10);
//        查询抽奖总人数
        int count = prizeRecordService.count(prizeRecord.getPrizeId());

        PrizeVo prizeVo = new PrizeVo();
        prizeVo.setCount(count);
        prizeVo.setList(recordDTOList);

        return prizeVo;
    }


}
