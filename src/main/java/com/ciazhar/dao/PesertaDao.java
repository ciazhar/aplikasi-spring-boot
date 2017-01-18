package com.ciazhar.dao;

import com.ciazhar.model.Peserta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PesertaDao{
    List <Peserta> daftarPeserta();
    Peserta saveOrUpdate (Peserta peserta);
    Peserta getIdPeserta(String id);
    void delete(String id);
}
