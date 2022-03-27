package ru.sobolev.spring_market.cart.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.sobolev.spring_market.cart.properties.CoreServiceIntegrationProperties;
import ru.sobolev.spring_market.cart.properties.TimeoutsForCoreServiceIntegration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(
        {CoreServiceIntegrationProperties.class, TimeoutsForCoreServiceIntegration.class}
)
@RequiredArgsConstructor
public class WebClientConfig {

    private final CoreServiceIntegrationProperties coreServiceIntegrationProperties;

    @Bean
    public WebClient coreServiceWebClient() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, coreServiceIntegrationProperties.getTimeouts().getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(coreServiceIntegrationProperties.getTimeouts().getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(coreServiceIntegrationProperties.getTimeouts().getWriteTimeout(), TimeUnit.MILLISECONDS));
                });

        return WebClient
                .builder()
                .baseUrl(coreServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
