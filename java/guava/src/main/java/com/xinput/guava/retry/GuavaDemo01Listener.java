package com.xinput.guava.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;

import java.util.concurrent.ExecutionException;

/**
 * RetryListener 重试监听器
 * <p>
 * Attempt: Attemp既是一次重试请求(call),也是请求的结果，并记录了当前请求的次数、
 * 是否包含异常和请求的返回值
 */
public class GuavaDemo01Listener<Boolean> implements RetryListener {

    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        // 第几次重试,(注意:第一次重试其实是第一次调用)
        System.out.print("[retry] time=" + attempt.getAttemptNumber());

        // 距离第一次重试的延迟
        System.out.print(", delay=" + attempt.getDelaySinceFirstAttempt());

        // 重试结果: 是异常终止, 还是正常返回
        System.out.print(", hasException=" + attempt.hasException());
        System.out.print(", hasResult=" + attempt.hasResult());

        // 是什么原因导致异常
        if (attempt.hasException()) {
            System.out.print(", causeBy=" + attempt.getExceptionCause().toString());
        } else {
            // 正常返回时的结果
            System.out.print(", result=" + attempt.getResult());
        }

        // bad practice: 增加了额外的异常处理代码
        try {
            Boolean result = (Boolean) attempt.get();
            System.out.print(", rude get=" + result);
        } catch (ExecutionException e) {
            System.err.println("this attempt produce exception." + e.getCause().toString());
        }

        System.out.println();
    }
}
