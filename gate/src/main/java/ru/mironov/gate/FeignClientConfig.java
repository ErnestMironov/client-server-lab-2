package ru.mironov.gate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mironov.gate.client.api.StudentDataApi;
import ru.mironov.gate.client.ApiClient;

@Configuration
public class FeignClientConfig {

    @Bean
    public StudentDataApi someStudentApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://localhost:8083");
        return apiClient.buildClient(StudentDataApi.class);
    }
}
