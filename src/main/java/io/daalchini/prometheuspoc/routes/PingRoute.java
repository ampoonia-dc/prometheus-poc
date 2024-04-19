package io.daalchini.prometheuspoc.routes;

import io.micrometer.core.annotation.Counted;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@Log4j2
public class PingRoute {

    public record PingResponse(
            Long timestamp
    ) {
    }

    @Counted("ping.count")
    @GetMapping({"/", "/ping"})
    public PingResponse ping() {
        final long currentInstantMillis = Instant.now().toEpochMilli();
        final PingResponse response = new PingResponse(currentInstantMillis);

        log.info("ping, response = {}", response);
        return response;
    }
}
