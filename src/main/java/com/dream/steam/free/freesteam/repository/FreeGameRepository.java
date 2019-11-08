package com.dream.steam.free.freesteam.repository;

import com.dream.steam.free.freesteam.entity.FreeGame;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by H.J
 * 2019/9/9
 */
public interface FreeGameRepository  extends JpaRepository<FreeGame,String> {

    List<FreeGame> findAllByOrderByIdDesc(Pageable pageable);

    FreeGame findById(Long id);

    int countAllBy();

    int deleteById(Long id);
}
