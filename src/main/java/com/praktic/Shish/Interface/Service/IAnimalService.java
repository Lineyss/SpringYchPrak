package com.praktic.Shish.Interface.Service;

import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Help.Pagination;
import jakarta.annotation.Nullable;

import java.util.HashSet;

public interface IAnimalService {
    Pagination<Animal> GetAll(int page,  @Nullable String Name, @Nullable String Type, @Nullable Boolean IsDeleted);
    HashSet<String> GetAllCategory();
}
