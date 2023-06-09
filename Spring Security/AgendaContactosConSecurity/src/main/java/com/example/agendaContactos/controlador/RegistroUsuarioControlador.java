package com.example.agendaContactos.controlador;

import com.example.agendaContactos.dto.UsuarioRegistroDTO;
import com.example.agendaContactos.entidad.Usuario;
import com.example.agendaContactos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO(){
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro(){
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@Valid @ModelAttribute("usuario") UsuarioRegistroDTO registroDTO,
                                           BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "registro"; // Vuelve a la página de registro si hay errores de validación
        }

        usuarioServicio.guardar(registroDTO);
        return "redirect:/registro?exito";
    }

}
