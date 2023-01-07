package com.codewithisa.parkiran.service;

import com.codewithisa.parkiran.model.Kendaraan;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public interface KendaraanService {

    void kendaraanMasuk(Kendaraan kendaraan) throws Exception;

    String kendaraanKeluar(UUID tiketParkir, String plat, LocalTime waktuKeluar) throws Exception;

    List<String> findAllKendaraan();
}
