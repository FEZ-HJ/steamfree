package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.HomePageImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by H.J
 * 2020/5/28
 */
public interface HomePageImageRepository extends JpaRepository<HomePageImage,String> {

    List<HomePageImage> findAllByOrderByIdAsc();
}
