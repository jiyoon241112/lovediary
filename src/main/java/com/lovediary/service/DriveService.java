package com.lovediary.service;

import com.lovediary.dto.DriveDto;
import com.lovediary.dto.DriveFileDto;
import com.lovediary.entity.Drive;
import com.lovediary.entity.DriveFile;
import com.lovediary.repository.DriveFileRepository;
import com.lovediary.repository.DriveRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * DriveService
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-10
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-10          HTH             최초 등록
 **/
@Service
public class DriveService {
    private final DriveRepository driveRepository;
    private final DriveFileRepository driveFileRepository;
    public DriveService(DriveRepository driveRepository, DriveFileRepository driveFileRepository) {
        this.driveRepository = driveRepository;
        this.driveFileRepository = driveFileRepository;
    }

    // 목록 조회
    @Transactional
    public List<DriveFileDto> getList(Long driveIdx) {
        List<DriveFile> fileList = driveFileRepository.findByDriveIdxAndDeleteYnOrderByIdxDesc(driveIdx, 'N');
        List<DriveFileDto> resultList = new ArrayList<>();

        for(DriveFile driveFile : fileList) {
            resultList.add(convertToDto(driveFile));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public DriveFileDto getOne(Long idx) {
        Optional<DriveFile> wrapper = driveFileRepository.findById(idx);
        DriveFile driveFile = wrapper.get();

        return convertToDto(driveFile);
    }

    // 파일 업로드
    @Transactional
    public Long saveItem(DriveFileDto driveFileDto) {
        return driveFileRepository.save(driveFileDto.toEntity()).getIdx();
    }

    // 드라이브 정보 조회
    @Transactional
    public DriveDto getDrive(Long coupleIdx) {
        Drive drive = driveRepository.findByCoupleIdx(coupleIdx);

        return convertToDto(drive);
    }

    // 드라이브 파일 DTO 변환
    @Transactional
    public DriveFileDto convertToDto(DriveFile driveFile) {
        return DriveFileDto.builder()
                .idx(driveFile.getIdx())
                .fileIdx(driveFile.getFileIdx())
                .driveIdx(driveFile.getDriveIdx())
                .size(driveFile.getSize())
                .deleteYn(driveFile.getDeleteYn())
                .registDate(driveFile.getRegistDate())
                .deleteDate(driveFile.getDeleteDate())
                .build();
    }

    // 드라이브 DTO 변환
    @Transactional
    public DriveDto convertToDto(Drive drive) {
        return DriveDto.builder()
                .idx(drive.getIdx())
                .coupleIdx(drive.getCoupleIdx())
                .path(drive.getPath())
                .useSize(drive.getUseSize())
                .totalSize(drive.getTotalSize())
                .build();
    }
}
