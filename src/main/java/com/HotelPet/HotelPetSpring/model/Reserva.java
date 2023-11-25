package com.HotelPet.HotelPetSpring.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Reserva {

    @Id
    private ObjectId id;
    private int andar;
    private String data;
    @DBRef
    private Animal animal;

    public Reserva(ObjectId id, int andar, String data, Animal animal) {
        this.id = id;
        this.andar = andar;
        this.data = data;
        this.animal = animal;
    }

    public Reserva() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
