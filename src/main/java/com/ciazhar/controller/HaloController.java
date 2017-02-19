package com.ciazhar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
Anotasi Controller digunakan untuk menandai bahwa class itu controller
*/
@Controller
public class HaloController{
  ///mapping url
  @RequestMapping("/")
  public String halo(){
    return "index";///return ke view-nya, yaitu index.html
  }
}
