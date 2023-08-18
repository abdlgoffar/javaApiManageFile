package JavaApiManageFile.controllers;

import JavaApiManageFile.services.ImagesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImagesController {
    private ImagesServices imagesServices;
    @Autowired
    public void setImagesServices(ImagesServices imagesServices) {
        this.imagesServices = imagesServices;
    }
    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public ResponseEntity<?> upload(@RequestParam("img") MultipartFile multipartFile) throws IOException {
        String upload = this.imagesServices.upload(multipartFile);
        return ResponseEntity.ok(upload);
    }
    @RequestMapping(value = "/images/{name}", method = RequestMethod.GET)
    public ResponseEntity<?>download(@PathVariable("name") String file) {
        byte[] download = this.imagesServices.download(file);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("images/png"))
                .body(download);
    }
}
