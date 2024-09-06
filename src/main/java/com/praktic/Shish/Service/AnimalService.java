package com.praktic.Shish.Service;

import com.praktic.Shish.DTO.AnimalDTO;
import com.praktic.Shish.Interface.AService;
import com.praktic.Shish.Interface.IAnimalRepository;
import com.praktic.Shish.Interface.IAnimalService;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class AnimalService
        extends AService<Animal, AnimalDTO>
        implements IAnimalService {

    @Autowired
    public AnimalService(IAnimalRepository repository) {
        super(repository);
    }

    @Override
    public Pagination<Animal> GetAll(int page, @Nullable String Name, @Nullable String Type, @Nullable Boolean IsDeleted) {
        ArrayList<Animal> animals = new ArrayList<>(repository.findAll());

        if(IsDeleted != null) {
            animals = animals.stream()
                    .filter(animal -> animal.isDeleted() == IsDeleted)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

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

    @Override
    public boolean Delete(Long ID) {
        Animal findAnimal = GetByID(ID);
        if(findAnimal != null)
        {
            if(findAnimal.isDeleted())
            {
                repository.deleteById(ID);
            }
            else
            {
                findAnimal.setDeleted(true);
                repository.save(findAnimal);
            }
        }
        return true;
    }

    @Override
    public HashSet<String> GetAllCategory() {
        HashSet<String> hashSet = new HashSet<>();

        for(Animal animal : repository.findAll())
        {
            hashSet.add(animal.getType());
        }

        return hashSet;
    }

    @Override
    protected Animal ConvertDTOtoEntity(AnimalDTO animalDTO) {
        return new Animal(animalDTO.getName(), animalDTO.getType());
    }

    @Override
    protected Animal UpdateDTOtoEntity(Animal animal, AnimalDTO animalDTO) {
        animal.setType(animalDTO.getType());
        animal.setName(animalDTO.getName());
        return animal;
    }

    @Override
    protected Class<Animal> getEntityClass() {
        return Animal.class;
    }
}
