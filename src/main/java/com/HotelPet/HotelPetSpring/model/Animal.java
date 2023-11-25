package com.HotelPet.HotelPetSpring.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Document
public class Animal {

    @Id
    private ObjectId id;
    private String nome;
    private String especie;
    @DBRef
    private Tutor tutor;

    public Animal(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
    }

    public Animal() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public Animal criarId(){
        setId(new ObjectId());
        return this;
    }

    public void setEspecie(String especie) {
       this.especie = especie;
    }


    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
