package com.praktic.Shish.Repository;

import com.praktic.Shish.DTO.AnimalDTO;
import com.praktic.Shish.Interface.ICrudRepository;
import com.praktic.Shish.Model.Animal;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AnimalRepository implements ICrudRepository<Animal, AnimalDTO> {

    private AtomicInteger idCounter = new AtomicInteger(1);
    private final ArrayList<Animal> animals = new ArrayList<>();

    @Override
    public ArrayList<Animal> GetAll() {
        return animals;
    }

    @Nullable
    @Override
    public Animal GetByID(int ID) {
        return animals.stream()
                .filter(animal -> animal.getID() == ID)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean Create(AnimalDTO model) {
        Animal _animal = animals.stream()
                .filter(animal -> animal.getType().equals(model.getType()))
                .findFirst()
                .orElse(null);

        if(_animal == null) {
            int ID = idCounter.getAndIncrement();

            Animal animal = new Animal(ID, model.getName(), model.getType());
            animals.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean Update(int ID, AnimalDTO model) {
        for(int i = 0; i<animals.size();i++)
        {
            Animal animal = animals.get(i);
            if(animal.getID() == ID)
            {
                animal.setType(model.getType());
                animal.setName(model.getName());
                animals.set(i, animal);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean Delete(int ID) {
        Animal _animal = GetByID(ID);

        if(_animal != null)
        {
            if(_animal.isDeleted())
            {
                return animals.removeIf(animal -> animal.getID() == ID);
            }
            else
            {
                _animal.setDeleted(true);
            }
        }
        return false;
    }
}
