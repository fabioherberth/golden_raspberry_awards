package br.com.fabioherberth.worstmovies.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

public interface FileLoader {

    void readerAndSaveFile(Resource resource) throws IOException;
    Resource loadFileWithResourceLoader(ResourceLoader resourceLoader);

}
