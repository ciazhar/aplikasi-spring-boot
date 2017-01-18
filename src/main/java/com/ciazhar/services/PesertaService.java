package com.ciazhar.services;

import com.ciazhar.dao.PesertaDao;
import com.ciazhar.model.Peserta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/*
Anotasi Service digunakan untuk menandai bahwa class itu service
*/
@Service
public class PesertaService implements PesertaDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;



    @Override
    public List<Peserta> daftarPeserta() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from Peserta", Peserta.class).getResultList();
    }

    @Override
    public Peserta saveOrUpdate(Peserta peserta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Peserta saved = entityManager.merge(peserta);///merge berfungsi untuk mengecek apakah data udah ada di database, jika belum create, jika sudah update
        entityManager.getTransaction().commit();
        return saved;
    }

    @Override
    public Peserta getIdPeserta(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Peserta.class,id);

    }
}
