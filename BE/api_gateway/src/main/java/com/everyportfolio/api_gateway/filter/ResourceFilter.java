package com.everyportfolio.api_gateway.filter;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ResourceFilter extends AbstractGatewayFilterFactory<ResourceFilter.Config> {
    private final Logger log = LoggerFactory.getLogger(ResourceFilter.class);
    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    public ResourceFilter () {super(ResourceFilter.Config.class);}
    @Override
    public GatewayFilter apply(ResourceFilter.Config config) {
        return ((exchange, chain) -> {
            log.info("ResourceFilter baseMessage>>>>>>" + config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("ResourceFilter Start>>>>>>" + exchange.getRequest());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if (config.isPostLogger()) {
                    log.info("ResourceFilter End>>>>>>" + exchange.getResponse());
                }
            }));
        });
    }
}
