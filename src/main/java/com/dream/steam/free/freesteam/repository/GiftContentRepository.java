package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.GiftContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by H.J
 * 2020/4/17
 */
public interface GiftContentRepository extends JpaRepository<GiftContent,String> {

    List<GiftContent> findAllByOrderByPriceAsc();

    GiftContent findOneById(Long giftId);
}
