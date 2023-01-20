package ua.nix.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nix.project.repository.entity.PhotoEntity;

import java.util.Set;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    Set<PhotoEntity> findPhotoEntityByDescription(String description);
}
