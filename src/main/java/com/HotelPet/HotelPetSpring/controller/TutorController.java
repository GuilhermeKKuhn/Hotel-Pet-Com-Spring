package com.HotelPet.HotelPetSpring.controller;

import com.HotelPet.HotelPetSpring.model.Animal;
import com.HotelPet.HotelPetSpring.model.Tutor;
import com.HotelPet.HotelPetSpring.repository.AnimalRepository;
import com.HotelPet.HotelPetSpring.repository.TutorRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TutorController {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/tutor/cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("tutor", new Tutor());
        return "tutor/cadastrar";
    }

    @PostMapping("/tutor/salvar")
    public String salvar(@ModelAttribute Tutor tutor){
        repository.insert(tutor);
        return "redirect:/";
    }

    @GetMapping("/tutor/listar")
    public String listar(Model model){
        List<Tutor> tutores = repository.findAll();
        model.addAttribute("tutores", tutores);
        return "tutor/listar";
    }

    @GetMapping("/tutor/visualizar/{id}")
    public String visualizar(@PathVariable ObjectId id, Model model){
        Tutor tutor = repository.findById(id).orElse(null);
        model.addAttribute("tutor", tutor);
        return "tutor/visualizar";
    }

    @GetMapping("/tutor/excluir/{id}")
    public String excluir(@PathVariable ObjectId id){
        Tutor tutor = repository.findById(id).orElse(null);
        if (tutor != null ){
            List<Animal> animaisTutor = animalRepository.findByTutor(tutor);
            if(animaisTutor.isEmpty()){
                repository.deleteById(id);
                return "redirect:/tutor/listar";
            }else{
                throw new IllegalArgumentException("O tutor nao pode ser excluido pois existem animais vinculados a ele ");
            }
        }else {
            return "redirect:/tutor/listar";
        }

    }

    @GetMapping("/tutor/atualizar/{id}")
    public String atualizar(@PathVariable ObjectId id, Model model){
        Tutor tutor = repository.findById(id).orElse(null);
        model.addAttribute("tutor", tutor);
        return "tutor/atualizar";
    }

    @PostMapping("/tutor/editar/{id}")
    public String editar(@ModelAttribute Tutor tutor){
        repository.save(tutor);
        return"redirect:/tutor/listar";
    }

}
