package com.praktic.Shish.Interface.Repository;

import com.praktic.Shish.Model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnimalRepository extends JpaRepository<Animal, Long> {
}
