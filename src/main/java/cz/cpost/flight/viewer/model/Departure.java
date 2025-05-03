package cz.cpost.flight.viewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departure {

    @JsonProperty("icao24")
    private String icao24;

    @JsonProperty("firstSeen")
    private Long firstSeen;

    @JsonProperty("estDepartureAirport")
    private String estDepartureAirport;

    @JsonProperty("lastSeen")
    private Long lastSeen;

    @JsonProperty("estArrivalAirport")
    private String estArrivalAirport;

    @JsonProperty("callsign")
    private String callsign;

    @JsonProperty("estDepartureAirportHorizDistance")
    private Integer estDepartureAirportHorizDistance;

    @JsonProperty("estDepartureAirportVertDistance")
    private Integer estDepartureAirportVertDistance;

    @JsonProperty("estArrivalAirportHorizDistance")
    private Integer estArrivalAirportHorizDistance;

    @JsonProperty("estArrivalAirportVertDistance")
    private Integer estArrivalAirportVertDistance;

    @JsonProperty("departureAirportCandidatesCount")
    private Integer departureAirportCandidatesCount;

    @JsonProperty("arrivalAirportCandidatesCount")
    private Integer arrivalAirportCandidatesCount;
}
