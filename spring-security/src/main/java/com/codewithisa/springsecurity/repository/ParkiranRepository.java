package com.codewithisa.springsecurity.repository;

import com.codewithisa.springsecurity.entity.Parkiran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkiranRepository extends JpaRepository<Parkiran, Long> {
}
