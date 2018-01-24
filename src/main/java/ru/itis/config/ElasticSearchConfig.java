/*package ru.itis.config;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ru.itis.repositories")
public class ElasticSearchConfig {
    @Bean
    NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }

    @Bean
    ElasticsearchOperations elasticsearchOperations() {
        File tempFile = null;

        try {
            tempFile = File.createTempFile("temp-elastic", Long.toString(System.nanoTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Settings.Builder elasticSearchSettings =
                Settings.settingsBuilder()
                        .put("http.enabled", "true")
                        .put("index.number_of_shards", "1")
                        .put("path.data", new File(tempFile, "data").getAbsolutePath())
                        .put("path.logs", new File(tempFile, "logs").getAbsolutePath())
                        .put("path.work", new File(tempFile, "work").getAbsolutePath())
                        .put("path.home", tempFile);

        return new ElasticsearchTemplate(nodeBuilder()
                .local(true)
                .settings(elasticSearchSettings.build())
                .node()
                .client());
    }


}*/