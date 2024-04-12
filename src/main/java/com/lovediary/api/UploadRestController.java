package com.lovediary.api;

import com.lovediary.service.FileService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping("/view/image/{idx}")
    public @ResponseBody byte[] viewImage(@PathVariable("idx") Long idx) throws IOException {
        return fileService.getOne(idx);
    }
}
