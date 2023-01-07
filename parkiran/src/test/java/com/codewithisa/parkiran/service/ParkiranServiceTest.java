package com.codewithisa.parkiran.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ParkiranServiceTest {

    @Autowired
    ParkiranService parkiranService;

    @Test
    public void getPenghasilanHariIni(){
        try{
            log.info("penghasilan hari ini sebesar " + parkiranService.getPenghasilanHariIni());
        }
        catch (Exception e){
            log.error("table parkiran belum terbuat");
        }
    }

    @Test
    public void getRataRataWaktuParkir(){
        try{
            log.info("rata-rata waktu parkir kendaraan adalah " + parkiranService.getRataRataWaktuParkir() + " jam");
        }
        catch (Exception e){
            log.error("table parkiran belum terbuat");
        }
    }
}
