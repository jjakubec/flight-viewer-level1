package cz.cpost.flight.viewer.api;

import cz.cpost.flight.viewer.model.Departure;
import cz.cpost.flight.viewer.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class DepartureApi {

    @Autowired
    DepartureService departureService;

    @GetMapping("/departures")
    public ResponseEntity<List<Departure>> departuresGet(
            @RequestParam String airport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime begin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        // Validace rozsahu času
        if (begin.isAfter(end) || begin.isEqual(end)) {
            return new ResponseEntity("Chyba: Počáteční čas musí být před konečným časem!", HttpStatus.BAD_REQUEST);
        }

        long secondsBetween = ChronoUnit.SECONDS.between(begin, end);
        if (secondsBetween > 604800) { // 7 dní v sekundách
            return new ResponseEntity("Chyba: Požadavek přesahuje povolených 7 dnů!", HttpStatus.BAD_REQUEST);
        }

        // Pomalejší varianta
        /*List<Departure> departures = departureService.getDepartures(
                airport,
                String.valueOf(begin.atZone(ZoneId.of("Europe/Prague")).toEpochSecond()),
                String.valueOf(end.atZone(ZoneId.of("Europe/Prague")).toEpochSecond())
        );

        return ResponseEntity.ok(departures);*/

        // Rychlejší varianta
        Mono<List<Departure>> departures = departureService.getDepartures(
                airport,
                String.valueOf(begin.atZone(ZoneId.of("Europe/Prague")).toEpochSecond()),
                String.valueOf(end.atZone(ZoneId.of("Europe/Prague")).toEpochSecond())
        );

        return ResponseEntity.ok(Objects.requireNonNull(departures.block()));
    }
}
