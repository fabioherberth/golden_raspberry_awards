package br.com.fabioherberth.worstmovies.service;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileLoader {

    void readAndSaveFile(Resource resource) throws IOException;

}
