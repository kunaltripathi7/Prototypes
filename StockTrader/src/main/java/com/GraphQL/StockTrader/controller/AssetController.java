package com.GraphQL.StockTrader.controller;

import com.GraphQL.StockTrader.dto.AddStockInput;
import com.GraphQL.StockTrader.entity.Asset;
import com.GraphQL.StockTrader.entity.AssetType;
import com.GraphQL.StockTrader.entity.Stock;
import com.GraphQL.StockTrader.repository.AssetRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Controller
public class AssetController {

    private final AssetRepository assetRepository;

    // Reactor Sink for emitting events to subscribers
    private final Sinks.Many<Asset> assetSink;

    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
        // Allows multiple subscribers, buffers if they get slow
        this.assetSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @QueryMapping
    public List<Asset> assets() {
        return assetRepository.findAll();
    }

    @QueryMapping
    public Asset asset(@Argument Long id) { // validations happen via graphql engine -> before coming & converting to
                                            // java object all of the validations happen in graphql
        return assetRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Stock addStock(@Argument AddStockInput input) {
        Stock newStock = new Stock(input.symbol(), input.name(), AssetType.STOCK, input.exchange());
        Stock savedStock = assetRepository.save(newStock);

        // Emit the newly saved stock to any active subscribers
        assetSink.tryEmitNext(savedStock);

        return savedStock;
    }

    @MutationMapping
    public Boolean removeAsset(@Argument Long id) {
        if (assetRepository.existsById(id)) {
            assetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @SubscriptionMapping
    public Flux<Asset> assetAdded() {
        return assetSink.asFlux();
    }
}
