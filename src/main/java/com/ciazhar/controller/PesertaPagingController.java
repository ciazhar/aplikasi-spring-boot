package com.ciazhar.controllers;


import com.ciazhar.dao.PesertaPagingDao;
import com.ciazhar.model.PesertaPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PesertaPagingController {

    @Autowired private PesertaPagingDao pesertaPagingDao;

    @RequestMapping("/peserta/registrasi")
    public void registrasi(){}

    @RequestMapping("/api/peserta")
    @ResponseBody
    public Page<PesertaPaging> semuaPeserta(Pageable page){
        return pesertaPagingDao.findAll(page);
    }

    @RequestMapping("/api/peserta/{id}")
    @ResponseBody
    public PesertaPaging cariById(@PathVariable(name = "id") PesertaPaging peserta){
        return peserta;
    }

    @RequestMapping("/peserta/list")
    public ModelMap daftarPeserta(Pageable page){
        ModelMap data = new ModelMap();
        data.put("daftarPeserta", pesertaPagingDao.findAll(page));
        return data;
    }
}
