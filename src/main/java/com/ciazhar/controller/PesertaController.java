package com.ciazhar.controller;

import com.ciazhar.dao.PesertaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
Anotasi Controller digunakan untuk menandai bahwa class itu controller
*/
@Controller
public class PesertaController{

    @Autowired
    private PesertaDao pesertaDao;

    @RequestMapping("/peserta")
    public String listPeserta(Model model){
        model.addAttribute("peserta", pesertaDao.daftarPeserta());
        return "/peserta/peserta";
    }
}
