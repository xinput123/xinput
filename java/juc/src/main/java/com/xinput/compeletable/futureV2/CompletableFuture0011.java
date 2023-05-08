package com.xinput.compeletable.futureV2;

import com.google.common.collect.Lists;
import com.xinput.entity.Request;
import com.xinput.entity.Result;
import com.xinput.util.Factory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用 CompletableFuture 替代 CountDownLatch
 */
public class CompletableFuture0011 {
    public static void main(String[] args) {
    }

    public static void countDownLatch() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Request> requests = createRequest(10);
        CountDownLatch countDown = new CountDownLatch(requests.size());
        for (Request request : requests) {
            executor.execute(() -> {
                try {
                    Result result = exec(request);

                } finally {
                    countDown.countDown();
                }
            });
        }
        countDown.await(200, TimeUnit.MILLISECONDS);
    }

    public static List<Request> createRequest(int size) {
        if (size <= 0) {
            return Lists.newArrayList();
        }

        List<Request> requests = Lists.newArrayListWithCapacity(size);
        for (int i = 0; i < size; i++) {
            Request request = new Request();
            request.setRequestId(i + 1);
            requests.add(request);
        }

        return requests;
    }

    public static Result exec(Request request) {
        Factory.sleep(Factory.getRandom(5));
        Result result = new Result();
        result.setRequestId(request.getRequestId());
        result.setName(request.getContent());
        return result;
    }

}
