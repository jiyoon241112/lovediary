package com.lovediary.service;

import com.lovediary.dto.*;
import com.lovediary.entity.Community;
import com.lovediary.entity.Course;
import com.lovediary.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * CourseService
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-08
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-08          JJY             최초 등록
 **/
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // <데이트 코스 리스트>
    @Transactional
    public List<BookMarkCourseDto> getCourseList(Long idx, String keyword) {
        List<BookMarkCourse> courseList = null;
        if(idx == null){
            courseList = courseRepository.courseList(keyword);
        } else {
            courseList = courseRepository.courseList(idx, keyword);
        }
        List<BookMarkCourseDto> resultList = new ArrayList<>();

        for(BookMarkCourse bookMarkCourse : courseList) {
            resultList.add(convertToDto(bookMarkCourse));
        }

        return resultList;
    }

    // <데이트 코스 상세>
    @Transactional
    public List<CourseListDto> getCourseDetail(Long idx) {
        List<CourseList> detailList = courseRepository.courseDetailList(idx);
        List<CourseListDto> resultList = new ArrayList<>();

        for(CourseList courseList : detailList) {
            resultList.add(convertToDto(courseList));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public CourseDto getOne(Long idx) {
        Optional<Course> wrapper = courseRepository.findById(idx);
        Course course = wrapper.get();

        return convertToDto(course);
    }

    // Dto 변환
    private BookMarkCourseDto convertToDto(BookMarkCourse bookMarkCourse) {
        return BookMarkCourseDto.builder()
                .idx(bookMarkCourse.getIdx())
                .title(bookMarkCourse.getTitle())
                .address(bookMarkCourse.getAddress())
                .datePlaceIdx(bookMarkCourse.getDatePlaceIdx())
                .placeCnt(bookMarkCourse.getPlaceCnt())
                .courseIdx(bookMarkCourse.getCourseIdx())
                .build();
    }

    // Dto 변환
    private CourseListDto convertToDto(CourseList courseList) {
        return CourseListDto.builder()
                .idx(courseList.getIdx())
                .themeIdx(courseList.getThemeIdx())
                .courseTitle(courseList.getCourseTitle())
                .title(courseList.getTitle())
                .content(courseList.getContent())
                .homepage(courseList.getHomepage())
                .build();
    }

    // 코스 DTO 변환
    private CourseDto convertToDto(Course course) {
        return CourseDto.builder()
                .idx(course.getIdx())
                .themeIdx(course.getThemeIdx())
                .title(course.getTitle())
                .accountIdx(course.getAccountIdx())
                .openYn(course.getOpenYn())
                .deleteYn(course.getDeleteYn())
                .registDate(course.getRegistDate())
                .modifyDate(course.getModifyDate())
                .deleteDate(course.getDeleteDate())
                .build();
    }
}
