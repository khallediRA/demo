package com.example.demo.repositories;

import com.example.demo.entities.Furniture;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

}
