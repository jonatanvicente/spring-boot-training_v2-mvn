package com.training.springbootdata.repository;

import com.training.springbootdata.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAirportRepository extends JpaRepository<Airport, String> {

    List<Airport> findAll();

    Optional<Airport> findById(String iata);

    List<Airport> findAll(Sort sort);

/*    @Query(
            value = "SELECT * FROM USERS u WHERE u.status = 1",
            nativeQuery = true)
    List<Airport> findAllById(Iterable<String> strings);*/

    Airport saveAndFlush(Airport entity);

    void deleteAllInBatch(Iterable<Airport> entities);

    void deleteAllByIdInBatch(Iterable<String> strings);


    Page<Airport> findAll(Pageable pageable);

    Airport save(Airport entity);

    boolean existsById(String s);

    long count();

    void deleteById(String s);

    void delete(Airport entity);

    void deleteAllById(Iterable<? extends String> strings);

    void deleteAll(Iterable<? extends Airport> entities);

    void deleteAll();
}
