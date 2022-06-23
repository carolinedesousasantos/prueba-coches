package com.kettal.cars.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cars {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String marca;

  private String modelo;

  @Enumerated(EnumType.STRING)
  private Colors color;

  private boolean disponible;

  private Long cantidad;

  private Float precio;

  @Column(name = "fecha_creacion")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime fechaCreacion;

  public Cars(
      String marca,
      String modelo,
      Colors color,
      boolean disponible,
      Long cantidad,
      Float precio,
      LocalDateTime fechaCreacion) {
    this.marca = marca;
    this.modelo = modelo;
    this.color = color;
    this.disponible = disponible;
    this.cantidad = cantidad;
    this.precio = precio;
    this.fechaCreacion = fechaCreacion;
  }
}
