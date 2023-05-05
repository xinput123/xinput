package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;

/**
 * 从多个平台比较价格
 */
public class PriceComparisonApp {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String product = "iPhone 13";
        CompletableFuture<Double> priceFromAmazon = getPriceFromAmazon(product);
        CompletableFuture<Double> priceFromEbay = getPriceFromEbay(product);
        CompletableFuture<Double> priceFromBestBuy = getPriceFromBestBuy(product);

        CompletableFuture.allOf(priceFromAmazon, priceFromEbay, priceFromBestBuy)
                .thenRun(() -> {
                    try {
                        Double amazonPrice = priceFromAmazon.get();
                        Double ebayPrice = priceFromEbay.get();
                        Double bestBuyPrice = priceFromBestBuy.get();

                        System.out.println("Price comparison for " + product);
                        System.out.println("Amazon: $" + amazonPrice);
                        System.out.println("eBay: $" + ebayPrice);
                        System.out.println("Best Buy: $" + bestBuyPrice);

                        Double lowestPrice = Math.min(Math.min(amazonPrice, ebayPrice), bestBuyPrice);
                        System.out.println("Lowest price: $" + lowestPrice);

                        System.out.println("cost mills: " + (System.currentTimeMillis() - start));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


    }

    public static CompletableFuture<Double> getPriceFromAmazon(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        // simulate getting price from Amazon
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete(1100.0);
        }).start();
        return future;
    }

    public static CompletableFuture<Double> getPriceFromEbay(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        // simulate getting price from Amazon
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete(1200.0);
        }).start();
        return future;
    }


    public static CompletableFuture<Double> getPriceFromBestBuy(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        // simulate getting price from Amazon
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete(1300.0);
        }).start();
        return future;
    }
}