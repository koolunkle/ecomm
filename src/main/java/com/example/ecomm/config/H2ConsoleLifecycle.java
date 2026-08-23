package com.example.ecomm.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class H2ConsoleLifecycle {

    private static final Logger log = LoggerFactory.getLogger(H2ConsoleLifecycle.class);

    private final H2ConsoleProperties properties;

    private Server webServer;

    public H2ConsoleLifecycle(H2ConsoleProperties properties) {
        this.properties = properties;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException {
        log.info("starting h2 console at http://localhost:{}", properties.port());
        this.webServer = Server.createWebServer("-webPort", properties.port().toString(), "-webAllowOthers")
                .start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        if (this.webServer != null) {
            this.webServer.stop();
        }
    }
}
