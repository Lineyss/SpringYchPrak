package com.praktic.Shish.Interface.Repository;

import com.praktic.Shish.Model.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeopleRepository extends JpaRepository<People, Long> {
}
