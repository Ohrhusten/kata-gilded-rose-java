package com.gildedrose;

import java.util.stream.Stream;

class GildedRose {
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Stream.of(items).forEach(this::processItem);
    }

    private void processItem(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                handleAgedBrie(item);
                break;
            case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                handleBackstagePasses(item);
                break;
            case SULFURAS_HAND_OF_RAGNAROS:
                // Do nothing here
                break;
            default:
                handleRemainingItems(item);
        }
    }

    private void handleRemainingItems(Item item) {
        item.sellIn--;
        decreaseQualityBounded(item);
        if (item.sellIn < 0) {
            decreaseQualityBounded(item);
        }
    }

    private void handleBackstagePasses(Item item) {
        item.sellIn--;
        increaseQualityBounded(item);
        if (item.quality < 50) {
            increaseQualityForSellInSmallerThan(item, 10);
            increaseQualityForSellInSmallerThan(item, 5);
        }
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void handleAgedBrie(Item item) {
        item.sellIn--;
        increaseQualityBounded(item);
        if (item.sellIn < 0) {
            increaseQualityBounded(item);
        }
    }

    private void increaseQualityForSellInSmallerThan(Item item, int upper) {
        if (item.sellIn < upper) {
            item.quality++;
        }
    }

    private void increaseQualityBounded(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseQualityBounded(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}