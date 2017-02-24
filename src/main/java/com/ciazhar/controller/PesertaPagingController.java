package com.ciazhar.controllers;


import com.ciazhar.dao.PesertaPagingDao;
import com.ciazhar.model.PesertaPaging;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class PesertaPagingController {

    @Autowired private PesertaPagingDao pesertaPagingDao;

    @RequestMapping("/peserta/registrasi/")
    public void registrasi(){}

      @RequestMapping(value = "/api/peserta/", method = RequestMethod.GET)
      @ResponseBody
      public Page<PesertaPaging> semuaPeserta(Pageable page){
          return pesertaPagingDao.findAll(page);
      }

      @RequestMapping(value = "/api/peserta/", method = RequestMethod.POST)
      @ResponseStatus(HttpStatus.CREATED)
      public void simpanPeserta(@RequestBody @Valid PesertaPaging p){
           pesertaPagingDao.save(p);
      }

      @RequestMapping("/api/peserta/{id}/")
      @ResponseBody
      public PesertaPaging cariById(@PathVariable(name = "id") PesertaPaging peserta){
          return peserta;
      }

    @RequestMapping("/peserta/list/")
    public ModelMap daftarPeserta(Pageable page){
        ModelMap data = new ModelMap();
        data.put("daftarPeserta", pesertaPagingDao.findAll(page));
        return data;
    }
    @RequestMapping(value="/peserta/form", method = RequestMethod.GET)
    public ModelMap tampilkanForm(@RequestParam(required = false, name = "id") PesertaPaging p){
        ModelMap data = new ModelMap();

        if(p == null){
            p = new PesertaPaging();
        }

        data.put("peserta", p);

        return data;
    }
    @RequestMapping(value = "/peserta/form", method = RequestMethod.POST)
    public String prosesForm(@ModelAttribute @Valid PesertaPaging p, BindingResult errors, SessionStatus status){
        if(errors.hasErrors()) {
            return "/peserta/form";
        }
        pesertaPagingDao.save(p);
        status.setComplete();
        return "redirect:/peserta/list/";
    }
}
