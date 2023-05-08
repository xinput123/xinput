package com.xinput.compeletable.futureV2;

import com.xinput.entity.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @since
 */
public class CompletableFuture002 {

    private static List<Shop> shopList = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {

    }

    // 同步: 查询所有店铺某个商品价格
    private static List<String> findPriceSync(String product) {
        return shopList.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))  //格式转换
                .collect(Collectors.toList());
    }

    // 异步: 查询所有店铺某个商品价格
//    private static List<String> findPriceAsync(String product) {
//        shopList.stream().map(shop -> CompletableFuture.supplyAsync());
//
//
//    }
}
