package com.kettal.cars.repository;

import com.kettal.cars.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars, Long> {}
