package com.okan.hazelcastdemo.repository;

import com.okan.hazelcastdemo.domain.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    Fruit findFruitByName(final String name);

    void deleteFruitByName(final String key);

    @Query("select f.name from Fruit f")
    Set<String> findAllFruits();
}
