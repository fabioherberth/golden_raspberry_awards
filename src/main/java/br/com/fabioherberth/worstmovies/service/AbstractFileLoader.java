package br.com.fabioherberth.worstmovies.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public abstract class AbstractFileLoader implements FileLoader {

    public Resource loadFileWithResourceLoader(ResourceLoader resourceLoader, String filePath) {
        return resourceLoader.getResource(filePath);
    }

}
