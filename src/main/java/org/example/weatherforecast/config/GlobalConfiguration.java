package org.example.weatherforecast.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GlobalConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((httpRequest, bytes, clientHttpRequestException) -> {
            HttpHeaders headers = httpRequest.getHeaders();
            headers.add("User-Agent", applicationName);
            return clientHttpRequestException.execute(httpRequest, bytes);
        });
        return restTemplate;
    }
}
