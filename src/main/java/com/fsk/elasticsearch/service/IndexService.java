package com.fsk.elasticsearch.service;

import com.fsk.elasticsearch.helper.Indicies;
import com.fsk.elasticsearch.helper.Utils;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
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

    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);
    private final List<String> INDICIES = List.of(Indicies.VEHICLE_INDEX);
    private final RestHighLevelClient restHighLevelClient;


    @PostConstruct
    public void createManuallyIndicies() {
        recreateIndicies(false);
    }


    public void recreateIndicies(final boolean deleteExisting) {

        final String settings = Utils.loadAssString("static/es-settings.json");

        if (settings == null) {
            LOG.error("Failed to Load index Settings");
            return;
        }

        for (final String indexName : INDICIES) {
            try {
                final boolean indexExists = restHighLevelClient
                        .indices()
                        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);

                if(indexExists) {
                    if (!deleteExisting) {
                        continue;
                    }
                }


                restHighLevelClient.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);

                final String mappings = loadMappings(indexName);

                if (mappings != null) {
                    createIndexRequest.mapping(mappings, XContentType.JSON);
                }

                restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            }catch (final Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

    }

    private String loadMappings(String indexName) {
        final String mappings = Utils.loadAssString("static/mappings/" + indexName + ".json");
        if (mappings == null) {
            LOG.error("Failed to load mappings for index with name '{}'", indexName);
            return null;
        }

        return mappings;
    }

}
