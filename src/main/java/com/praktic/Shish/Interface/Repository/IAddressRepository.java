package com.praktic.Shish.Interface.Repository;

import com.praktic.Shish.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
