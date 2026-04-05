package com.GraphQL.StockTrader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import com.GraphQL.StockTrader.entity.Stock;

import graphql.schema.TypeResolver;

@Configuration // needed to intercept and map java obj to graphql type
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.type("Asset", typeWiring -> typeWiring.typeResolver(assetTypeResolver()));
    }

    private TypeResolver assetTypeResolver() {
        return env -> {
            Object javaObject = env.getObject();
            if (javaObject instanceof Stock) {
                return env.getSchema().getObjectType("Stock");
            }
            else {
                return env.getSchema().getObjectType("Bond");
            }
        };
    }
}
