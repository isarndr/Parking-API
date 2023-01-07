package com.codewithisa.parkiran.model.request;

import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class KendaraanRequest {
    private UUID tiketParkir;

    private String plat;

    private LocalTime waktuKeluar;
}
