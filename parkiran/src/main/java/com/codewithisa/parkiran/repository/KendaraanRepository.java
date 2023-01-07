package com.codewithisa.parkiran.repository;

import com.codewithisa.parkiran.model.Kendaraan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KendaraanRepository extends JpaRepository<Kendaraan, UUID> {

    @Query(
            nativeQuery = true,
            value = "select * from kendaraan where plat = :plat"
    )
    Optional<Kendaraan> findKendaraanByPlat(@Param("plat") String plat);
}
