package com.HotelPet.HotelPetSpring;

import com.HotelPet.HotelPetSpring.model.Animal;
import com.HotelPet.HotelPetSpring.model.Tutor;
import com.HotelPet.HotelPetSpring.repository.AnimalRepository;
import com.HotelPet.HotelPetSpring.repository.ReservaRepository;
import com.HotelPet.HotelPetSpring.repository.TutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    private Animal animal;

    @BeforeEach
    public void Inicial() {
        tutorRepository.deleteAll();
        animalRepository.deleteAll();
        reservaRepository.deleteAll();

        Tutor tutor = new Tutor();
        tutor.setNome("João");
        tutor.setCpf("12345678900");
        tutor = tutorRepository.save(tutor);

        animal = new Animal("Rex", "Cachorro");
        animal.setTutor(tutor);
        animalRepository.save(animal);
    }

    @Test
    public void testCadastrarAnimal() throws Exception {
        mockMvc.perform(get("/animal/cadastrar"))
                .andExpect(status().isOk())
                .andExpect(view().name("animal/cadastrar"));
    }


    @Test
    public void testExcluirAnimal() throws Exception {
        mockMvc.perform(get("/animal/excluir/" + animal.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/animal/listar"));
    }

    @Test
    public void testSalvarReserva() throws Exception {
        mockMvc.perform(post("/reserva/salvar")
                        .param("animal.id", animal.getId().toHexString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}
