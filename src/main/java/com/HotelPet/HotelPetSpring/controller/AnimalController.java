package com.HotelPet.HotelPetSpring.controller;

import com.HotelPet.HotelPetSpring.model.Animal;
import com.HotelPet.HotelPetSpring.model.Tutor;
import com.HotelPet.HotelPetSpring.repository.AnimalRepository;
import com.HotelPet.HotelPetSpring.repository.TutorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping("/animal/cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("animal", new Animal());
        List<Tutor> tutores = tutorRepository.findAll();
        model.addAttribute("tutores", tutores);
        return "animal/cadastrar";
    }

    @PostMapping("/animal/salvar")
    public String salvar(@ModelAttribute Animal animal){
        animalRepository.insert(animal);
        return "redirect:/";
    }

    @GetMapping("/animal/listar")
    public String listar(Model model){
        List<Animal> animais = animalRepository.findAll();
        model.addAttribute("animais", animais);
        return "animal/listar";
    }

    @GetMapping("/animal/visualizar/{id}")
    public String visualizar(@PathVariable ObjectId id, Model model){
        Animal animal = animalRepository.findById(id).orElse(null);
        model.addAttribute("animal", animal);
        return "animal/visualizar";
    }

    @GetMapping("/animal/excluir/{id}")
    public String excluir(@PathVariable ObjectId id){
        animalRepository.deleteById(id);
        return "redirect:/animal/listar";
    }

    @GetMapping("/animal/atualizar/{id}")
    public String atualizar(@PathVariable ObjectId id, Model model){
        Animal animal = animalRepository.findById(id).orElse(null);
        List<Tutor> tutores = tutorRepository.findAll();
        model.addAttribute("tutores", tutores);
        model.addAttribute("animal", animal);
        return "animal/atualizar";
    }

    @PostMapping("/animal/editar/{id}")
    public String editar(@ModelAttribute Animal animal){
        animalRepository.save(animal);
        return "redirect:/animal/listar";
    }

}

