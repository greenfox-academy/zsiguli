package com.greenfox.controller;

import com.greenfox.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {

  @Autowired
  private ErrorMessage errorMessage;

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ErrorMessage exceptionHandling(MissingServletRequestParameterException ex) {
    errorMessage.setError(createErrorMessage(ex.getParameterName()));
    return errorMessage;
  }

  private String createErrorMessage(String error) {
    String errorMessage = "Please provide ";
    if (isVowel(error.charAt(0))) {
      errorMessage += "an ";
    } else {
      errorMessage += "a ";
    }
    errorMessage += error + "!";
    return errorMessage;
  }

  private boolean isVowel(char c) {
    if (c == 'a') {
      return true;
    } else if (c == 'e') {
      return true;
    } else if (c == 'i') {
      return true;
    } else if (c == 'o') {
      return true;
    } else if (c == 'u') {
      return true;
    } else return false;
  }

  @Autowired
  private DoubleService doubleService;

  @GetMapping("/doubling")
  public DoubleService doubling(@RequestParam("input") int inputNumber) {
    doubleService.setReceived(inputNumber);
    doubleService.setResult(inputNumber * 2);
    return doubleService;
  }

  @Autowired
  Greeting greeting;

  @GetMapping("/greeter")
  public Greeting greet(@RequestParam("name") String name, @RequestParam("title") String title) {
    return greeting;
  }

  @Autowired
  AppendA appendA;

  @GetMapping("/appenda/{appendable}")
  public AppendA appendA(@PathVariable String appendable) {
    appendA.setAppended(appendable);
    return appendA;
  }

  @Autowired
  DoUntilError doUntilError;

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public DoUntilError error() {
    return doUntilError;
  }

  @Autowired
  DoUntil doUntil;

  @PostMapping("/dountil/{what}")
  public DoUntil doUntil(@PathVariable String what, @RequestBody Until until) {
    if (until.getUntil() == 0) {

    }
    int result;
    if (what.equals("sum")) {
      result = sum(until.getUntil());
    } else if (what.equals("factor")) {
      result = factorio(until.getUntil());
    } else {
      System.out.println("error");
      result = 0;
    }
    doUntil.setResult(result);
    return doUntil;
  }

  private int sum(int num) {
    if (num < 1) {
      return num;
    } else {
      return num + sum(num - 1);
    }
  }

  private int factorio(int num) {
    if (num < 2) {
      return num;
    } else {
      return num * factorio(num - 1);
    }
  }
}
