package com.codewithisa.parkiran.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI demoApi(
            @Value(
                    "Parkiran API dapat melakukan simulasi parkiran di mana kendaraan dapat masuk ke parkiran" +
                    " kemudian mendapatkan tiket parkir. Kendaraan yang masuk akan disimpan ke dalam " +
                    "database seperti jenis kendaraannya, warnanya, plat nomornya, serta jam masuknya. " +
                    "Kendaraan yang keluar harus memberikan tiket parkirnya yang berisi kode unik untuk " +
                    "melakukan validasi dan pembayaran. Pembayaran akan secara otomatis terhitung " +
                    "berdasarkan jenis kendaraan dan waktu parkir. Parkiran API juga dapat menghitung berapa" +
                    " banyak kendaraan yang terparkir di parkiran, menghitung total penghasilan parkiran, " +
                    "dan menghitung waktu parkir rata-rata.\n\nUntuk informasi lebih lanjur silahkan menghubungi " +
                            "isarandra@yahoo.com"
            ) String appDescription,
            @Value("v1.0.0") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Parkiran API")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}

