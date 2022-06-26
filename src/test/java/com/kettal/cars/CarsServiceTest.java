package com.kettal.cars;

import com.kettal.cars.dto.form.FormCar;
import com.kettal.cars.exceptions.*;
import com.kettal.cars.model.Cars;
import com.kettal.cars.service.CarsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = CarsApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarsServiceTest {

  @Autowired CarsService service;

  @Test
  void createCarSuccess()
      throws DateNotAcceptableException, ColorNotAcceptableException, PriceExceededException,
          QuantityExceededException {
    int sizeBefore = service.read().size();
    FormCar form = fillForm("Marca1", "Modelo1", "AZUL", true, 100L, 8000.50F);
    service.create(form);
    int sizeAfter = service.read().size();

    Assertions.assertEquals(sizeBefore + 1, sizeAfter);
  }

  @Test
  void createCarColorNotAccepted() {
    Assertions.assertThrows(
        ColorNotAcceptableException.class,
        () -> {
            FormCar form =
              fillForm("Marca1", "Modelo1", "AMARILLO", true, 100L, 8000.50F);
          service.create(form);
        });
  }

  @Test
  void createCarPriceExceeded() {
    Assertions.assertThrows(
        PriceExceededException.class,
        () -> {
            FormCar form =
              fillForm("Marca1", "Modelo1", "AZUL", true, 100L, 100000.01F);
          service.create(form);
        });
  }

  @Test
  void createCarQuantityExceeded() {
    Assertions.assertThrows(
        QuantityExceededException.class,
        () -> {
            FormCar form =
              fillForm("Marca1", "Modelo1", "AZUL", true, 10000L, 100000.00F);
          service.create(form);
        });
  }

  @Test
  void carNotFoundException() {
    Assertions.assertThrows(
        CarNotFoundException.class,
        () -> {
            FormCar form =
              fillForm("Marca1", "Modelo1", "AZUL", true, 10L, 100000.00F);
          service.create(form);
          service.readById(-10L);
        });
  }

  @Test
  void update()
      throws DateNotAcceptableException, PriceExceededException, ColorNotAcceptableException,
          QuantityExceededException, CarNotFoundException {

    FormCar formCreate = fillForm("Marca1", "Modelo1", "AZUL", true, 100L, 8000.50F);
    Cars car = service.create(formCreate);

    FormCar formUpdateCar =
        new FormCar("Marca actualizada", "Modelo1", "AZUL", true, 100L, 8000.50F);
    Cars carUpdated = service.update(car.getId(), formUpdateCar);
    Assertions.assertEquals("Marca actualizada", carUpdated.getMarca());
  }

  @Test
  void delete()
      throws DateNotAcceptableException, PriceExceededException, ColorNotAcceptableException,
          QuantityExceededException, CarNotFoundException {
      FormCar formCreate = fillForm("Marca1", "Modelo1", "AZUL", true, 100L, 8000.50F);
    int sizeBeforeCreate = service.read().size();
    Cars car = service.create(formCreate);
    service.delete(car.getId());
    int sizeAfterDelete = service.read().size();

    Assertions.assertEquals(sizeAfterDelete, sizeBeforeCreate);
  }

  public FormCar fillForm(
      String marca, String modelo, String color, boolean disponible, Long cantidad, Float precio) {
    return new FormCar(marca, modelo, color, disponible, cantidad, precio);
  }
}
