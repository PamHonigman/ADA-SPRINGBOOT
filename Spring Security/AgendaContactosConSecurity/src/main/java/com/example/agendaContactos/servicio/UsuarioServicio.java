package com.example.agendaContactos.servicio;

import com.example.agendaContactos.dto.UsuarioRegistroDTO;
import com.example.agendaContactos.entidad.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();
}
