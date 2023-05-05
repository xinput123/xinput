package com.xinput.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * 索引
 */
public class EsIndexDemo {

    private String ip;
    private int port;
    private RestHighLevelClient client;

    @Before
    public void init() {
        this.ip = "127.0.0.1";
        this.port = 9200;
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ip, port, "http"))
        );
    }

    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest("book");
        try {
            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断索引是否存在
     */
    @Test
    public void existIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("book");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
        GetIndexRequest request2 = new GetIndexRequest("book2");
        boolean exists2 = client.indices().exists(request2, RequestOptions.DEFAULT);
        System.out.println(exists2);
    }

    /**
     * 删除索引
     */
    @Test
    public void delIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("book");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(JSON.toJSONString(response));
    }
}
