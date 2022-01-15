package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Doc;
import nl.eindopdracht.autogarage.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DocStorageServiceImpl implements DocStorageService {

    @Autowired
    private DocRepository docRepository;

    @Override
    public Doc saveFile(MultipartFile file) {
        String docName = file.getOriginalFilename();
        try {
            Doc doc = new Doc(docName, file.getContentType(), file.getBytes());
            return docRepository.save(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Doc> getFile(Long fileId) {
        return docRepository.findById(fileId);
    }

    @Override
    public List<Doc> getFiles() {
        return docRepository.findAll();
    }
}

