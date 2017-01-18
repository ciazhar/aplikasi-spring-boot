package com.ciazhar.controller;

import com.ciazhar.dao.PesertaDao;
import com.ciazhar.model.Peserta;
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
        return "/peserta/listPeserta";
    }

    @RequestMapping(value = "/peserta/create", method = RequestMethod.GET)
    public String tampilkanForm(Model model){
        model.addAttribute("peserta",new Peserta());
        return "/peserta/formPeserta";
    }

    @RequestMapping(value = "/peserta/create", method = RequestMethod.POST)
    public String saveForm(Model model, Peserta peserta){
        model.addAttribute("peserta", pesertaDao.saveOrUpdate(peserta));
        return "redirect:/peserta";
    }
    @RequestMapping(value = "/peserta/edit/{id}",method = RequestMethod.GET)
    public String editForm(@PathVariable String id, Model model){
        model.addAttribute("peserta",pesertaDao.getIdPeserta(id));
        return "/peserta/formPeserta";
    }
}
