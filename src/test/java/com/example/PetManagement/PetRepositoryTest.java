package com.example.PetManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Test
    void test1(){
        petRepository.save(new Pet(4, "202211151537", "Bosse", "Dog", "Black", "Estonia"));

        petRepository.findAll().forEach(System.out::println);
    }
}