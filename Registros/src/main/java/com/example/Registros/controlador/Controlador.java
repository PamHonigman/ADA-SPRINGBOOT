package com.example.Registros.controlador;

import com.example.Registros.entidad.Persona;
import com.example.Registros.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Controlador {

    @Autowired
    private PersonaServicio servicio;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String listar(Model model){

        List<Persona> personas = servicio.listarTodasLasPersonas();

        model.addAttribute("personas", personas);

        return "index";
    }

    @GetMapping("/new")
    public String agregar(Model model){
        Persona persona = new Persona();
        model.addAttribute("persona", persona);
        return "create_persona";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute("persona") Persona persona){
        servicio.guardarPersona(persona);
        return "redirect:/list";
    }

    @GetMapping("/update_persona/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Integer id, Model model){

        model.addAttribute("persona", servicio.obtenerPorId(id));

        return "update_persona";
    }

    @PostMapping("/update_persona/{id}")
    public String actualizarPersona(@PathVariable Integer id, @ModelAttribute("persona") Persona persona, Model model){

        Persona personaExistente = servicio.obtenerPorId(id);

        personaExistente.setId(id);
        personaExistente.setNombre(persona.getNombre());
        personaExistente.setTelefono(persona.getTelefono());

        servicio.actualizarPersona(personaExistente);

        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String eliminarPersona(@PathVariable Integer id){
        servicio.eliminarPersona(id);
        return "redirect:/list";
    }
}
