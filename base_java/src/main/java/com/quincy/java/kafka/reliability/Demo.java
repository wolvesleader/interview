package com.quincy.java.kafka.reliability;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Demo
 *
 * @author: quincy
 * Date: 2020/12/26 下午4:26
 * History:
 *                      master  slave1，slave2 slave3
 * ——————————————      ——————————————————————           ————————————
 * |            |      |      消息服务       |           |           |
 * |            |——————|         |         |           |           |
 * | Producer   |      |         |         |           |  Consumer |
 * |            |——————|         |         |           |           |
 * |            |      |      消息存储      |           |           |
 * ——————————————      ————————————————————            ————————————
 * id 发送时间   消息状态
 * 分析上边的消息服务结构
 * 可能存在消息丢失的地方
 * 1.Producer发送到消息服务的时候可能因为网络或者消息服务宕机早晨发送失败
 * 2.存储阶段，可能由于消息服务的错误造成消息无法存储，造成消息丢失
 * 3.
 * 4.
 * 5.
 * 解决方案
 * 1种情况丢失的消息，捕获消息发送的异常，从新发送消息，具体做法可以参考com.quincy.java.kafka.interceptor.CounterInterceptor
 * 2种情况可以通过以下方案来解决
 *  1)对于单个节点的 Broker，需要配置 Broker 参数，在收到消息后，将消息写入磁盘后再给 Producer 返回确认响应，这样即使发生宕机，
 *  由于消息已经被写入磁盘，就不会丢失消息，恢复后还可以继续消费。例如，在 RocketMQ 中，需要将刷盘方式 flushDiskType 配置为
 *  SYNC_FLUSH 同步刷盘。如果是 Broker 是由多个节点组成的集群，需要将 Broker 集群配置成：至少将消息发送到 2 个以上的节点，
 *  再给客户端回复发送确认响应。这样当某个 Broker 宕机时，其他的 Broker 可以替代宕机的 Broker，也不会发生消息丢失
 *  2)消息存入到数据库种的表中，设置消息状态
 *
 */

public class Demo {
}
