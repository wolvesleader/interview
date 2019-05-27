package com.quincy.java.netty.protocolcustom.handler;

import com.quincy.java.netty.protocolcustom.converter.DubboDecoder;
import com.quincy.java.netty.protocolcustom.converter.DubboEncoder;
import com.quincy.java.netty.protocolcustom.protocol.DubboProtocol;

/**
 * author:quincy
 * Date:2019-05-26
 */
public class NettyAdapter {

    private final DubboProtocol dubboProtocol = new DubboProtocol();

    private final DubboEncoder dubboEncoder = new DubboEncoder(dubboProtocol);

    private final DubboDecoder dubboDecoder = new DubboDecoder(dubboProtocol);

    public DubboEncoder getDubboEncoder() {
        return dubboEncoder;
    }

    public DubboDecoder getDubboDecoder() {
        return dubboDecoder;
    }
}
