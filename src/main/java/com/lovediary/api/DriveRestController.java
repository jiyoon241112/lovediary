package com.lovediary.api;

import com.lovediary.dto.DriveDto;
import com.lovediary.dto.DriveFileDto;
import com.lovediary.service.DriveService;
import com.lovediary.service.FileService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 * DriveRestController
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-16
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-16          HTH             최초 등록
 **/
@RestController
public class DriveRestController {
    private final DriveService driveService;
    private final FileService fileService;
    public DriveRestController(DriveService driveService, FileService fileService) {
        this.driveService = driveService;
        this.fileService = fileService;
    }

    @PostMapping("/drive/upload")
    public ResponseData saveUploadFile(@RequestParam(name = "file", required = false) MultipartFile uploadFile) throws IOException {
        if(uploadFile == null) {
            return new ResponseData(constValues.ERROR, "파일을 업로드해주세요.", null);
        }

        DriveDto driveDto = driveService.getDrive(1L);
        Long uploadIdx = fileService.saveItem(uploadFile, 4);

        DriveFileDto fileDto = DriveFileDto.builder()
                .fileIdx(uploadIdx)
                .driveIdx(driveDto.getIdx())
                .size(uploadFile.getSize())
                .build();

        Long result = driveService.saveItem(fileDto);

        return new ResponseData(constValues.DONE, "파일을 업로드했습니다.", result);
    }
}
