package com.ciazhar.model;

import com.ciazhar.dao.MateriDao;
import com.ciazhar.model.Materi;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MateriApiController {
    @Autowired private MateriDao md;

    @RequestMapping(value = "/materi", method = RequestMethod.GET)
    public Page<Materi> daftarMateri(Pageable page){
        return md.findAll(page);
    }

    @RequestMapping(value="/materi/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusMateri(@PathVariable("id") String id){
        md.delete(id);
    }

    @RequestMapping(value="/materi", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertMateriBaru(@RequestBody @Valid Materi m){
        md.save(m);
    }
}
