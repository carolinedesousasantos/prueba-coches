package com.kettal.cars.dto.form;

import com.kettal.cars.model.Cars;
import com.kettal.cars.model.Colors;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class FormUpdateCar {
  @NotNull
  @NotEmpty
  @Length(min = 3)
  private String marca;

  @NotNull
  @NotEmpty
  @Length(min = 3)
  private String modelo;

  @NotNull @NotEmpty private String color;

  private boolean disponible;

  @Min(value = 0)
  @Max(value = 1000)
  private Long cantidad;

  @Min(value = 0)
  @Max(value = 100000)
  private Float precio;

  public FormUpdateCar(
      String marca, String modelo, String color, boolean disponible, Long cantidad, Float precio) {
    this.marca = marca;
    this.modelo = modelo;
    this.color = color;
    this.disponible = disponible;
    this.cantidad = cantidad;
    this.precio = precio;
  }

  public Cars update(Cars car) {
    car.setMarca(this.marca);
    car.setModelo(this.modelo);
    car.setColor(Colors.valueOf(this.color));
    car.setDisponible(this.disponible);
    car.setCantidad(this.cantidad);
    car.setPrecio(this.precio);
    return car;
  }
}
