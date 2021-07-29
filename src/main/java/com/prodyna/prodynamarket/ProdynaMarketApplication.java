package com.prodyna.prodynamarket;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.prodyna.prodynamarket.Models")
@EnableJpaRepositories(basePackages = "com.prodyna.prodynamarket.repositories")
@ComponentScan(basePackages = {"com.prodyna.prodynamarket.controllers", "com.prodyna.prodynamarket.services"})
public class ProdynaMarketApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProdynaMarketApplication.class, args);
    }

}
