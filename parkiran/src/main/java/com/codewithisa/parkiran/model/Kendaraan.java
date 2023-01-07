package com.codewithisa.parkiran.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "plat")
)
public class Kendaraan {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "tiketParkir", unique = true)
    @Schema(hidden = true)
    private UUID tiketParkir;

    @Schema(example = "mobil")
    @Column(nullable = false)
    private String jenis;

    @Schema(example = "merah")
    @Column(nullable = false)
    private String warna;

    @Schema(example = "B 123 UI")
    @Column(nullable = false)
    private String plat;

    @Schema(example = "08:15:45")
    @Column(nullable = false)
    private LocalTime waktuMasuk;

}
