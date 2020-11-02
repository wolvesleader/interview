线程池面试专题讲解
1.线程池 用来创建线程的容器 怎么创建线程 存放在哪里
2.线程池作用 和 线程区别 线程管理  new Thread()
3.线程池的创建方式
Executors工具类提供
newSingleThreadExecutor newFixedThreadExecutor()
newCacheThreadExecutor newScheduleThreadExecutor
自定义
基于ThreadPoolExecutor 或者自己写
4.使用自定义的线程池还是提供的线程池怎么选择
5.线程池中的核心参数
==================================================
6.线程池执行流程
7.线程池中能获取到线程么
8.线程池拒绝策略
9.线程池回收策略
10.线程池生命周期
 * The runState provides the main lifecycle control, taking on values:
 *
 *   RUNNING:  Accept new tasks and process queued tasks
 *   SHUTDOWN: Don't accept new tasks, but process queued tasks
 *   STOP:     Don't accept new tasks, don't process queued tasks,
 *             and interrupt in-progress tasks
 *   TIDYING:  All tasks have terminated, workerCount is zero,
 *             the thread transitioning to state TIDYING
 *             will run the terminated() hook method
 *   TERMINATED: terminated() has completed
11.线程池在开发中的应用 1.读excel文件
12.开发中为什么选择线程池，不选择消息队列
13.线程池为什么会丢失任务
14.线程池丢失任务解决方案

自己学习
线程池executor.execute(); 和executor.submit()区别
线程池 executor.shutdown(); executor.shutdownNow() 区别
自己总结线程池在项目中的应用