package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 从多个API可以获取不同维度的用户信息
 */
public class UserInfoCompletable {

    public static ExecutorService exs = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {

    }

    public static void a2() {
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(getName(), getAge(), getAddress());

        combinedFuture.thenApplyAsync(v -> {
            String name = getName().join();
            Integer age = getAge().join();
            String address = getAddress().join();
            String userInfo = name + ", " + age + ", " + address;
            System.out.println(userInfo);
            return userInfo;
        });

    }

    public static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> "John Doe");
    }

    public static CompletableFuture<Integer> getAge() {
        return CompletableFuture.supplyAsync(() -> 25);
    }

    public static CompletableFuture<String> getAddress() {
        return CompletableFuture.supplyAsync(() -> "123 Main St, Anytown, USA");
    }


    public static void a1() {
        CompletableFuture<String> nameFuture = getNameFromAPI();
        CompletableFuture<Integer> ageFuture = getAgeFromAPI();
        CompletableFuture<String> addressFuture = getAddressFromAPI();
        CompletableFuture<String> phoneFuture = getPhoneFromAPI();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(nameFuture, ageFuture, addressFuture, phoneFuture);

        allFutures.thenRun(() -> {
            String name = nameFuture.join();
            Integer age = ageFuture.join();
            String address = addressFuture.join();
            String phone = phoneFuture.join();


            System.out.println("User Name: " + name);
            System.out.println("User Age: " + age);
            System.out.println("User Address: " + address);
            System.out.println("User Phone: " + phone);
        });
    }

    private static CompletableFuture<String> getNameFromAPI() {
        return CompletableFuture.supplyAsync(() -> {
            // Code to get user name from API
            return "John Doe";
        }, exs);
    }

    private static CompletableFuture<Integer> getAgeFromAPI() {
        return CompletableFuture.supplyAsync(() -> {
            // Code to get user age from API
            return 30;
        }, exs);
    }

    private static CompletableFuture<String> getAddressFromAPI() {
        return CompletableFuture.supplyAsync(() -> {
            // Code to get user address from API
            return "123 Main St, Anytown USA";
        }, exs);
    }

    private static CompletableFuture<String> getPhoneFromAPI() {
        return CompletableFuture.supplyAsync(() -> {
            // Code to get user phone from API
            return "555-1234";
        }, exs);
    }
}
