package cz.cpost.flight.viewer.service;

import cz.cpost.flight.viewer.model.Departure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartureService {

    @Value("${opensky.api.url1}")
    private String apiUrl1;

    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public DepartureService(RestTemplateBuilder restTemplateBuilder, @Value("${opensky.api.url2}") String apiUrl2) {
        this.restTemplate = restTemplateBuilder.build();

        this.webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10485760))
                .baseUrl(apiUrl2)
                .build();
    }

    // Pomalejší varianta
    /*public List<Departure> getDepartures(String airport, String begin, String end) {
        String url = String.format(apiUrl1, airport, begin, end);

        ResponseEntity<Departure[]> response = restTemplate.getForEntity(url, Departure[].class);

        return Arrays.asList(response.getBody());
    }*/

    // Rychlejší varianta
    public Mono<List<Departure>> getDepartures(String airport, String begin, String end) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("airport", airport)
                        .queryParam("begin", begin)
                        .queryParam("end", end)
                        .build())
                .retrieve()
                .bodyToMono(Departure[].class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)))
                .map(Arrays::asList);
    }
}
