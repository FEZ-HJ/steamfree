package com.dream.steam.free.freesteam.dto;

import com.dream.steam.free.freesteam.entity.LotteryContent;
import com.dream.steam.free.freesteam.entity.LotteryRecord;
import lombok.Data;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
@Data
public class LotteryDTO {

    List<LotteryRecord> records;

    LotteryContent lotteryContent;

    LotteryRecord myRecord;
}
