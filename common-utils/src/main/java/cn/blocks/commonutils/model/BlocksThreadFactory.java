package cn.blocks.commonutils.model;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 *      blocks线程工厂
 *
 * @author Somnus丶y
 * @date 2019/9/24 15:54
 */
public class BlocksThreadFactory implements ThreadFactory {


    private static final AtomicInteger poolNumber   = new AtomicInteger(1);
    private final        ThreadGroup   group;
    private final        AtomicInteger threadNumber = new AtomicInteger(1);
    private final        String        namePrefix;


    public BlocksThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" +
                     poolNumber.getAndIncrement() +
                     "-thread-";
    }

    public BlocksThreadFactory(String type){
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" +
                     poolNumber.getAndIncrement() +
                     "-type-"+type+
                     "-thread-";
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon()){
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }




}
