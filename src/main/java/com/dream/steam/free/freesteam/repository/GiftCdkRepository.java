package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.GiftCdk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by H.J
 * 2020/4/20
 */
public interface GiftCdkRepository extends JpaRepository<GiftCdk,String> {

    @Query(nativeQuery = true,value = "SELECT id,gift_id,cdk FROM gift_cdk where gift_id = ?1 and open_id is null ORDER BY RAND() LIMIT 1")
    Object findOneByGiftId(Long giftId);

    List<GiftCdk> findAllByOpenIdOrderByCreateTimeDesc(String openId);

    List<GiftCdk> findAllByGiftIdOrderByIdDesc(Long GiftId,Pageable pageable);

    int countAllById(Long id);

    int deleteById(Long id);

    int deleteByGiftId(Long giftId);

    GiftCdk findOneById(Long id);

}
