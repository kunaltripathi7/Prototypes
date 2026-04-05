package com.GraphQL.StockTrader.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOND")
public class Bond extends Asset {

    private Double couponRate;

    public Bond() {
    }

    public Bond(String symbol, String name, AssetType assetType, Double couponRate) {
        super(symbol, name, assetType);
        this.couponRate = couponRate;
    }

    public Double getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }
}
