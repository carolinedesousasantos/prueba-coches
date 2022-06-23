package com.kettal.cars.controller;

import com.kettal.cars.model.Colors;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colors")
public class ColorsController {

  @GetMapping
  public List<Colors> getColors() {
    return new ArrayList<>(EnumSet.allOf(Colors.class));
  }
}
