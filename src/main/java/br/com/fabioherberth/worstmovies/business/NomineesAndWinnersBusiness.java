package br.com.fabioherberth.worstmovies.business;

import br.com.fabioherberth.worstmovies.dto.ProducerDTO;
import br.com.fabioherberth.worstmovies.dto.WinnersDTO;
import br.com.fabioherberth.worstmovies.model.NomineesAndWinners;
import br.com.fabioherberth.worstmovies.service.NomineesAndWinnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class NomineesAndWinnersBusiness {

    @Autowired
    private NomineesAndWinnersService nomineesAndWinnersService;


    public WinnersDTO getMoviesWinners(){
        List<NomineesAndWinners> byWinners = getValidNominees();

        return sortTheIntervals(calculateInterval(byWinners));
    }

    private List<NomineesAndWinners> getValidNominees() {
        List<NomineesAndWinners> byWinners = nomineesAndWinnersService.getNomineesWinners();

        byWinners.removeAll(getPrizesOnlyOnce(byWinners));

        return byWinners;
    }

    private List<ProducerDTO> calculateInterval(List<NomineesAndWinners> movies){
        NomineesAndWinners winners = movies.get(0);
        List<ProducerDTO> producerDTOS = new ArrayList<>();

        for (int i = 1; i < movies.size(); i++){
            if(winners.getProducers().equals(movies.get(i).getProducers())){
                NomineesAndWinners nominees = movies.get(i);
                producerDTOS.add(new ProducerDTO(nominees.getProducers(),
                        (nominees.getYear() - winners.getYear()),
                        winners.getYear(),
                        nominees.getYear()));

            }
            winners = movies.get(i);
        }

        return producerDTOS;
    }

    private WinnersDTO sortTheIntervals(List<ProducerDTO> producerDTOS){
        WinnersDTO winnersDTO = new WinnersDTO();
        Collections.sort(producerDTOS, Comparator.comparing(ProducerDTO::getInterval));

        winnersDTO.setMin(filterIntervals(producerDTOS, 0));
        winnersDTO.setMax(filterIntervals(producerDTOS, producerDTOS.size() - 1));

        return winnersDTO;
    }

    private List<ProducerDTO> filterIntervals(List<ProducerDTO> producerDTOS, Integer interval) {
        return producerDTOS.stream()
                           .filter(producerDTO -> producerDTOS.get(interval)
                                                              .getInterval()
                                                              .equals(producerDTO.getInterval()))
                           .collect(Collectors.toList());
    }

    private List<NomineesAndWinners> getPrizesOnlyOnce(List<NomineesAndWinners> movies){
        List<NomineesAndWinners> moviesNotDuplicates = new ArrayList<>();

        Set<NomineesAndWinners> onlyOnce = movies
                .stream()
                .filter(movie -> movies
                        .stream()
                        .filter(x -> x.getProducers().equals(movie.getProducers()))
                        .count() == 1)
                .collect(Collectors.toSet());

        moviesNotDuplicates.addAll(onlyOnce);
        return moviesNotDuplicates;
    }

}
