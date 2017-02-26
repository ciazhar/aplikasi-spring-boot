package com.ciazhar.controller;

import com.ciazhar.dao.PesertaPagingDao;
import com.ciazhar.model.PesertaPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by ciazhar on 2/26/17.
 */
@Controller
@RequestMapping("/peserta")
public class PesertaPagingController {

    @Autowired
    PesertaPagingDao pesertaPagingDao;

    @RequestMapping("/list")
    public String listPeserta(ModelMap modelMap){
        modelMap.put("peserta",pesertaPagingDao.findAll());
        return "/peserta/list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public void tampilkanForm(
            @RequestParam(required = false, name = "id") PesertaPaging peserta,
            ModelMap mm){
        if(peserta != null){
            mm.addAttribute("peserta", peserta);
        } else {
            mm.addAttribute("peserta", new PesertaPaging());
        }
    }
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String prosesForm(@ModelAttribute @Valid PesertaPaging p, BindingResult hasilValidasi){
        if(hasilValidasi.hasErrors()){
            return "/peserta/form";
        }
        pesertaPagingDao.save(p);
        return "redirect:list";
    }
}
