package com.xinput.es;

import com.alibaba.fastjson.JSON;
import com.xinput.es.entity.User;
import com.xinput.es.util.EsUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 索引
 */
public class EsDocumentDemo {

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
     * 添加文档
     */
    @Test
    public void addDocument() throws IOException {
        // 创建对象
        User user = new User("xinput", 10);

        // 创建请求
        IndexRequest request = new IndexRequest("person");
        // 规则 put /indexName/_doc/1
        request.id("2");
        // 以TimeValue形式设置主分片超时时间
        request.timeout(TimeValue.timeValueSeconds(1));
        // 以String形式设置主分片超时时间
        request.timeout("1s");
        // 使用WriteRequest.RefreshPolicy实例设置刷新策略
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        // 使用String设置刷新策略
        request.setRefreshPolicy("wait_for");
        // ES版本变更以后，这里不能直接传入json了，但是可以使用 map<String,Object>
//        Map<String, Object> map = new HashMap();
//        map.put("name", "xinput");
//        map.put("age", 1);
//        request.source(map);
        request.source(EsUtils.toSource(user));

        // 客户端发送请求，获取响应的结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status());
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 批量添加文档
     */
    @Test
    public void addBatchDocument() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        for (int i = 1; i <= 100; i++) {
            User user = new User("xx_" + i, 10 + i % 10);
            IndexRequest request = new IndexRequest("person");
            bulkRequest.add(request.id("" + i).source(EsUtils.toSource(user)));
        }

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures()); // 是否失败，返回 false 代表 成功！
        System.out.println(JSON.toJSONString(bulkResponse, true));
    }

    /**
     * 获取文档，判断是否存在 get /indexName/_doc/1
     */
    @Test
    public void existDocument() throws IOException {
        GetRequest getRequest = new GetRequest("person", "1");
        // 不获取返回的 _source 的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);

        GetRequest getRequest2 = new GetRequest("person", "2");
        // 不获取返回的 _source 的上下文
        getRequest2.fetchSourceContext(new FetchSourceContext(false));
        getRequest2.storedFields("_none_");
        boolean exists2 = client.exists(getRequest2, RequestOptions.DEFAULT);
        System.out.println(exists2);
    }

    /**
     * 获取文档的信息
     */
    @Test
    public void getDocument() throws IOException {
        GetRequest getRequest = new GetRequest("person", "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse);
    }

    /**
     * 查询
     * SearchRequest 搜索请求
     * SearchSourceBuilder 条件构造
     * HighlightBuilder 构建高亮
     * TermQueryBuilder 精确查询
     */
    @Test
    public void queryDocument() throws IOException {
        SearchRequest searchRequest = new SearchRequest("person");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.highlighter();

        // 查询条件，我们可以使用 QueryBuilders 工具来实现
        // QueryBuilders.termQuery 精确
        // QueryBuilders.matchAllQuery() 匹配所有

        // 匹配 name = xx_10 的数据
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "xx_10");
//        QueryBuilders.matchAllQuery();
//        sourceBuilder.query(termQueryBuilder);

        // resthighlevelclient  分页使用了from，size分页,分页查询的结果最多只返回了10条
        sourceBuilder.from(0);
        sourceBuilder.size(100);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));


        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=================================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }

    /**
     * 更新文档信息
     */
    @Test
    public void updateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("person", "1");
        updateRequest.timeout("1s");

        User user = new User("xinput", 15);
        updateRequest.doc(EsUtils.toSource(user));

        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("person", "2");
        deleteRequest.timeout("1s");
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }
}
