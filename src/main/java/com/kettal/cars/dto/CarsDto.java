package com.kettal.cars.dto;

import com.kettal.cars.model.Cars;
import com.kettal.cars.model.Colors;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarsDto {

  private Long id;

  private String marca;

  private String modelo;

  private Colors color;

  private boolean disponible;

  private Long cantidad;

  private Float precio;

  private LocalDateTime fechaCreacion;

  public CarsDto(Cars car) {
    this.id = car.getId();
    this.marca = car.getMarca();
    this.modelo = car.getModelo();
    this.color = car.getColor();
    this.disponible = car.isDisponible();
    this.cantidad = car.getCantidad();
    this.precio = car.getPrecio();
    this.fechaCreacion = car.getFechaCreacion();
  }

  public static List<CarsDto> convert(List<Cars> car) {
    return car.stream().map(CarsDto::new).collect(Collectors.toList());
  }
}
