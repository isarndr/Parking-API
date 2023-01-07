package com.codewithisa.springsecurity.service;

import com.codewithisa.springsecurity.repository.ParkiranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkiranServiceImpl implements ParkiranService{

    @Autowired
    ParkiranRepository parkiranRepository;

    @Override
    public int getPenghasilanHariIni() {
        return parkiranRepository.findAll().get(0).getPenghasilan();
    }

    @Override
    public double getRataRataWaktuParkir() {
        return parkiranRepository.findAll().get(0).getMeanWaktuParkir();
    }

    @Override
    public void clearTable() {
        parkiranRepository.deleteAll();
    }
}
