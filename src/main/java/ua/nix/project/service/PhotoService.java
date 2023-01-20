package ua.nix.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nix.project.repository.PhotoRepository;
import ua.nix.project.repository.StudentRepository;
import ua.nix.project.repository.entity.PhotoEntity;
import ua.nix.project.repository.entity.StudentEntity;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final StudentRepository studentRepository;

    public void createPhoto(String url, String description, long studentId) {

        PhotoEntity photo = new PhotoEntity();
        photo.setUrl(url);
        photo.setDescription(description);
        photo.setStudent(studentRepository.findById(studentId).orElseThrow());

        photoRepository.save(photo);
    }

    public Set<PhotoEntity> findPhotoByDescription(String description){
        return photoRepository.findPhotoEntityByDescription(description);

    }
}
