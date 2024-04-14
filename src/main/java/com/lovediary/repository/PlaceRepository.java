package com.lovediary.repository;

import com.lovediary.dto.BookMarkPlace;
import com.lovediary.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * PlaceRepository
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          JJY             최초 등록
 **/

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query(nativeQuery = true, value =
            "SELECT dp.idx, dp.theme_idx, dp.`type`,dp.`title`, dp.address, pb.place_idx " +
            "FROM date_place dp " +
            "LEFT JOIN( " +
            " SELECT place_idx " +
            " FROM place_bookmark pb  " +
            ") AS pb " +
            "ON dp.idx = pb.place_idx " +
            "WHERE (:idx IS NULL OR dp.theme_idx = :idx) " +
            "AND dp.delete_yn ='N' " +
            "AND dp.title LIKE CONCAT('%', :keyword, '%')")
    List<BookMarkPlace> placeList(Long idx, String keyword);

    @Query(nativeQuery = true, value =
            "SELECT dp.idx, dp.theme_idx, dp.`type`,dp.`title`, dp.address, pb.place_idx " +
            "FROM date_place dp " +
            "LEFT JOIN( " +
            " SELECT place_idx " +
            " FROM place_bookmark pb  " +
            ") AS pb " +
            "ON dp.idx = pb.place_idx " +
            "WHERE (:idx IS NULL OR dp.theme_idx = :idx) " +
            "AND dp.delete_yn ='N' " +
            "AND dp.title LIKE CONCAT('%', :keyword, '%') " +
            "ORDER BY ABS(dp.latitude - :latitude) + ABS(dp.longitude - :longitude) ")
    List<BookMarkPlace> placeList(Long idx, String keyword, Float latitude, Float longitude);

    Place findByIdxAndDeleteYn(Long idx, Character deleteYn);
}
