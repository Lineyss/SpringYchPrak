package com.praktic.Shish.Interface;

import com.praktic.Shish.DTO.AnimalDTO;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import jakarta.annotation.Nullable;

import java.util.HashSet;

public interface IAnimalService {
    Pagination<Animal> GetAll(int page,  @Nullable String Name, @Nullable String Type, @Nullable Boolean IsDeleted);
    HashSet<String> GetAllCategory();
}
