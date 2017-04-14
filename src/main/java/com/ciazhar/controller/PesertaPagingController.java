package com.ciazhar.controller;

import com.ciazhar.dao.PesertaPagingDao;
import com.ciazhar.model.Peserta;
import com.ciazhar.model.PesertaPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ciazhar on 2/26/17.
 */
@Controller
@RequestMapping("/peserta")
public class PesertaPagingController {

    @Autowired
    PesertaPagingDao pesertaPagingDao;


    @RequestMapping(value = "/api", method = RequestMethod.GET)
    @ResponseBody
    public Page<PesertaPaging> daftarPeserta(Pageable page){
        return pesertaPagingDao.findAll(page);
    }

    @RequestMapping(value="/api/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusPeserta(@PathVariable("id") String id){
        pesertaPagingDao.delete(id);
    }

    @RequestMapping(value="/api", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertPesertaBaru(@RequestBody @Valid PesertaPaging m){
        pesertaPagingDao.save(m);
    }

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
    @RequestMapping(value = "/hapus")
    public String hapusPeserta(@RequestParam(name = "id")String id,ModelMap modelMap){
        pesertaPagingDao.delete(id);
        return "redirect:list";
    }
}
