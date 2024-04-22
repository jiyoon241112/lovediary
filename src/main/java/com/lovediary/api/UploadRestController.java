package com.lovediary.api;

import com.lovediary.dto.FileDto;
import com.lovediary.service.FileService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
    public ResponseData upload(@RequestParam("type") Integer type,
                               @RequestParam("file") MultipartFile uploadFile) throws IOException {
        Long result = fileService.saveItem(uploadFile, type);

        return new ResponseData(constValues.DONE, "저장되었습니다.", result);
    }

    @GetMapping("/download/{idx}")
    public void download(HttpServletResponse response, @PathVariable("idx") Long idx) {
        FileDto fileDto =  fileService.getOne(idx);
        String filePath = fileDto.getPath();
        String fileName = fileDto.getFileName();

        File file = new File(filePath);
        long fileLength = file.length();

        String contentType = filePath.substring(filePath.lastIndexOf(".") + 1);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", "image/" + contentType);
        response.setHeader("Content-Length", "" + fileLength);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        try(
            FileInputStream fis = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();
        ){
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
    }

    @GetMapping("/view/image/{idx}")
    public @ResponseBody byte[] viewImage(@PathVariable("idx") Long idx) throws IOException {
        return fileService.getByte(idx);
    }

}
