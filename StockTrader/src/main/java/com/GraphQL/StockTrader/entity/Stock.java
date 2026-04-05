package com.GraphQL.StockTrader.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STOCK")
public class Stock extends Asset {

    private String exchange;

    public Stock() {
    }

    public Stock(String symbol, String name, AssetType assetType, String exchange) {
        super(symbol, name, assetType);
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
