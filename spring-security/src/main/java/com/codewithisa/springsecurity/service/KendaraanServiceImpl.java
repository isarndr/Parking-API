package com.codewithisa.springsecurity.service;

import com.codewithisa.springsecurity.entity.Kendaraan;
import com.codewithisa.springsecurity.entity.Parkiran;
import com.codewithisa.springsecurity.repository.KendaraanRepository;
import com.codewithisa.springsecurity.repository.ParkiranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KendaraanServiceImpl implements KendaraanService{
    @Autowired
    KendaraanRepository kendaraanRepository;

    @Autowired
    ParkiranRepository parkiranRepository;

    @Override
    public void kendaraanMasuk(Kendaraan kendaraan) throws Exception {
        Optional<Kendaraan> kendaraanOptional = kendaraanRepository.findKendaraanByPlat(kendaraan.getPlat());

        if(kendaraanOptional.isPresent()){
           throw new Exception("kendaraan dengan plat yang sama sudah ada di parkiran");
        }

        kendaraanRepository.save(kendaraan);

    }

    @Override
    public String kendaraanKeluar(UUID tiketParkir, String plat, LocalTime waktuKeluar) throws Exception {
        Optional<Kendaraan> kendaraanSaatKeluarOptional = kendaraanRepository.findKendaraanByPlat(plat);

        if(!kendaraanSaatKeluarOptional.isPresent()){
            throw new Exception("plat tidak ada di parkiran");
        }

        Kendaraan kendaraanSaatKeluar = kendaraanSaatKeluarOptional.get();

        Optional<Kendaraan> kendaraanBerdasarkanTiketOptional = kendaraanRepository.findById(tiketParkir);

        if(!kendaraanBerdasarkanTiketOptional.isPresent()){
            throw new Exception("tiket parkir tidak diketahui");
        }

        Kendaraan kendaraanBerdasarkanTiket = kendaraanBerdasarkanTiketOptional.get();

        if(!kendaraanSaatKeluar.getPlat().equals(kendaraanBerdasarkanTiket.getPlat())){
            throw new Exception("plat saat masuk dengan plat saat keluar berbeda");
        }

        long waktuParkir = kendaraanSaatKeluar.getWaktuMasuk().until(waktuKeluar, ChronoUnit.HOURS);

        String jenis = kendaraanSaatKeluar.getJenis().toLowerCase();

        int biayaParkir = 0;
        if(jenis.equals("motor")){
            biayaParkir=1500;
            if(waktuParkir>1){
                biayaParkir+=(waktuParkir-1)*1500;
            }
        }
        else if(jenis.equals("mobil")){
            biayaParkir=5000;
            if(waktuParkir>1){
                biayaParkir+=(waktuParkir-1)*2000;
            }
        }

        Optional<Parkiran> parkiranOptional=parkiranRepository.findById(1l);
        if(!parkiranOptional.isPresent()){
            Parkiran parkiran = Parkiran
                    .builder()
                    .id(1l)
                    .penghasilan(biayaParkir)
                    .jumlahKendaraan(1)
                    .meanWaktuParkir((double)waktuParkir)
                    .jumlahWaktuParkir((int)waktuParkir)
                    .build();
            parkiranRepository.save(parkiran);
            kendaraanRepository.delete(kendaraanSaatKeluar);
            return "kendaraan dengan plat " + plat + " telah keluar, biaya parkirnya adalah " + biayaParkir;
        }
        Parkiran parkiran = parkiranOptional.get();
        parkiran.setJumlahWaktuParkir(parkiran.getJumlahWaktuParkir()+(int)waktuParkir);
        parkiran.setPenghasilan(parkiran.getPenghasilan()+biayaParkir);
        parkiran.setJumlahKendaraan(parkiran.getJumlahKendaraan()+1);
        parkiran.setMeanWaktuParkir((double)parkiran.getJumlahWaktuParkir()/parkiran.getJumlahKendaraan());
        parkiranRepository.save(parkiran);
        kendaraanRepository.delete(kendaraanSaatKeluar);
        return "kendaraan dengan plat " + plat + " telah keluar, biaya parkirnya adalah " + biayaParkir;
    }

    @Override
    public List<String> findAllKendaraan() {
        List<Kendaraan> kendaraanList =kendaraanRepository.findAll();
        List<String> kendaraanListString = new ArrayList<>();
        kendaraanList.forEach(kendaraan -> {
            kendaraanListString.add(kendaraan.getJenis()+" "+kendaraan.getWarna()+" "+kendaraan.getPlat());
        });
        return kendaraanListString;
    }
}
