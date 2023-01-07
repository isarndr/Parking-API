package com.codewithisa.parkiran.controller;

import com.codewithisa.parkiran.model.Kendaraan;
import com.codewithisa.parkiran.model.request.KendaraanRequest;
import com.codewithisa.parkiran.service.KendaraanService;
import com.codewithisa.parkiran.service.ParkiranService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/kendaraan")
public class KendaraanController {

    @Autowired
    KendaraanService kendaraanService;

    @Autowired
    ParkiranService parkiranService;

    @Operation(summary = "digunakan saat kendaraan memasuki parkiran")
    @PostMapping("/masuk")
    public ResponseEntity<Kendaraan> kendaraanMasuk(@RequestBody Kendaraan kendaraan){
        try {
            kendaraanService.kendaraanMasuk(kendaraan);
            log.info("Kendaraan dengan plat {} berhasil masuk", kendaraan.getPlat());
            return new ResponseEntity<>(kendaraan, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "digunakan saat kendaraan keluar parkiran, biaya parkir akan terhitung secara otomatis")
    @DeleteMapping("/keluar")
    public ResponseEntity<String> kendaraanKeluar(@RequestBody KendaraanRequest kendaraanRequest){
        try {
            String string = kendaraanService.kendaraanKeluar(kendaraanRequest.getTiketParkir(),
                    kendaraanRequest.getPlat(),
                    kendaraanRequest.getWaktuKeluar());
            log.info("Kendaraan dengan plat {} berhasil keluar", kendaraanRequest.getPlat());
            return new ResponseEntity<>(string, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "untuk menampilkan semua kendaraan yang ada di parkiran")
    @GetMapping("/semua-kendaraan")
    public ResponseEntity<List<String>> daftarKendaraanDiParkiran(){
        try{
            List<String> kendaraanListString = kendaraanService.findAllKendaraan();
            log.info("end point semua-kendaraan berhasil dieksekusi");
            return new ResponseEntity<>(kendaraanListString, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "untuk menampilkan panghasilan parkiran dalam satu hari")
    @GetMapping("/penghasilan")
    public ResponseEntity<String> penghasilanHariIni(){
        try{
            int penghasilanHariIni=parkiranService.getPenghasilanHariIni();
            String string = "Penghasilan parkiran hari ini sebesar Rp" + penghasilanHariIni;
            log.info(string);
            return new ResponseEntity<>(string, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("table parkiran sedang kosong");
            e.printStackTrace();
            return new ResponseEntity<>("table parkiran sedang kosong",HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "untuk menampilkan rata-rata waktu parkir kendaraan di parkiran")
    @GetMapping("/rata-rata-waktu-parkir")
    public ResponseEntity<String> rataRataWaktuParkir(){
        try{
            double meanWaktuParkir=parkiranService.getRataRataWaktuParkir();
            String string = "Rata-rata waktu parkir kendaraan adalah " + meanWaktuParkir + " jam";
            log.info(string);
            return new ResponseEntity<>(string, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("table parkiran sedang kosong");
            e.printStackTrace();
            return new ResponseEntity<>("table parkiran sedang kosong",HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "untuk mereset table parkiran agar rata-rata waktu parkir dan penghasilan parkir kembali menjadi " +
                    "nol"
    )
    @DeleteMapping(
            "/reset-table-parkiran"
    )
    public ResponseEntity<String> resetTableParkiran(){
        parkiranService.clearTable();
        log.info("parkiran table is clear");
        return new ResponseEntity<>("Table Parkrian is clear", HttpStatus.ACCEPTED);
    }
}
