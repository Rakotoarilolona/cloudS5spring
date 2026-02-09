package com.carte.clouds5spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.carte.clouds5spring.dto.PhotoDto;
import com.carte.clouds5spring.entity.Photo;
import com.carte.clouds5spring.repository.PhotoRepository;


@Service
public class PhotoService 
{
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<PhotoDto> getPhotosBySignalement(Integer routeProblemeId) {

        return photoRepository.findByRouteProblemeId(routeProblemeId)
                .stream()
                .map(photo -> PhotoDto.fromEntity(photo, true)) // ✅ bytes inclus
                .toList();
    }
}
