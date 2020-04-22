package com.dream.steam.free.freesteam.service;

import com.dream.steam.free.freesteam.dto.ReturnJson;
import com.dream.steam.free.freesteam.entity.GiftCdk;
import com.dream.steam.free.freesteam.entity.GiftContent;
import com.dream.steam.free.freesteam.entity.OperationRecord;
import com.dream.steam.free.freesteam.repository.GiftCdkRepository;
import com.dream.steam.free.freesteam.repository.GiftContentRepository;
import com.dream.steam.free.freesteam.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by H.J
 * 2020/4/17
 */
@Service
public class GiftService {

    @Autowired
    private GiftContentRepository repository;

    @Autowired
    private GiftCdkRepository giftCdkRepository;

    @Autowired
    private ExpService expService;

//    查询所有礼品
    public List<GiftContent> findAll(){
        return repository.findAllByOrderByPriceAsc();
    }

    public GiftContent findOneById(Long id){
        return repository.findOneById(id);
    }

    public int count(){
        return repository.countAllBy();
    }

    public int cdkCountById(Long id){
        return giftCdkRepository.countAllById(id);
    }

    public int deleteContent(Long id){
        return repository.deleteById(id);
    }

    public int deleteCdk(Long id){
        return giftCdkRepository.deleteById(id);
    }

    public int deleteByGiftId(Long id){
        return giftCdkRepository.deleteByGiftId(id);
    }

//    新增礼品
    public GiftContent insert(GiftContent giftContent){
        return repository.save(giftContent);
    }

//    新增奖券
    public GiftCdk inset(GiftCdk giftCdk){
        return giftCdkRepository.save(giftCdk);
    }

//    获取一个可用奖券
    public GiftCdk findOneByGiftId(Long giftId){
        Object[] object = (Object[])giftCdkRepository.findOneByGiftId(giftId);
        if(null == object){
            return null;
        }
        GiftCdk giftCdk = new GiftCdk();
        giftCdk.setId(((BigInteger)object[0]).longValue());
        giftCdk.setGiftId(((BigInteger)object[1]).longValue());
        giftCdk.setCdk((String)object[2]);
        return giftCdk;
    }

    public GiftCdk findOneByCdkId(Long id){
        return giftCdkRepository.findOneById(id);
    }

//    兑换礼物
    @Transient
    public Map<String,Object> convertGift(Long giftId, String openId){
        Map<String,Object> map = new HashMap<>();

        GiftContent giftContent = repository.findOneById(giftId);
//        存入奖品兑换信息
        GiftCdk giftCdk = findOneByGiftId(giftId);
        if(giftCdk == null){
            map.put("message","奖品已被兑换完啦！");
            map.put("code","100");
        }else {
            if(expService.save(openId,giftContent)){
                giftCdk.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
                giftCdk.setOpenId(openId);
                giftCdkRepository.save(giftCdk);
                OperationRecordService.save(new OperationRecord(openId,"兑换礼品"+giftContent.getTitle()+"成功"));
                map.put("message","兑换成功！");
                map.put("giftCdk",giftCdk);
                map.put("code","200");
            }else{
                map.put("message","积分不足");
                map.put("code","100");
            }
        }
        return map;
    }

    public List<GiftCdk> giftRecord(String openId){
        return giftCdkRepository.findAllByOpenIdOrderByCreateTimeDesc(openId);
    }

    public List<GiftCdk> record(Long id,int page ,int size){
        Pageable pageable = PageRequest.of(page,size);
        return giftCdkRepository.findAllByGiftIdOrderByIdDesc(id,pageable);
    }
}
