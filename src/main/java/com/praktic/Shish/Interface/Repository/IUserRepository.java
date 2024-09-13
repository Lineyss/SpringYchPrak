package com.praktic.Shish.Interface.Repository;

import com.praktic.Shish.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
