package com.dream.steam.free.steamfree.service;

import com.dream.steam.free.steamfree.dto.FreeGameDTO;
import com.dream.steam.free.steamfree.entity.FreeGame;
import com.dream.steam.free.steamfree.repository.FreeGameRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H.J
 * 2019/9/9
 */
@Service
public class FreeGameService {

    @Autowired
    private FreeGameRepository repository;

    public FreeGame insert(FreeGame freeGame){
        return repository.save(freeGame);
    }

    public int count(){
        return repository.countAllBy();
    }

    @Modifying
    @Transactional
    public int delete(Long id){
        return repository.deleteById(id);
    }

    public List<FreeGameDTO> findAll(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        List<FreeGame> freeGames = repository.findAllByOrderByIdDesc(pageable);
        return getDTO(freeGames);
    }

    public List<FreeGameDTO> findAll(int page){
        Pageable pageable = PageRequest.of(page,20);
        List<FreeGame> freeGames = repository.findAllByOrderByIdDesc(pageable);
        return getDTO(freeGames);
    }

    public FreeGameDTO findById(Long id){
        FreeGame freeGame = repository.findById(id);
        return setDTO(freeGame);
    }

    private FreeGameDTO setDTO(FreeGame freeGame){
        FreeGameDTO freeGameDTO = new FreeGameDTO();
        BeanUtils.copyProperties(freeGame,freeGameDTO);

        List<String> imageList = splitStringTOList(freeGame.getImages(),"http");
        freeGameDTO.setImages(imageList);

        List<String> tagList = splitStringTOList(freeGame.getTag(),",");
        freeGameDTO.setTag(tagList);

        List<String> typeList = splitStringTOList(freeGame.getType(),",");
        freeGameDTO.setType(typeList);

        return freeGameDTO;
    }

    private List<String> splitStringTOList(String string,String regex){
        List<String> list = new ArrayList<>();
        String[] imagesArray = string.split(regex);
        for(String image : imagesArray){
            if(StringUtils.isNotBlank(image)){
                if(regex.equals("http")){
                    image = "http" + image;
                }
                list.add(image);
            }
        }
        return list;
    }

    private List<FreeGameDTO> getDTO(List<FreeGame> freeGames){
        List<FreeGameDTO> freeGameDTOS = new ArrayList<>();
        for(FreeGame freeGame : freeGames){
            FreeGameDTO freeGameDTO = setDTO(freeGame);
            freeGameDTOS.add(freeGameDTO);
        }
        return freeGameDTOS;
    }
}
