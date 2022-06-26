package com.kettal.cars.controller;

import com.kettal.cars.dto.CarsDto;
import com.kettal.cars.dto.form.FormCar;
import com.kettal.cars.exceptions.*;
import com.kettal.cars.model.Cars;
import com.kettal.cars.service.CarsService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cars")
public class CarsController {

  @Autowired CarsService service;

  @PostMapping
  public ResponseEntity<CarsDto> create(
          @RequestBody @Valid FormCar form, UriComponentsBuilder uriBuilder)
      throws DateNotAcceptableException, ColorNotAcceptableException, PriceExceededException,
          QuantityExceededException {
    Cars carSaved = service.create(form);
    URI uri = uriBuilder.path("/cars/{id}").buildAndExpand(carSaved.getId()).toUri();
    return ResponseEntity.created(uri).body(new CarsDto(carSaved));
  }

  @GetMapping
  public List<CarsDto> read() {
    List<Cars> cars = service.read();
    return CarsDto.convert(cars);
  }

  @GetMapping(value = {"/{id}"})
  public CarsDto readById(@PathVariable Long id) throws CarNotFoundException {
    return service.readById(id);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<CarsDto> update(
      @PathVariable Long id, @RequestBody @Valid FormCar form)
      throws CarNotFoundException, PriceExceededException, ColorNotAcceptableException,
          QuantityExceededException {
    Cars carUpdated = service.update(id, form);
    return ResponseEntity.ok(new CarsDto(carUpdated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CarsDto> delete(@PathVariable Long id) throws CarNotFoundException {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
