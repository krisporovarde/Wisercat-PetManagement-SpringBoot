package com.example.PetManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class PetManagementApplication {

	private final PetRepository petRepository;

	public PetManagementApplication(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@CrossOrigin("http://localhost:4200/")
	@GetMapping("/findAllPets")
	public List<Pet> getPets() {
		return petRepository.findAll();
	}

	@CrossOrigin("http://localhost:4200/")
	@PostMapping("/add")
	public void addPet(@RequestBody Pet pet) {
		petRepository.save(pet);
	}


	@CrossOrigin("http://localhost:4200/")
	@GetMapping("/findPetById/{id}")
	public Pet getPetById(@PathVariable Integer id) {
		return petRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Pet not found"));
	}

	@CrossOrigin("http://localhost:4200/")
	@PutMapping("/updatePet/{id}")
	public void updatePet(@PathVariable Integer id, @RequestBody Pet updatedPet) {
		Pet petToUpdate = petRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Pet not found"));

		petToUpdate.setName(updatedPet.getName());
		petToUpdate.setCode(updatedPet.getCode());
		petToUpdate.setType(updatedPet.getType());
		petToUpdate.setFurcolor(updatedPet.getFurcolor());
		petToUpdate.setCountry(updatedPet.getCountry());

		petRepository.save(petToUpdate);
	}

	public static void main(String[] args) {
		SpringApplication.run(PetManagementApplication.class, args);
	}

}
