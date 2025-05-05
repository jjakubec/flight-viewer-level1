package cz.cpost.flight.viewer.service;

import cz.cpost.flight.viewer.model.Departure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Service for fetching flight departure data from the OpenSky Network API.
 * This service uses both RestTemplate and WebClient to demonstrate two different approaches to making HTTP requests.
 */
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

    // Slower variant
    /*public List<Departure> getDepartures(String airport, String begin, String end) {
        String url = String.format(apiUrl1, airport, begin, end);

        ResponseEntity<Departure[]> response = restTemplate.getForEntity(url, Departure[].class);

        return Arrays.asList(response.getBody());
    }*/

    // Faster variant
    /**
     * Fetches flight departure data from the OpenSky Network API using WebClient.
     *
     * @param airport The airport code for which to fetch departure data.
     * @param begin   The start time for the data range (in seconds since epoch).
     * @param end     The end time for the data range (in seconds since epoch).
     * @return A Mono containing a list of Departure objects.
     */
    public Mono<List<Departure>> getDepartures(String airport, String begin, String end) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("airport", airport)
                        .queryParam("begin", begin)
                        .queryParam("end", end)
                        .build())
                .retrieve()
                .bodyToMono(Departure[].class)
                .map(Arrays::asList);
    }
}
