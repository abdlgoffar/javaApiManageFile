package JavaApiManageFile.services;

import JavaApiManageFile.entities.Images;
import JavaApiManageFile.helpers.ZipConversion;
import JavaApiManageFile.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImagesServices {
    private ImagesRepository imagesRepository;
    @Autowired
    public void setImagesRepository(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }
    public String upload(MultipartFile multipartFile) throws IOException {
        Images images = new Images(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                ZipConversion.compress(multipartFile.getBytes()));
        Images saveImages = this.imagesRepository.save(images);
        if (saveImages != null) return "upload image " + multipartFile.getOriginalFilename() + " successfully";
        return null;
    }
    public byte[] download(String imageName) {
        Optional<Images> byName = this.imagesRepository.findByName(imageName);
        return ZipConversion.decompress(byName.get().getFile());
    }



}
