package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.entity.FreeGame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2019/9/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FreeGameServiceTest {

    @Autowired
    private FreeGameService service;

    @Test
    public void insert() {
        FreeGame freeGame = new FreeGame();
        freeGame.setAllAssess("特别好评 (6,963)");
        freeGame.setAssess("特别好评（45）");
        freeGame.setChineseName("超级房车赛：起点2");
        freeGame.setContent("作为英国电影学院奖获奖影片《赛车手:格网》的续集，《赛车手:格网》获得了数百万的销量。体验与先进的人工智能的激烈比赛，并沉浸在与网格2的新TrueFeel™处理系统的比赛，这是权力的边缘控制兴奋背后的每一个标志性的车轮。新一代的自我游戏技术平台提供了重新定义的视觉和令人瞠目结舌的损害，因为你证明自己跨越三个大洲，在一个新的，不断发展的世界赛车运动。赢得声誉，球迷和财富，因为你在激烈的，无情的比赛中，在许可的赛道上，美丽的城市街道和致命的山路上，你的道路通往顶峰。GRID 2还将通过创新的模式，一个完全独立的进程系统，以及与RaceNet的深度集成，为多人游戏设定新的标准。");
        freeGame.setEnglishName("Grid 2");
        freeGame.setFactory("Steam");
        freeGame.setImages("https://media.st.dl.bscstorage.net/steam/apps/44350/ss_860483b888be640c3fe858d21a3f344b5d40d485.600x338.jpg?t=1558459769");
        freeGame.setLanguage("无中文");
        freeGame.setNewPrice("0.00");
        freeGame.setPrice("88.00");
        freeGame.setRowImage("https://media.st.dl.bscstorage.net/steam/apps/44350/header.jpg?t=1558459769");
        freeGame.setSaleDate("2013年5月28日");
        freeGame.setTag("竞速");
        freeGame.setType("结束时间：2019-05-23");
        service.insert(freeGame);
    }
}