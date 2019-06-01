package com.quincy.java.base.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * author:quincy
 * Date:2019-06-01
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
