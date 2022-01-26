package com.github.archturion64.CodingChallengeIceCream.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IceCreamFlavorRepository extends JpaRepository<IceCreamFlavorEntity, Long> {
    Optional<IceCreamFlavorEntity> findByName(String name);
}
