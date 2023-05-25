//Revisado

package com.example.agendaContactos.servicio;

import com.example.agendaContactos.entidad.Contacto;

import java.util.List;

public interface ContactoServicio {

    public List<Contacto> listarTodosLosContactos();

    public Contacto guardarContacto(Contacto contacto);

    public Contacto obtenerContactoPorId(Integer Id);

    public Contacto actualizarContacto(Contacto contacto);

    public void eliminar(Contacto contacto);

}
