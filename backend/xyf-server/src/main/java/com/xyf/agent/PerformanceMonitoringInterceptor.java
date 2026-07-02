package com.xyf.agent;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 性能监控拦截器
 */
public class PerformanceMonitoringInterceptor {

    // 使用ConcurrentHashMap存储每个方法的统计信息
    private static final ConcurrentHashMap<String, AtomicLong> methodCallCount = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicLong> methodTotalTime = new ConcurrentHashMap<>();

    /**
     * 拦截方法调用并记录执行时间
     */
    @RuntimeType
    public static Object monitorExecution(@Origin Method method, @AllArguments Object[] args) throws Throwable {
        long startTime = System.nanoTime();

        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();

        try {
            // 执行原始方法
            Object result = method.invoke(null, args);
            return result;
        } finally {
            long endTime = System.nanoTime();
            long executionTime = (endTime - startTime) / 1_000_000; // 转换为毫秒

            // 更新统计信息
            methodCallCount.computeIfAbsent(methodName, k -> new AtomicLong(0)).incrementAndGet();
            methodTotalTime.computeIfAbsent(methodName, k -> new AtomicLong(0)).addAndGet(executionTime);

            // 输出监控信息
            System.out.println(String.format("Performance Monitor: %s executed in %d ms",
                    methodName, executionTime));
        }
    }

    // 提供给外部访问统计信息的方法（可选）
    public static ConcurrentHashMap<String, AtomicLong> getMethodCallCount() {
        return methodCallCount;
    }

    public static ConcurrentHashMap<String, AtomicLong> getMethodTotalTime() {
        return methodTotalTime;
    }
}