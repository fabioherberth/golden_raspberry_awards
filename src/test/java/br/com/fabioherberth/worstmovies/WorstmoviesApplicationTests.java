package br.com.fabioherberth.worstmovies;

import br.com.fabioherberth.worstmovies.dto.ProducerDTO;
import br.com.fabioherberth.worstmovies.dto.WinnersDTO;
import br.com.fabioherberth.worstmovies.model.NomineesAndWinners;
import br.com.fabioherberth.worstmovies.repository.NomineesAndWinnersRepository;
import br.com.fabioherberth.worstmovies.service.CSVFileLoader;
import br.com.fabioherberth.worstmovies.service.NomineesAndWinnersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class WorstmoviesApplicationTests {

	@Autowired
	private NomineesAndWinnersService nomineesAndWinnersService;

	@Autowired
	NomineesAndWinnersRepository repository;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	CSVFileLoader dataLoader;

	@BeforeEach
	void setup() throws IOException {
		repository.deleteAll();
		dataLoader.readAndSaveFile(loadCsvFileWithResourceLoader());
	}

	@Test
	void checkSavedRecordsTest() {
		assertTrue(repository.count() > 0);
	}

	@Test
	void checkRecordByTitleTest() {
		String title = "Cruising";
		NomineesAndWinners nomineesAndWinners = repository.findByTitle(title).get(0);
		assertEquals(title, nomineesAndWinners.getTitle());
	}

	@Test
	void checkRecordNotFondByTitleTest(){
		String title = "Ocean's Eleven";
		assertThrows(IndexOutOfBoundsException.class, () -> {
			repository.findByTitle(title).get(0);
		});
	}

	@Test
	void producerWithLongerRangeTest() {
		ProducerDTO producerDTO = new ProducerDTO("Matthew Vaughn", 13, 2002, 2015);
		WinnersDTO winner  		= nomineesAndWinnersService.getWinners();

		assertEquals(producerDTO, winner.getMax().get(0));
	}

	@Test
	void producerWithShorterRangeTest(){
		ProducerDTO producerDTO = new ProducerDTO("Joel Silver", 1, 1990, 1991);
		WinnersDTO winners 		= nomineesAndWinnersService.getWinners();

		assertEquals(producerDTO, winners.getMin().get(0));
	}

	@Test
	void twoProducerWithLongerRangeTest(){
		List<NomineesAndWinners> winners = Arrays.asList(new NomineesAndWinners(null, 2010, "Challenge", "Movies SA.", "Fulano", "yes"),
														 new NomineesAndWinners(null, 2023, "Bug a Movie", "Movies LTDA", "Fulano", "yes"));

		repository.saveAll(winners);

		WinnersDTO winner = nomineesAndWinnersService.getWinners();

		assertTrue(winner.getMax().size() == 2);
	}

	@Test
	void twoProducerWithShorterRangeTest(){
		List<NomineesAndWinners> winners = Arrays.asList(new NomineesAndWinners(null, 2010, "Challenge", "Movies SA.", "Fulano", "yes"),
														 new NomineesAndWinners(null, 2011, "Bug a Movie", "Movies LTDA", "Fulano", "yes"));

		repository.saveAll(winners);

		WinnersDTO winner = nomineesAndWinnersService.getWinners();

		assertTrue(winner.getMin().size() == 2);
	}

	@Test
	@Transactional
	void checkDeletedRecordByYear() {
		Integer year = 1980;
		repository.deleteByYear(1980);

		assertThrows(IndexOutOfBoundsException.class, () -> {
			repository.findByYear(year).get(0);
		});

	}

	public Resource loadCsvFileWithResourceLoader() {
		return resourceLoader.getResource("classpath:movielist.csv");
	}

}
