package com.ecommerce.order.clients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
public class ProductServiceConfig {

    @Bean
    public ProductServiceClient restClientProductInterface(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder
                                    .baseUrl("http://product-service")
                                    .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                                            (request, response) -> Optional.empty())
                                    .build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(ProductServiceClient.class);
    }
}
