package com.lovediary.service;

import com.lovediary.entity.File;
import com.lovediary.repository.FileRepository;
import com.lovediary.dto.FileDto;
import com.lovediary.values.constValues;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * FileService
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          HTH             최초 등록
 **/
@Service
public class FileService {
    @Value("${file.path}")
    private String uploadPath;
    private final FileRepository fileRepository;
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    // 목록 조회
    @Transactional
    public List<FileDto> getList(List<Long> idxList) {
        List<File> fileList = fileRepository.findByIdxInOrderByIdxDesc(idxList);
        List<FileDto> resultList = new ArrayList<>();

        for(File file : fileList) {
            resultList.add(convertToDto(file));
        }

        return resultList;
    }

    // 목록 조회
    @Transactional
    public FileDto getOne(Long idx) {
        Optional<File> wrapper = fileRepository.findById(idx);
        File file = wrapper.get();

        FileDto fileDto = convertToDto(file);
        fileDto.setPath(uploadPath + file.getPath());

        return fileDto;
    }

    // 업로드
    @Transactional
    public Long saveItem(MultipartFile uploadFile, Integer type) throws IOException {
        StringBuilder builder = new StringBuilder();

        // type 1: 프로필, 2: 커뮤니티/밸런스 게임, 3: 채팅, 4: 드라이브
        if(type == 1) {
            builder.append(constValues.PROFILE_FILE_PATH);
        } else if(type == 2) {
            builder.append(constValues.COMMUNITY_FILE_PATH);
        } else if(type == 3) {
            builder.append(constValues.CHATTING_FILE_PATH);
        } else if(type == 4) {
            builder.append(constValues.DRIVE_FILE_PATH);
        } else return null;

        builder.append(UUID.randomUUID().toString().replaceAll("-", ""));

        // 디렉토리 없으면 생성
        String savePath = builder.toString();
        java.io.File uploadPathFolder = new java.io.File(uploadPath, builder.toString());

        if(!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }

        builder.append("/");
        builder.append(uploadFile.getOriginalFilename());
        savePath = builder.toString();

        Path path = Paths.get(uploadPath + savePath);
        uploadFile.transferTo(path);

        // DB INSERT
        FileDto fileDto = FileDto.builder()
                .path(savePath)
                .fileName(uploadFile.getOriginalFilename())
                .build();

        return fileRepository.save(fileDto.toEntity()).getIdx();
    }

    // 파일 조회
    @Transactional
    public byte[] getByte(Long idx) throws IOException {
        Optional<File> wrapper = fileRepository.findById(idx);
        File file = wrapper.get();

        return Files.readAllBytes(new java.io.File(uploadPath, file.getPath()).toPath());
    }

    // DTO 변환
    private FileDto convertToDto(File file) {
        return FileDto.builder()
                .idx(file.getIdx())
                .path(file.getPath())
                .fileName(file.getFileName())
                .registDate(file.getRegistDate())
                .build();
    }
}
