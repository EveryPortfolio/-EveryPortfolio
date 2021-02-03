package com.everyportfolio.api_gateway.filter;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PortfolioFilter extends AbstractGatewayFilterFactory<PortfolioFilter.Config> {
    private final Logger log = LoggerFactory.getLogger(PortfolioFilter.class);
    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    public PortfolioFilter() {
        super(PortfolioFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(PortfolioFilter.Config config) {
        return ((exchange, chain) -> {
            log.info("PortfolioFilter baseMessage>>>>>>" + config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("PortfolioFilter Start>>>>>>" + exchange.getRequest());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if (config.isPostLogger()) {
                    log.info("PortfolioFilter End>>>>>>" + exchange.getResponse());
                }
            }));
        });
    }
}
