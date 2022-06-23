package com.kettal.cars.service;

import com.kettal.cars.dto.CarsDto;
import com.kettal.cars.dto.form.FormCreateCar;
import com.kettal.cars.dto.form.FormUpdateCar;
import com.kettal.cars.exceptions.*;
import com.kettal.cars.model.Cars;
import com.kettal.cars.model.Colors;
import com.kettal.cars.repository.CarsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CarsService {

  @Autowired CarsRepository repository;

  public List<Cars> read() {
    return repository.findAll();
  }

  public CarsDto readById(Long id) throws CarNotFoundException {
    Optional<Cars> car = repository.findById(id);
    if (car.isPresent()) {
      return new CarsDto(car.get());

    } else {
      throw new CarNotFoundException();
    }
  }

  public Cars create(FormCreateCar form)
      throws DateNotAcceptableException, ColorNotAcceptableException, PriceExceededException,
          QuantityExceededException {
    LocalDateTime now = LocalDateTime.now();

    if (now.getYear() < 2000) {
      throw new DateNotAcceptableException();
    }

    if (form.getPrecio() > 100000F) {
      throw new PriceExceededException();
    }

    if (form.getCantidad() > 1000) {
      throw new QuantityExceededException();
    }

    Colors color;
    try {
      color = Colors.valueOf(form.getColor());
    } catch (IllegalArgumentException e) {
      throw new ColorNotAcceptableException();
    }

    Cars car =
        new Cars(
            form.getMarca(),
            form.getModelo(),
            color,
            form.isDisponible(),
            form.getCantidad(),
            form.getPrecio(),
            now);

    return repository.save(car);
  }

  public Cars update(Long id, FormUpdateCar form)
      throws CarNotFoundException, ColorNotAcceptableException, PriceExceededException,
          QuantityExceededException {
    Optional<Cars> optionalCar = repository.findById(id);
    if (optionalCar.isPresent()) {

      if (form.getPrecio() > 100000F) {
        throw new PriceExceededException();
      }

      if (form.getCantidad() > 1000) {
        throw new QuantityExceededException();
      }

      try {
        Colors.valueOf(form.getColor());
      } catch (IllegalArgumentException e) {
        throw new ColorNotAcceptableException();
      }

      return form.update(optionalCar.get());
    } else {
      throw new CarNotFoundException();
    }
  }

  public void delete(Long id) throws CarNotFoundException {
    Optional<Cars> car = repository.findById(id);
    if (car.isPresent()) {
      repository.delete(car.get());
    } else {
      throw new CarNotFoundException();
    }
  }
}
