package com.codewithisa.parkiran.repository;

import com.codewithisa.parkiran.model.Parkiran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkiranRepository extends JpaRepository<Parkiran, Long> {
}
