package com.codewithisa.parkiran.service;

import org.springframework.stereotype.Service;

@Service
public interface ParkiranService {

    int getPenghasilanHariIni();

    double getRataRataWaktuParkir();

    void clearTable();
}
