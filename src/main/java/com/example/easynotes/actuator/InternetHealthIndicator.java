package com.example.easynotes.actuator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InternetHealthIndicator implements HealthIndicator {

    private static final Log logger = LogFactory.getLog(InternetHealthIndicator.class);

    protected String hostName = "google.com";
    protected int port = 443;
    protected int timeout = 1000;

    @Override
    public Health health() {
        try {
            int time = ping(hostName, port, timeout);
            return Health.up().withDetail("ping", time).build();
        } catch (IOException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("Internet is down", e);
            }
            return Health.down(e).build();
        }
    }

    protected int ping(String hostName, int port, int timeout) throws IOException {
        try (Socket socket = new Socket()) {
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            long time = System.currentTimeMillis();
            socket.connect(socketAddress, timeout);
            return (int) (System.currentTimeMillis() - time);
        }
    }
}