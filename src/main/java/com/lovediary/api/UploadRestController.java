package com.lovediary.api;

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
 * UploadRestController
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          HTH             최초 등록
 **/
@RestController
public class UploadRestController {
    private final FileService fileService;
    public UploadRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseData upload(@RequestParam("type") Integer type, @RequestParam("file") MultipartFile uploadFile) throws IOException {
        Long result = fileService.saveItem(uploadFile, type);

        return new ResponseData(constValues.DONE, "저장되었습니다.", result);
    }
}
