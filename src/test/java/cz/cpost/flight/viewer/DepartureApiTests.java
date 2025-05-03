package cz.cpost.flight.viewer;

import cz.cpost.flight.viewer.api.DepartureApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@SpringBootTest
class DepartureApiTests {

    @Autowired
    DepartureApi departureApi;

    @Test
    void departuresGetOK() {

        String airport = "EDDF";
        LocalDateTime begin = LocalDateTime.parse("2023-10-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2023-10-02T00:00:00");

        ResponseEntity response = departureApi.departuresGet(airport, begin, end);

        Assertions.assertNotNull(response);
    }

    @Test
    void departuresGetBadRequest() {

        String airport = "EDDF";
        LocalDateTime begin = LocalDateTime.parse("2023-10-02T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2023-10-01T00:00:00");

        ResponseEntity response = departureApi.departuresGet(airport, begin, end);

        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("Chyba: Počáteční čas musí být před konečným časem!", response.getBody());
    }

    @Test
    void departuresGetBadRequest2() {

        String airport = "EDDF";
        LocalDateTime begin = LocalDateTime.parse("2023-10-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2023-10-09T00:00:00");

        ResponseEntity response = departureApi.departuresGet(airport, begin, end);

        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("Chyba: Požadavek přesahuje povolených 7 dnů!", response.getBody());
    }
}
