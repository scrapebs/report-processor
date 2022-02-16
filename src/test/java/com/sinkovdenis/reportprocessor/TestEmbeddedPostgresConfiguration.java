package com.sinkovdenis.reportprocessor;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class TestEmbeddedPostgresConfiguration {

    @Autowired
    private EmbeddedPostgres embeddedPostgres;

    @Bean
    public EmbeddedPostgres getEmbeddedPostgres() throws IOException {
        return EmbeddedPostgres.builder()
                .setServerConfig("search_path", "public")
                .start();
    }

    @Bean
    public DataSource getDataSource() {
        return embeddedPostgres.getPostgresDatabase();
    }

    @PreDestroy
    public void destroy() throws IOException {
        embeddedPostgres.close();
    }
}