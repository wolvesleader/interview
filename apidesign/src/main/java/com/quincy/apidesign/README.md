# API接口

### 0.接口编写
* 请求方式
* 请求地址
* 接口入参
* 接口出参
* 接口版本
* 接口同步异步
* 接口文档演示案例

### 1.接口文档编写工具
* swagger2 目前比较常用
* excel
* word
* YApi目前比较常用
### 2.接口开发规范
* 接口的响应要明确表示接口的处理结果

  例如某，接口该接口的业务逻辑是
  
  这个收单服务本身并不真正处理下单操作，只是做一些预校验和预处理；
  真正的下单操作，需要在收单服务内部调用另一个订单服务来处理；
  订单服务处理完成后，会返回订单状态和 ID
  ~~~json
  {
      "success": true,
      "code": 5001,
      "info": "Risk order detected",
      "message": "OK",
      "data": {
        "orderStatus": "Cancelled",
        "orderId": -1
      }
  }
  ~~~
  存在的问题,success=true,message=ok 明显订单成功了,为什么orderStatus=Cancelled 取消了订单了
  会给人造成困惑
  
  造成混乱的原因是在接口内部做了其他接口的请求处理,针对这种情况我们只要遵循
  
  1).对外隐藏内部实现。虽然说收单服务调用订单服务进行真正的下单操作，但是直接接口其实是收单服务提供的
  收单服务不应该“直接”暴露其背后订单服务的状态码、错误描述
  
  2).设计接口结构时，明确每个字段的含义，以及客户端的处理方式
  
  我们从新设计接口的response
  ```java
    @Data
    public class APIResponse<T> {
        private boolean success;
        private T data;
        private int code;
        private String message;
    }
  ```
  接口的设计逻辑
  
  HTTP响应状态码!=200 表示请求没有到达收单服务，或者收单服务存在问题，客户端直接给用户友好提示  
  HTTP响应状态码 ==200 && success == false  
  可能是因为收单服务参数验证错误，也可能是因为订单服务下单操作失败。  
  这时，根据收单服务定义的错误码表和 code，做不同处理。  
  比如友好提示，或是让用户重新填写相关信息，其中友好提示的文字内容可以从 message 中获取  
  HTTP响应状态码 ==200 && success == true  
  解析data数据
  
* 要考虑接口变迁的版本控制策略  
  
  
* 
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

