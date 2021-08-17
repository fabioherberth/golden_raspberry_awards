package br.com.fabioherberth.worstmovies.service;

import br.com.fabioherberth.worstmovies.business.CSVNomineesAndWinners;
import br.com.fabioherberth.worstmovies.business.NomineesAndWinnersBusiness;
import br.com.fabioherberth.worstmovies.dto.WinnersDTO;
import br.com.fabioherberth.worstmovies.exception.ResourceNotFoundException;
import br.com.fabioherberth.worstmovies.model.NomineesAndWinners;
import br.com.fabioherberth.worstmovies.repository.NomineesAndWinnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NomineesAndWinnersService {

    @Autowired
    private NomineesAndWinnersBusiness nomineesAndWinnersBusiness;

    @Autowired
    private NomineesAndWinnersRepository nomineesAndWinnersRepository;

    public NomineesAndWinners findNomineesAndWinnersById(Long nomineesId) throws ResourceNotFoundException {
        return nomineesAndWinnersRepository.findById(nomineesId)
                .orElseThrow(() -> new ResourceNotFoundException("Nominees not found for this id :: " + nomineesId));
    }

    public Map<String, Boolean> delete(Long nomineesId) throws ResourceNotFoundException {
        NomineesAndWinners nominees = findNomineesAndWinnersById(nomineesId);

        nomineesAndWinnersRepository.delete(nominees);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    public WinnersDTO getWinners(){
        return nomineesAndWinnersBusiness.getMoviesWinners();
    }

    public List<NomineesAndWinners> getNomineesWinners() {
        return nomineesAndWinnersRepository
                .findByWinner("yes", Sort.by(Sort.Direction.ASC, "producers", "year"));
    }

    public void saveFromCsvFile(List<CSVNomineesAndWinners> fileDataCsv) {
        List<String> producers;

        for (CSVNomineesAndWinners csv : fileDataCsv) {

            producers = Arrays.asList(csv.getProducers().split("[,]+"));

            for (String producer : producers) {
                nomineesAndWinnersRepository.save(new NomineesAndWinners(null, csv.getYear(),
                        csv.getTitle(),
                        csv.getTitle(),
                        producer.trim(),
                        csv.getWinner()));
            }

        }
    }

}
