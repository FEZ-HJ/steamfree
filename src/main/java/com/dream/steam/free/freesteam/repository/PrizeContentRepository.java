package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.PrizeContent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by H.J
 * 2020/4/16
 */
public interface PrizeContentRepository extends JpaRepository<PrizeContent,String> {

    PrizeContent findById(Long id);

    List<PrizeContent> findAllByOrderByIdDesc(Pageable pageable);

    int countAllBy();

    int deleteById(Long id);

    List<PrizeContent> findAllByWinnersOrderByEndTimeDesc(String winners);
}
