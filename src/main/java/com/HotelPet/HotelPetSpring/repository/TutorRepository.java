package com.HotelPet.HotelPetSpring.repository;

import com.HotelPet.HotelPetSpring.model.Tutor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends MongoRepository<Tutor, ObjectId> {

}
