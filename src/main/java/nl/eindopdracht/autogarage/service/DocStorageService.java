package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Doc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocStorageService {

    Doc saveFile(MultipartFile file);

    Optional<Doc> getFile(Long fileId);

    List<Doc> getFiles();
}

