package com.example.agendaContactos.repositorio;

import com.example.agendaContactos.entidad.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepositorio extends JpaRepository<Contacto, Integer> {

    @Query("SELECT c FROM Contacto c WHERE c.nombre LIKE %?1% OR c.celular LIKE %?1% OR c.email LIKE %?1%")
    public List<Contacto> findAll(String palabraClave);
}
