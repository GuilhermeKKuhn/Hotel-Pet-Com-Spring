package com.HotelPet.HotelPetSpring.repository;

import com.HotelPet.HotelPetSpring.model.Animal;
import com.HotelPet.HotelPetSpring.model.Tutor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, ObjectId> {

    public List<Animal> findByTutor(Tutor tutor);

}
