package com.HotelPet.HotelPetSpring.repository;

import com.HotelPet.HotelPetSpring.model.Reserva;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends MongoRepository<Reserva, ObjectId> {
}
