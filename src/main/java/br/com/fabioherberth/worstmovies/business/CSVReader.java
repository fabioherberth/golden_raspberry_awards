package br.com.fabioherberth.worstmovies.business;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Slf4j
public class CSVReader<T> {

    private static final char CSV_SEPARETOR = ';';

    public List<T> readCSV(InputStream fileInputStream, Class clazz) {

        List<T> result;

        try {
            Reader reader = new InputStreamReader(fileInputStream);
            result = new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withSeparator(CSV_SEPARETOR)
                    .withThrowExceptions(false)
                    .build()
                    .parse();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read expected CSV file!");
        }

        return result;
    }

}
