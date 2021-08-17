package br.com.fabioherberth.worstmovies.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProducerDTO {

    private String  producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

}
