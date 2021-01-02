package org.example.service;

import org.example.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
@Profile("default")
class CloudStorageService extends StorageService {
    @Value("${Storage.cloud.bucket:")
    String bucket;

    @Value("${storage.cloud.access-key:}")
    String accessKey;

    @Value("${storage.cloud.access-secret:}")
    String accessSecret;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        logger.info("Initializing cloud storage with root dir:{}", this.bucket);
    }

//    @Override
//    public InputStream openInputStream(String uri) throws IOException {
//
//        throw new IOException("File not found:" + uri);
//    }
//
//    @Override
//    public String store(String extName, InputStream input) throws IOException {
//
//        throw new IOException("Unable to access cloud storages.");
//    }

}
