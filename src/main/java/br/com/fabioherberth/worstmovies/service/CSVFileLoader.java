package br.com.fabioherberth.worstmovies.service;

import br.com.fabioherberth.worstmovies.business.CSVNomineesAndWinners;
import br.com.fabioherberth.worstmovies.business.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CSVFileLoader extends AbstractFileLoader implements ApplicationRunner {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private NomineesAndWinnersService nomineesAndWinnersService;

    private final CSVReader<CSVNomineesAndWinners> csvReader = new CSVReader<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = loadFileWithResourceLoader(resourceLoader, "classpath:files/movielist.csv");
        readAndSaveFile(resource);
    }

    @Override
    public void readAndSaveFile(Resource resource) throws IOException {
        List<CSVNomineesAndWinners> csvFile = csvReader.readCSV(resource.getInputStream(), CSVNomineesAndWinners.class);
        nomineesAndWinnersService.saveFromCsvFile(csvFile);
    }

}
