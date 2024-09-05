package com.praktic.Shish.Interface;

import com.praktic.Shish.DTO.AnimalDTO;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import jakarta.annotation.Nullable;

import java.util.HashSet;

public interface IAnimalService {
    Pagination<Animal> GetAll(int page,  @Nullable String Name, @Nullable String Type);
    @Nullable
    Animal GetByID(int ID);
    boolean Create(AnimalDTO peopleDTO);
    boolean Update(int ID, AnimalDTO peopleDTO);
    boolean Delete(int ID);
    HashSet<String> GetAllCategory();
}
