package com.water.neptune.ets.common;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通用线程池
 *
 * @author zhangmiaojie
 */
public class CommonThreadPool {
    private static ThreadPoolExecutor executor = null;
    private static BlockingQueue<Runnable> workQueue = null;
    /**
     * 队列容量
     */
    private final static int THREAD_QUEUE_SIZE = 1000;
    /**
     * 池中线程数
     */
    private final static int CORE_POOL_SIZE = 10;
    /**
     * 最大线程数
     */
    private final static int MAX_POOL_SIZE = 15;

    private static void init() {
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            AtomicInteger counter = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Common-Task-Thread-" + counter.addAndGet(1));
            }
        };
        try {
            if (workQueue == null) {
                workQueue = new ArrayBlockingQueue<>(THREAD_QUEUE_SIZE);
            }
            if (executor == null) {
                executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 10, TimeUnit.SECONDS, workQueue,
                        namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                executor.allowCoreThreadTimeOut(true);
            }
        } catch (Exception t) {
            // log.error("初始化线程池时出现异常......" + t.getMessage(), t);
        }
    }

    public static void execute(Runnable runnable) {
        if (executor == null || workQueue == null) {
            init();
        }
        executor.execute(runnable);
    }

    public static void shutdown() {
        executor.shutdown();
    }

    public static void wait4AllThreadFinished() throws InterruptedException {
        shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }

}
