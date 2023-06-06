package com.example.agendaContactos.controlador;

import com.example.agendaContactos.entidad.Contacto;
import com.example.agendaContactos.servicio.ContactoServicio;
import com.example.agendaContactos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoControlador {

    @Autowired
    private ContactoServicio contactoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String verPaginaDeInicio(Model modelo){
        List<Contacto> contactos = contactoServicio.listarTodosLosContactos();
        modelo.addAttribute("contactos", contactos);

        return "index";
    }

    @GetMapping("/new")
    public String mostrarFormularioRegistrarContacto(Model modelo){
        modelo.addAttribute("contacto", new Contacto());

        return "new";
    }

    @PostMapping("/save")
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult,
                                  RedirectAttributes redirect, Model modelo){

        if (bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "new";
        }

        contactoServicio.guardarContacto(contacto);

        redirect.addFlashAttribute("msgExito", "El contacto ha sido añadido exitosamente");

        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioEditarContacto(@PathVariable Integer id, Model modelo){
        Contacto contacto = contactoServicio.obtenerContactoPorId(id);
        modelo.addAttribute("contacto", contacto);

        return "update";
    }

    @PostMapping("/update/{id}")
    public String actualizarContacto(@PathVariable Integer id, @Validated Contacto contacto,
                                     BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {

        Contacto contactoDB = contactoServicio.obtenerContactoPorId(id);

        if (bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "update";
        }

        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setCelular(contacto.getCelular());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());

        contactoServicio.guardarContacto(contactoDB);

        redirect.addFlashAttribute("msgExito", "El contacto ha sido modificado exitosamente");

        return "redirect:/list";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirect){
        Contacto contacto = contactoServicio.obtenerContactoPorId(id);

        contactoServicio.eliminar(contacto);

        redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado exitosamente");

        return "redirect:/list";
    }

    @GetMapping("/search")
    public String verResultadoBusqueda(Model modelo, @Param("palabraClave") String palabraClave) {
        List<Contacto> contactos = contactoServicio.listAll(palabraClave);
        modelo.addAttribute("contactos", contactos);
        modelo.addAttribute("palabraClave", palabraClave);

        return "search";
    }





}
