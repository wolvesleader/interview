package com.quincy.java.base.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * author:quincy
 * Date:2019-06-01
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event);
    }
}
