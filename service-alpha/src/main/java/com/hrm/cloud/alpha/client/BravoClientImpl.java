package com.hrm.cloud.alpha.client;

import com.hrm.cloud.alpha.config.BravoClientProperties;
import com.hrm.cloud.alpha.dto.MyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class BravoClientImpl implements BravoClient {

    private final WebClient webClient;
    private final BravoClientProperties bravoClientConfig;

    @Override
    public MyDto ping(MyDto myDto) {
        log.info("Sending request to BRAVO: {}", myDto);
        MyDto response = webClient.post()
                .uri(uri -> uri.scheme(bravoClientConfig.getScheme())
                        .host(bravoClientConfig.getHost())
                        .port(bravoClientConfig.getPort())
                        .path(bravoClientConfig.getEndpoint())
                        .build())
                .bodyValue(myDto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(MyDto.class)
                .block();
        log.info("Received response from BRAVO: {}", response);
        return response;
    }
}
