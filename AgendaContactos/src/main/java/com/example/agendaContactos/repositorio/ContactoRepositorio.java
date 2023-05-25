//Revisado

package com.example.agendaContactos.repositorio;

import com.example.agendaContactos.entidad.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepositorio extends JpaRepository<Contacto, Integer> {
}
