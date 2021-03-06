package com.greenfox.controllers;

import com.greenfox.model.Drink;
import com.greenfox.model.Food;
import com.greenfox.model.Fox;
import com.greenfox.model.TrickList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  @Autowired
  Fox fox;
  @Autowired
  TrickList availableTricks;

  @GetMapping(value = "/")
  public String homePage(Model model) {
    model.addAttribute("fox", fox);
    return "index";
  }

  @GetMapping(value = "/nutritionStore")
  public String nutritionStore(Model model) {
    model.addAttribute("foods", Food.values());
    model.addAttribute("drinks", Drink.values());
    model.addAttribute("fox", fox);
    return "nutritionStore";
  }

  @GetMapping(value = "/operation")
  public String operation(Model model) {
    model.addAttribute("fox", fox);
    return "operation";
  }

  @GetMapping(value = "/trickCenter")
  public String tricks(Model model) {
    model.addAttribute("fox", fox);
    model.addAttribute("tricks", availableTricks.getTricks());
    return "trickCenter";
  }

  @GetMapping(value = "/hack")
  public String initHack(Model model) {
    model.addAttribute("fox", fox);
    return "hack";
  }

  @PostMapping(value = "/hack")
  public String hack(Model model) throws Exception{
    model.addAttribute("fox", fox);
    fox.hack();
    return "redirect:/hack";
  }

  @GetMapping(value = "/fakeHack")
  public String fakeHack() {
    return "fakeHack";
  }
}
