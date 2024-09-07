package com.praktic.Shish.Interface.Repository;

import com.praktic.Shish.Model.Collar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICollarRepository extends JpaRepository<Collar, Long> {
}
