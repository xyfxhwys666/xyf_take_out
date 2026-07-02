package com.xyf.agent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 性能监控Java Agent
 * 用于监控应用程序的关键方法执行时间
 */
public class PerformanceMonitorAgent {

    // 统计信息存储
    private static final ConcurrentHashMap<String, AtomicLong> methodCallCount = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicLong> methodTotalTime = new ConcurrentHashMap<>();

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Performance Monitor Agent starting...");

        // 监控所有控制器层的方法
        new AgentBuilder.Default(new ByteBuddy())
                .type(ElementMatchers.nameStartsWith("com.xyf.controller"))
                .transform(new AgentBuilder.Transformer() {
                    @Override
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                           TypeDescription typeDescription,
                                                           ClassLoader classLoader,
                                                           JavaModule module) {
                        return builder.method(ElementMatchers.isPublic()
                                .and(ElementMatchers.not(ElementMatchers.isConstructor()))
                                .and(ElementMatchers.named("execute")
                                        .or(ElementMatchers.named("handle")
                                                .or(ElementMatchers.named("process")))))
                                .intercept(net.bytebuddy.implementation.MethodDelegation.to(
                                        PerformanceMonitoringInterceptor.class));
                    }
                })
                .installOn(inst);

        // 监控服务层的方法
        new AgentBuilder.Default(new ByteBuddy())
                .type(ElementMatchers.nameStartsWith("com.xyf.service.impl"))
                .transform(new AgentBuilder.Transformer() {
                    @Override
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                           TypeDescription typeDescription,
                                                           ClassLoader classLoader,
                                                           JavaModule module) {
                        return builder.method(ElementMatchers.isPublic()
                                .and(ElementMatchers.not(ElementMatchers.isConstructor())))
                                .intercept(net.bytebuddy.implementation.MethodDelegation.to(
                                        PerformanceMonitoringInterceptor.class));
                    }
                })
                .installOn(inst);

        System.out.println("Performance Monitor Agent started successfully");

        // 添加关闭钩子，打印最终统计结果
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            printFinalStats();
        }));
    }

    private static void printFinalStats() {
        System.out.println("\n=== Performance Monitoring Final Statistics ===");
        for (String method : methodCallCount.keySet()) {
            long count = methodCallCount.get(method).get();
            long totalTime = methodTotalTime.get(method).get();
            double avgTime = totalTime / (double) count;

            System.out.printf("%s: %d calls, total time: %d ms, avg time: %.2f ms%n",
                    method, count, totalTime, avgTime);
        }
        System.out.println("==========================================\n");
    }
}