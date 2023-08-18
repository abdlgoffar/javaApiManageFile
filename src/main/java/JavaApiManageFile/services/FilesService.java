package JavaApiManageFile.services;

import JavaApiManageFile.entities.Files;
import JavaApiManageFile.repositories.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FilesService {
    private FilesRepository filesRepository;
    @Autowired
    public void setFilesRepository(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }
    private final String path = "C:/Users/abd.goffar/Documents/";
    public String upload(MultipartFile multipartFile) throws IOException {
        Files files = new Files(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                this.path + multipartFile.getOriginalFilename());
        Files saveFiles = this.filesRepository.save(files);//save data images to database
        multipartFile.transferTo(new File(this.path + multipartFile.getOriginalFilename()));//save data images to hdd server
        if (saveFiles != null) return "uploaded file successfully";
        return null;
    }
    public byte[] download(String name) throws IOException {
        Optional<Files> byName = this.filesRepository.findByName(name);
        byte[] img = java.nio.file.Files.readAllBytes(new File(byName.get().getPath()).toPath());
        return img;
    }
}
