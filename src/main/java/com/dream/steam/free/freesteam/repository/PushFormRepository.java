package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.PushForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by H.J
 * 2019/11/6
 */
public interface PushFormRepository extends JpaRepository<PushForm,String> {

    List<PushForm> findByOpenIdAndUseDateIsNullAndStaleDateIsAfterOrderByCreateDateAsc(String openId, Date date);

//    select a.open_id,a.form_id from push_form a,(select MIN(create_date) create_date,open_id from push_form where use_date is null and stale_date > now() group by open_id) b where
//    a.open_id=b.open_id and a.create_date = b.create_date;

    @Query(value = "select a.* from push_form a," +
            "(select MIN(create_date) create_date,open_id from push_form where use_date is null and stale_date > now() group by open_id) " +
            "b where a.open_id=b.open_id and a.create_date = b.create_date;", nativeQuery = true)
    List<PushForm> findAllCanPush();
}
