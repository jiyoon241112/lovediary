package com.lovediary.service;

import com.lovediary.dto.PointDto;
import com.lovediary.entity.Point;
import com.lovediary.repository.PointRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * PointService
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
@Service
public class PointService {
    private final PointRepository pointRepository;
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    // 목록 조회
    @Transactional
    public List<PointDto> getList() {
        List<Point> pointList = pointRepository.findByCoupleIdxOrderByIdx(1L);
        List<PointDto> resultList = new ArrayList<>();

        for(Point point : pointList) {
            resultList.add(convertToDto(point));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public PointDto getOne(Long idx) {
        Optional<Point> wrapper = pointRepository.findById(idx);
        Point point = wrapper.get();

        return convertToDto(point);
    }

    // DTO 변환
    private PointDto convertToDto(Point point) {
        return PointDto.builder()
                .idx(point.getIdx())
                .coupleIdx(point.getCoupleIdx())
                .contents(point.getContents())
                .point(point.getPoint())
                .registDate(point.getRegistDate())
                .build();
    }
}
