package com.example.agendaContactos.servicio;

import com.example.agendaContactos.entidad.Contacto;
import com.example.agendaContactos.repositorio.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpContactoServicio  implements ContactoServicio{

    @Autowired
    private ContactoRepositorio contactoRepositorio;

    @Override
    public List<Contacto> listarTodosLosContactos() {
        return contactoRepositorio.findAll();
    }

    @Override
    public Contacto guardarContacto(Contacto contacto) {
        return contactoRepositorio.save(contacto);
    }

    @Override
    public Contacto obtenerContactoPorId(Integer id) {
        return contactoRepositorio.findById(id).get();
    }

    @Override
    public Contacto actualizarContacto(Contacto contacto) {
        return contactoRepositorio.save(contacto);
    }

    @Override
    public void eliminar(Contacto contacto) {
        contactoRepositorio.delete(contacto);
    }

    @Override
    public List<Contacto> listAll(String palabraClave) {
        if(palabraClave != null){
            return contactoRepositorio.findAll(palabraClave);
        }
        return contactoRepositorio.findAll();
    }
}
