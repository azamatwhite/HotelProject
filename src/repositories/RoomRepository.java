package com.example.hotelproject.repositories;

import com.example.hotelproject.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // бос номер табу
    List<Room> findByAvailableTrue();

    //котегория бойынша номер табу
    List<Room> findByCategory(String category);

    //номер бағасын көрсетілген бағадан төменін іздеу
    List<Room> findByPricePerNightLessThanEqual(Double price);
    
    //белгіллі котегорияның бос номерін табу
    List<Room> findByCategoryAndAvailableTrue(String category);
}
