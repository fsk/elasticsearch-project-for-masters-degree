package com.fsk.elasticsearch.service;

import com.fsk.elasticsearch.helper.Indicies;
import com.fsk.elasticsearch.helper.Utils;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Service;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IndexService {

    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);;
    private final List<String> INIDICES_TO_CREATE = List.of(Indicies.VEHICLE_INDEX);
    private final RestHighLevelClient restHighLevelClient;

    @PostConstruct
    public void createManuallyIndicies() {

        final String settings = Utils.loadAssString("static/es-settings.json");

        for (final String indexName : INIDICES_TO_CREATE) {
            try {
                boolean indexExists = restHighLevelClient
                        .indices()
                        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);

                if (indexExists) {
                    continue;
                }

                final String mappings = Utils.loadAssString("static/mappings/" + indexName + ".json");
                if (settings == null || mappings == null) {
                    LOG.error("Filed to create index with name '{}'", indexName);
                    continue;
                }

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);
                createIndexRequest.mapping(mappings, XContentType.JSON);

                restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            }catch (final Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

    }

}
