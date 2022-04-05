package com.example.demo.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import com.example.demo.dto.FurnitureDTO;
import com.example.demo.entities.Furniture;
import com.example.demo.services.FurnitureService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/furniture")
public class FurnitureController {

    private FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @PostMapping("/create-new-furniture")
    public ResponseEntity<Furniture> createNewFurniture(@RequestBody FurnitureDTO furnitureDTO) {
        if (furnitureDTO.getFurnitureType() == null || furnitureDTO.getDescription() == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Furniture furniture = furnitureService.save(furnitureDTO);
        return new ResponseEntity<Furniture>(furniture, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-furnitures")
    public List<Furniture> getAllFurnitures() {

        return furnitureService.retrieveAllFurniture();
    }

    @GetMapping("/get-furniture-by-id/{furnitureId}")
    public ResponseEntity<Furniture> getFurnitureById(@PathParam("furnitureId") Long furnitureId) {
        return new ResponseEntity<Furniture>(furnitureService.retrieveFurnitureById(furnitureId), HttpStatus.OK);
    }

    @DeleteMapping("/deletefurniture/{furnitureId}")
    public ResponseEntity<String> deleteFurnitureById(@PathParam("furnitureId") Long furnitureId) {

        if (furnitureService.retrieveFurnitureById(furnitureId) == null) {
            return new ResponseEntity<String>("The furniture was not found", HttpStatus.NOT_FOUND);

        }

        furnitureService.deleteFurniture(furnitureId);
        return new ResponseEntity<String>("The furniture was succesfully deleted", HttpStatus.OK);

    }

    @PutMapping("/update-furniture")
    public ResponseEntity<Furniture> updateFurniture(@RequestBody Furniture furniture) {

        return new ResponseEntity<Furniture>(furnitureService.updatFurniture(furniture), HttpStatus.OK);

    }
}
