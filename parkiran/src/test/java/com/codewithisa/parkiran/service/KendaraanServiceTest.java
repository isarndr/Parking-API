package com.codewithisa.parkiran.service;

import com.codewithisa.parkiran.model.Kendaraan;
import com.codewithisa.parkiran.repository.KendaraanRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class KendaraanServiceTest {

    @Autowired
    KendaraanService kendaraanService;

    @Autowired
    KendaraanRepository kendaraanRepository;

    @Test
    public void kendaraanMasuk() {
        Kendaraan kendaraan = Kendaraan
                .builder()
                .jenis("motor")
                .warna("ijo")
                .plat("A 111 H")
                .waktuMasuk(LocalTime.of(8,45,00))
                .build();

        try{
            kendaraanService.kendaraanMasuk(kendaraan);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void kendaraanKeluar(){
        try {
            kendaraanService.kendaraanKeluar(UUID.fromString(
                    "757a6453-10a6-4e6f-96fc-76e917f6464b"),
                    "A 111 H",
                    LocalTime.of(15,15,00)
            );
            log.info("kendaraan keluar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllKendaraan(){
        List<String> listKendaraanInString = kendaraanService.findAllKendaraan();
        listKendaraanInString.forEach(kendaraanInString -> {
            log.info(kendaraanInString);
        });
    }
}
