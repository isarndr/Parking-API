package com.codewithisa.parkiran.repository;

import com.codewithisa.parkiran.model.Kendaraan;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class KendaraanRepositoryTest {

    @Autowired
    KendaraanRepository kendaraanRepository;

    @Test
    public void findKendaraanByPlat(){
        Optional<Kendaraan> kendaraanOptional = kendaraanRepository.findKendaraanByPlat("A 111 H");
        if(kendaraanOptional.isPresent()){
            log.info("kendaraan ditemukan di database");
        }
        else{
            log.info("kendaraan tidak ditemukan di database");
        }
    }
}
