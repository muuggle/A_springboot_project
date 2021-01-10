package org.example.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
//@Profile("default")
public class LocalStorageService extends StorageService {
    @Value("${storage.local:/var/static}")
    String localStorageRootDir;

    final Logger logger = LoggerFactory.getLogger(getClass());

    private File localStorageRoot;

    @PostConstruct
    public void init() {
        logger.info("Intializing local storage with root dir: {}", this.localStorageRootDir);
        this.localStorageRoot = new File(this.localStorageRootDir);

    }

//    @Override
//    public InputStream openInputStream(String uri) throws IOException {
//        File targetFile = new File(this.localStorageRoot, uri);
//        return new BufferedInputStream(new FileInputStream(targetFile));
//    }

//    @Override
//    public String store(String extName,InputStream input) throws IOException{
//        String fileName= UUID.randomUUID().toString()+"."+extName;
//        File targetFile =new File(this.localStorageRoot,fileName);
//        try(OutputStream output=new BufferedOutputStream(new FileOutputStream(targetFile))){
//            input.transferTo(output);
//        }
//        return fileName;
//    }
}
