package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.dto.FurnitureDTO;
import com.example.demo.entities.Furniture;
import com.example.demo.repositories.FurnitureRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class FurnitureService {

    private FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public Furniture save(FurnitureDTO furnitureDTO) {

        Furniture furniture = Furniture.builder()
                .price(furnitureDTO.getPrice())
                .description(furnitureDTO.getDescription())
                .width(furnitureDTO.getWidth())
                .length(furnitureDTO.getLength()).furnitureType(furnitureDTO.getFurnitureType()).build();
        return furnitureRepository.save(furniture);
    }

    public Furniture updatFurniture(Furniture furniture) {

        return furnitureRepository.save(furniture);

    }

    public List<Furniture> retrieveAllFurniture() {
        return furnitureRepository.findAll();
    }

    public Furniture retrieveFurnitureById(Long furnitureId) {
        return furnitureRepository.findById(furnitureId).orElse(null);
    }

    public void deleteFurniture(Long furnitureId) {
        furnitureRepository.deleteById(furnitureId);
    }

}
