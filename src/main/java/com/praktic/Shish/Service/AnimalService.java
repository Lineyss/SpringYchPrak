package com.praktic.Shish.Service;

import com.praktic.Shish.DTO.AnimalDTO;
import com.praktic.Shish.Interface.IAnimalService;
import com.praktic.Shish.Interface.ICrudRepository;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class AnimalService implements IAnimalService {

    @Autowired
    private ICrudRepository<Animal, AnimalDTO> animalRepository;

    @Override
    public Pagination<Animal> GetAll(int page, @Nullable String Name, @Nullable String Type) {
        ArrayList<Animal> animals = animalRepository.GetAll()
                .stream()
                .filter(element -> !element.isDeleted())
                .collect(Collectors.toCollection(ArrayList::new));

        if (Type != null) {
            animals = animals.stream()
                    .filter(animal -> animal.getType().equalsIgnoreCase(Type))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if (Name != null) {
            animals = animals.stream()
                    .filter(animal -> animal.getName().equalsIgnoreCase(Name))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return new Pagination<Animal>(animals, page);
    }

    @Nullable
    @Override
    public Animal GetByID(int ID) {
        return animalRepository.GetByID(ID);
    }

    @Override
    public boolean Create(AnimalDTO peopleDTO) {
        return animalRepository.Create(peopleDTO);
    }

    @Override
    public boolean Update(int ID, AnimalDTO peopleDTO) {
        return animalRepository.Update(ID,peopleDTO);
    }

    @Override
    public boolean Delete(int ID) {
        return animalRepository.Delete(ID);
    }

    @Override
    public HashSet<String> GetAllCategory() {
        HashSet<String> hashSet = new HashSet<>();

        for(Animal animal : animalRepository.GetAll())
        {
            hashSet.add(animal.getType());
        }

        return hashSet;
    }
}
