package com.ciazhar.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HaloController{
  @RequestMapping("/")
  public String halo(){
    return "index";
  }
}
