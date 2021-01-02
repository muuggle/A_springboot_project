package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("storage.local")
public class StorageConfiguration {
    String rootDir;
    int maxsize;
    boolean allowEmpty;
    List<String> allowTypes;

}
