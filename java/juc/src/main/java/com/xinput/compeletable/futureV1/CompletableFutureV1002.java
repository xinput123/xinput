package com.xinput.compeletable.futureV1;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xinput.entity.ProductItem;
import com.xinput.util.Factory;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * allOf
 * 我们在处理业务时，有时会有多任务异步处理，同步返回结果的情况
 * <p>
 * 采用多线程执异步行某种任务，比如校验商品的属性是否合法
 * 将执行结果搜集，分组分类，处理。
 * 将处理以后的结果给予展示。
 */
public class CompletableFutureV1002 {

    public static ThreadFactory guavaThreadFactory =
            new ThreadFactoryBuilder().setNameFormat("CompletableFutureV1002-").build();

    public static ExecutorService executor = new ThreadPoolExecutor(
            10,
            20,
            1L,
            TimeUnit.MINUTES,
            new SynchronousQueue<>(),
            guavaThreadFactory,
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
//        List<ProductItem> productItems = createProductItemList(15);
//        validateProductItem1(productItems);
//        validateProductItem2(productItems);
//        validateProductItem3(productItems);
        priceComparison("ipone13");
    }

    static void validateProductItem1(List<ProductItem> productItems) {
        Stopwatch sw = Stopwatch.createStarted();
        List<CompletableFuture<String>> futures = Lists.newArrayListWithCapacity(productItems.size());
        productItems.forEach(productItem -> futures.add(CompletableFuture.supplyAsync(() -> validate(productItem), executor)));
        // 将任务放在一起
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

        List<String> results = Lists.newArrayListWithCapacity(productItems.size());
        allFutures.whenComplete((v, th) -> {
            futures.forEach(cf -> {
                String result = cf.join();
                if (StringUtils.isNotBlank(result)) {
                    results.add(result);
                }
            });
        });
        allFutures.join();
        System.out.println(results);
        sw.stop();
        System.out.println("validateProductItem1 cost: " + sw.elapsed().getSeconds());
    }

    static void validateProductItem2(List<ProductItem> productItems) {
        Stopwatch sw = Stopwatch.createStarted();
        List<CompletableFuture<String>> futures = Lists.newArrayListWithCapacity(productItems.size());
        productItems.forEach(productItem -> futures.add(CompletableFuture.supplyAsync(() -> validate(productItem), executor)));
        // 将任务放在一起
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

//        CompletableFuture<List<String>> allFutureList = allFutures.thenApplyAsync(val -> {
//            System.out.println(Thread.currentThread().getName() + " 处理结果");
//            return futures.stream().map(f -> f.join()).filter(result -> StringUtils.isNotBlank(result)).collect(Collectors.toList());
//        },executor);
        // 这里不需要指定异步的线程去执行，因为依赖的前面的阶段是异步地执行，所以 thenApply()也异步地执行，即时它没有Async后缀。
        CompletableFuture<List<String>> allFutureList = allFutures.thenApply(val -> {
            System.out.println(Thread.currentThread().getName() + " 处理结果");
            return futures.stream().map(f -> f.join()).filter(result -> StringUtils.isNotBlank(result)).collect(Collectors.toList());
        });

        // 获取最终执行的结果
        List<String> results = allFutureList.join();
        System.out.println(results);
        sw.stop();
        System.out.println("validateProductItem2 cost: " + sw.elapsed().getSeconds());
    }

    static void validateProductItem3(List<ProductItem> productItems) {
        Stopwatch sw = Stopwatch.createStarted();
        List<CompletableFuture<String>> futures = Lists.newArrayListWithCapacity(productItems.size());
        productItems.forEach(productItem -> futures.add(CompletableFuture.supplyAsync(() -> validate(productItem), executor)));

        // 对每个 CompletableFuture 进行 join(), 同样可以拿到所有的结果
        List<String> results = futures.stream()
                .map(CompletableFuture::join)
                .filter(result -> StringUtils.isNotBlank(result))
                .collect(Collectors.toList());

        // 获取最终执行的结果
        System.out.println(results);
        sw.stop();
        System.out.println("validateProductItem3 cost: " + sw.elapsed().getSeconds());
    }

    static List<ProductItem> createProductItemList(int size) {
        List<ProductItem> productItemList = Lists.newArrayListWithCapacity(size);
        for (int i = 1; i < size + 1; i++) {
            Long productId = Long.valueOf(i);
            Double price = Double.valueOf(i);
            String productName = "商品-" + i;
            String desc = "这是商品-" + i;
            int sleep = Factory.getRandom(5);
            if (i == 2) {
                // 名称过长
                productItemList.add(new ProductItem(productId, "这是来自全宇宙最闪耀的商品", price, "好用的产品", sleep));
            } else if (i == 4) {
                // 价格异常
                productItemList.add(new ProductItem(productId, productName, 0D, desc, sleep));
            } else if (i == 6) {
                // 描述为空
                productItemList.add(new ProductItem(productId, productName, price, "", sleep));
            } else {
                productItemList.add(new ProductItem(productId, productName, price, desc, sleep));
            }
        }
        return productItemList;
    }

    static String validate(ProductItem productItem) {
        Factory.sleep(productItem.getSleep());
        if (productItem.getPrice() <= 0) {
            return "价格不合理 -> " + productItem.toString();
        }

        if (StringUtils.isAnyEmpty(productItem.getName(), productItem.getDesc())) {
            return "名称和描述不能为空 -> " + productItem.toString();
        }

        if (productItem.getName().length() > 10) {
            return "名称过长 -> " + productItem.toString();
        }


        return "";
    }

    /**
     * 从多个平台比较价格
     */
    static void priceComparison(String productName) {
        Stopwatch sw = Stopwatch.createStarted();
        CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(() -> getPriceFromTaoBao(productName), executor);
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> getPriceFromJD(productName), executor);
        CompletableFuture<Double> cf3 = CompletableFuture.supplyAsync(() -> getPriceFromPDD(productName), executor);

        CompletableFuture<Void> cf = CompletableFuture.allOf(cf1, cf2, cf3).thenRun(() -> {
            try {
                Double taoBaoPrice = cf1.get();
                Double jdPrice = cf2.get();
                Double pddPrice = cf3.get();

                System.out.println("Price comparison for " + productName);
                System.out.println("TaoBao: ￥" + taoBaoPrice);
                System.out.println("JD: ￥" + jdPrice);
                System.out.println("PDD: ￥" + pddPrice);
                List<Double> prices = Lists.newArrayList(taoBaoPrice, jdPrice, pddPrice);
                Double lowestPrice = Collections.min(prices);
                System.out.println("Lowest price: ￥" + lowestPrice);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cf.join();
        sw.stop();
        System.out.println("priceComparison cost: " + sw.elapsed().getSeconds());
    }

    static double getPriceFromTaoBao(String productName) {
        Factory.sleep(3);
        return 8000;
    }

    static double getPriceFromJD(String productName) {
        Factory.sleep(5);
        return 7500;
    }

    static double getPriceFromPDD(String productName) {
        Factory.sleep(4);
        return 7000;
    }
}
