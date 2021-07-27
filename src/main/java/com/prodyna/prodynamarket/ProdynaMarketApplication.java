package com.prodyna.prodynamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.prodyna.prodynamarket.Models")
@EnableJpaRepositories(basePackages = "com.prodyna.prodynamarket.Repositories")
@ComponentScan(basePackages = {"com.prodyna.prodynamarket.Controllers", "com.prodyna.prodynamarket.Services"})
public class ProdynaMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdynaMarketApplication.class, args);
    }

}
