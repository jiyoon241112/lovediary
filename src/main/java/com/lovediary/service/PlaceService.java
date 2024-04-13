package com.lovediary.service;
/**
 *
 * PlaceService
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          JJY             최초 등록
 **/
import com.lovediary.dto.*;
import com.lovediary.entity.Place;
import com.lovediary.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    // <데이트 장소/맛집 리스트>
    @Transactional
    public List<BookMarkPlaceDto> getPlaceList(Long idx, String keyword) {
        List<BookMarkPlace> placeList = null;
        if(idx == null){
            placeList = placeRepository.placeList(keyword);
        } else {
            placeList = placeRepository.placeList(idx, keyword);
        }
        List<BookMarkPlaceDto> resultList = new ArrayList<>();

        for(BookMarkPlace bookMarkPlace : placeList) {
            resultList.add(convertToDto(bookMarkPlace));
        }

        return resultList;
    }

    // <데이트 장소/맛집 상세 페이지>
    @Transactional
    public PlaceDto getOne(Long idx) {
        Place place = placeRepository.findByIdxAndDeleteYn(idx, 'N');

        return convertToDto(place);
    }

    // 데이트 장소 Dto 변환
    private PlaceDto convertToDto(Place place) {
        return PlaceDto.builder()
                .idx(place.getIdx())
                .imageList(place.getImageList())
                .title(place.getTitle())
                .contents(place.getContents())
                .homepage(place.getHomepage())
                .build();
    }

    // 즐겨찾기 Dto 변환
    private BookMarkPlaceDto convertToDto(BookMarkPlace bookMarkPlace) {
        return BookMarkPlaceDto.builder()
                .idx(bookMarkPlace.getIdx())
                .themeIdx(bookMarkPlace.getThemeIdx())
                .type(bookMarkPlace.getType())
                .title(bookMarkPlace.getTitle())
                .address(bookMarkPlace.getAddress())
                .placeIdx(bookMarkPlace.getPlaceIdx())
                .build();
    }
}
