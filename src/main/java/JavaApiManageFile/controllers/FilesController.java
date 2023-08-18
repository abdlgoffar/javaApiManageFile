package JavaApiManageFile.controllers;

import JavaApiManageFile.services.FilesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RestController
public class FilesController {
    private FilesService filesService;
    @Autowired
    public void setFilesService(FilesService filesService) {
        this.filesService = filesService;
    }
    @RequestMapping(value = "/v2/images", method = RequestMethod.POST)
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> upload(@RequestParam("img") MultipartFile multipartFile) throws IOException {
        log.info(multipartFile.getOriginalFilename());
        String upload = this.filesService.upload(multipartFile);
        return ResponseEntity.ok(upload);
    }
    @RequestMapping(value = "/v2/images/{name}", method = RequestMethod.GET)
    public ResponseEntity<?>download(@PathVariable("name") String file) throws IOException {
        byte[] download = this.filesService.download(file);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("images/png"))
                .body(download);
    }
}
