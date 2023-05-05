package com.xinput.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试es连接是否正常
 * es 7.6.2
 * ES 端口 9200 和 9300 的区别 ？
 * 9200 作为 Http 协议，主要用于外部通讯
 * 9300 作为 Tcp 协议，jar 之间就是通过 tcp 协议通讯
 * ES集群之间是通过 9300 进行通讯的
 */
public class EsInitDemo {

    private String ip;
    private int port;

    @Before
    public void init() {
        this.ip = "127.0.0.1";
        this.port = 9300;
    }

    /**
     * 测试连接 es
     */
    @Test
    public void esClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ip, port, "http"))
        );
        System.out.println(client.toString());
    }
}
