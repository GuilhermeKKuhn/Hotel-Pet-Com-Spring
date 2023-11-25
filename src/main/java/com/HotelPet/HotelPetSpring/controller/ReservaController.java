package com.HotelPet.HotelPetSpring.controller;

import com.HotelPet.HotelPetSpring.model.Animal;
import com.HotelPet.HotelPetSpring.model.Reserva;
import com.HotelPet.HotelPetSpring.model.Tutor;
import org.bson.types.ObjectId;
import org.springframework.ui.Model;
import com.HotelPet.HotelPetSpring.repository.AnimalRepository;
import com.HotelPet.HotelPetSpring.repository.ReservaRepository;
import com.HotelPet.HotelPetSpring.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservaController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("/reserva/realizarReserva")
    public String realizarReserva(Model model){
        Reserva reserva = new Reserva();
        List<Animal> animais = animalRepository.findAll();
        model.addAttribute("animais", animais);
        model.addAttribute("reserva", reserva);
        return "reserva/realizarReserva";
    }

    @PostMapping("/reserva/salvar")
    public String salvar(@ModelAttribute Reserva reserva){
        ObjectId idAnimal = reserva.getAnimal().getId();
        Animal animalSelecionado = animalRepository.findById(idAnimal).orElse(null);
        if(animalSelecionado.getEspecie().equalsIgnoreCase("Cachorro")){
            reserva.setAndar(1);
        } else if (animalSelecionado.getEspecie().equalsIgnoreCase("Gato")) {
            reserva.setAndar(2);
        }else {
            reserva.setAndar(3);
        }
        reserva.setAnimal(animalSelecionado);
        reservaRepository.insert(reserva);
        return "redirect:/";
    }

    @GetMapping("/reserva/listar")
    public String listar(Model model){
        List<Reserva> reservas = reservaRepository.findAll();
        model.addAttribute("reservas", reservas);
        return "reserva/listar";
    }

    @GetMapping("/reserva/excluir")
    public String excluir(@PathVariable ObjectId id){
        reservaRepository.deleteById(id);
        return "redirect:/reserva/listar";
    }

    @GetMapping("/reserva/visualizar/{id}")
    public String visualizar(@PathVariable ObjectId id, Model model){
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        model.addAttribute("reserva", reserva);
        return "reserva/visualizar";
    }

    @GetMapping("/reserva/excluir/{id}")
    public String excluirPorId(@PathVariable ObjectId id){
        reservaRepository.deleteById(id);
        return"redirect:/reserva/listar";
    }


}
