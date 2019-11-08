package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.LotteryContent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/10
 */
public interface LotteryContentRepository extends JpaRepository<LotteryContent,String> {

    LotteryContent findById(Long id);

    List<LotteryContent> findAllByOrderByIdDesc(Pageable pageable);

    int countAllBy();

    int deleteById(Long s);

    List<LotteryContent> findAllByOpenidOrderByIdDesc(String openid);
}
