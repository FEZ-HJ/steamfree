package com.dream.steam.free.freesteam.vo;

import com.dream.steam.free.freesteam.dto.PrizeRecordDTO;
import com.dream.steam.free.freesteam.entity.PrizeContent;
import lombok.Data;

import java.util.List;

/**
 * Created by H.J
 * 2020/4/17
 */
@Data
public class PrizeVo {

    private List<PrizeRecordDTO> list;

    private int count;

    private PrizeContent prizeContent;

}
