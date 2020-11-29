package com.quincy.apidesign.interfacesecurity1;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: AuthToken
 *
 * @author: quincy
 * Date: 2020/11/29 下午7:00
 * History:
 */

public class AuthToken {

    /**
     *  有效时间10分钟
     */
    private static final long EXPIRE_INTERVAL = 10 * 60 * 1000L;
    private String token;
    private long  timestamp;

    public AuthToken(String token, long timestamp) {
        this.timestamp = timestamp;
        this.token = token;
    }

    public static AuthToken generate(String baseUrl, String appId, String password, long timestamp) {
        String token = genToken(baseUrl, appId, password, timestamp);
        AuthToken authToken = new AuthToken(token,timestamp);
        return authToken;

    }

    public static String genToken(String baseUrl, String appId, String password, long timestamp){
        try {
            String url = baseUrl + "?appId="+appId + "&timestamp="+timestamp + "&password=" + password;
            byte[] keyBytes = url.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(password.getBytes());
            byte[] result = Base64.getEncoder().encode(rawHmac);
            String token = new String(result, "UTF-8");
            //必须取消掉=号要不然参数解析解析不出来
            return token.replace("=","-0i");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }



    public boolean isExpired() {
        if (timestamp > System.currentTimeMillis() + EXPIRE_INTERVAL) {
            return true;
        }
        return false;
    }

    /**
     * 检测token是否相同
     * @param clientAuthToken
     * @return
     */
    public boolean match(AuthToken clientAuthToken) {
        System.out.println("match: " + clientAuthToken.token + "==" + token);
        if (clientAuthToken != null && clientAuthToken.token.equals(token)){
            return true;
        }
        return false;
    }
}
