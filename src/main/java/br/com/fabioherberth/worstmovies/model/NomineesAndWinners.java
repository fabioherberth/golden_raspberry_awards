package br.com.fabioherberth.worstmovies.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "nominees_winners")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NomineesAndWinners {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;
    private Integer year;
    private String  title;
    private String  studios;
    private String  producers;
    private String  winner;

}
