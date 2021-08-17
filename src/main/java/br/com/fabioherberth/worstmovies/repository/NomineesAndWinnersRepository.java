package br.com.fabioherberth.worstmovies.repository;

import br.com.fabioherberth.worstmovies.model.NomineesAndWinners;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NomineesAndWinnersRepository extends JpaRepository<NomineesAndWinners, Long> {

    List<NomineesAndWinners> findByWinner(String yesOrNo, Sort sort);

    List<NomineesAndWinners> findByTitle(String title);

    List<NomineesAndWinners> findByYear(Integer year);

    void deleteByYear(Integer year);

}
