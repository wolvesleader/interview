package com.quincy.hbase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@SpringBootTest
class HbaseReviewApplicationTests {

    @Test
    void contextLoads() throws Exception {
        /*Configuration configuration = new Configuration();
        Properties props = PropertiesLoaderUtils.loadAllProperties("hbase.properties");
        String clientPort = props.getProperty("hbase.zookeeper.property.clientPort");
        String quorum = props.getProperty("hbase.zookeeper.quorum");

        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.property.clientPort", clientPort);
        config.set("hbase.zookeeper.quorum", quorum);

        HBaseAdmin.available(config);*/

        String jsonStr = "{\n" +
                "    \"success\": true,\n" +
                "    \"message\": \"操作成功！\",\n" +
                "    \"code\": 200,\n" +
                "    \"result\": {\n" +
                "        \"code\": 0,\n" +
                "        \"data\": {\n" +
                "            \"total\": 36487,\n" +
                "            \"totalPage\": 730,\n" +
                "            \"goodsList\": [\n" +
                "                {\n" +
                "                    \"big\": \"upload/goods/20190807152462681_big.jpg\",\n" +
                "                    \"thumbnail\": \"upload/goods/20190807152462681_thumbnail.jpg\",\n" +
                "                    \"original\": \"upload/goods/20190807152462681.jpg\",\n" +
                "                    \"salesVolume\": 23803,\n" +
                "                    \"origin\": \"荷兰\",\n" +
                "                    \"description\": \"&lt;p&gt;\\r\\n\\t&lt;img src=&quot;/upload/image/2019-08/5d4a8180ad4e7.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a8181b1364.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a81823e55d.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a818400849.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a81847e1ea.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a81853fd82.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a8185c690e.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a8186e6267.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a8188b04cf.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a818935771.png&quot; alt=&quot;&quot; /&gt;\\r\\n&lt;/p&gt;\",\n" +
                "                    \"picLibUrl\": \"http://images.houniao.hk/\",\n" +
                "                    \"spec\": {},\n" +
                "                    \"hasShelfLife\": \"1\",\n" +
                "                    \"categoryIdPath\": \"29_30_32\",\n" +
                "                    \"tiny\": \"upload/goods/20190807152462681_tiny.jpg\",\n" +
                "                    \"isSale\": 0,\n" +
                "                    \"goodsUnit\": \"盒\",\n" +
                "                    \"sku\": \"HN1075540039\",\n" +
                "                    \"category\": [\n" +
                "                        \"母婴用品\",\n" +
                "                        \"奶粉\",\n" +
                "                        \"婴幼奶粉\"\n" +
                "                    ],\n" +
                "                    \"barcode\": \"8711173001870\",\n" +
                "                    \"goodsName\": \"荷兰美素 Hero baby奶粉1段（0-6个月）800 g\",\n" +
                "                    \"brand\": \"荷兰美素herobaby\",\n" +
                "                    \"descriptionImages\": [\n" +
                "                        \"upload/image/2019-08/5d4a8180ad4e7.png\",\n" +
                "                        \"upload/image/2019-08/5d4a8181b1364.png\",\n" +
                "                        \"upload/image/2019-08/5d4a81823e55d.png\",\n" +
                "                        \"upload/image/2019-08/5d4a818400849.png\",\n" +
                "                        \"upload/image/2019-08/5d4a81847e1ea.png\",\n" +
                "                        \"upload/image/2019-08/5d4a81853fd82.png\",\n" +
                "                        \"upload/image/2019-08/5d4a8185c690e.png\",\n" +
                "                        \"upload/image/2019-08/5d4a8186e6267.png\",\n" +
                "                        \"upload/image/2019-08/5d4a8188b04cf.png\",\n" +
                "                        \"upload/image/2019-08/5d4a818935771.png\"\n" +
                "                    ],\n" +
                "                    \"tradeType\": \"保税直供\",\n" +
                "                    \"categoryId\": \"32\",\n" +
                "                    \"goodsSeoKeywords\": \"荷兰美素,美素,herobaby,hero baby,荷兰美素1段,美素一段,美素奶粉,美素1段\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"big\": \"upload/goods/20190807161165502_big.jpg\",\n" +
                "                    \"thumbnail\": \"upload/goods/20190807161165502_thumbnail.jpg\",\n" +
                "                    \"original\": \"upload/goods/20190807161165502.jpg\",\n" +
                "                    \"salesVolume\": 34853,\n" +
                "                    \"origin\": \"荷兰\",\n" +
                "                    \"description\": \"&lt;p&gt;\\r\\n\\t&lt;img src=&quot;/upload/image/2019-08/5d4a87af514ef.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87afaf905.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b01c6a2.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b0876c9.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b0ec3b2.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b1726b3.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b1c2b55.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b227e45.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b289c28.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b3240f2.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b36fda5.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b3c0c60.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b425ef3.png&quot; alt=&quot;&quot; /&gt;&lt;img src=&quot;/upload/image/2019-08/5d4a87b457b2f.png&quot; alt=&quot;&quot; /&gt;\\r\\n&lt;/p&gt;\",\n" +
                "                    \"picLibUrl\": \"http://images.houniao.hk/\",\n" +
                "                    \"spec\": {},\n" +
                "                    \"hasShelfLife\": \"1\",\n" +
                "                    \"categoryIdPath\": \"29_30_32\",\n" +
                "                    \"tiny\": \"upload/goods/20190807161165502_tiny.jpg\",\n" +
                "                    \"isSale\": 0,\n" +
                "                    \"goodsUnit\": \"盒\",\n" +
                "                    \"sku\": \"HN1075540038\",\n" +
                "                    \"category\": [\n" +
                "                        \"母婴用品\",\n" +
                "                        \"奶粉\",\n" +
                "                        \"婴幼奶粉\"\n" +
                "                    ],\n" +
                "                    \"barcode\": \"8711173001887\",\n" +
                "                    \"goodsName\": \"荷兰美素Hero baby奶粉2段（6-10个月）800g\",\n" +
                "                    \"brand\": \"荷兰美素herobaby\",\n" +
                "                    \"descriptionImages\": [\n" +
                "                        \"upload/image/2019-08/5d4a87af514ef.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87afaf905.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b01c6a2.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b0876c9.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b0ec3b2.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b1726b3.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b1c2b55.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b227e45.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b289c28.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b3240f2.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b36fda5.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b3c0c60.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b425ef3.png\",\n" +
                "                        \"upload/image/2019-08/5d4a87b457b2f.png\"\n" +
                "                    ],\n" +
                "                    \"tradeType\": \"保税直供\",\n" +
                "                    \"categoryId\": \"32\",\n" +
                "                    \"goodsSeoKeywords\": \"荷兰美素,美素,herobaby,hero baby,美素2段,婴幼儿奶粉。,荷兰美素2段,纸盒美素2段\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"timestamp\": 1620716378593\n" +
                "}";


        JSONObject object = JSON.parseObject(jsonStr);
        JSONObject jsonObject = object.getJSONObject("result").getJSONObject("data");
        JSONArray goodsList = jsonObject.getJSONArray("goodsList");

        List<HouNiaoGoodsVo> houNiaoGoodsVos = goodsList.toJavaList(HouNiaoGoodsVo.class);



    }

}
