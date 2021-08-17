package br.com.fabioherberth.worstmovies.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class WinnersDTO {

    List<ProducerDTO> min = new ArrayList<>();
    List<ProducerDTO> max = new ArrayList<>();

}
