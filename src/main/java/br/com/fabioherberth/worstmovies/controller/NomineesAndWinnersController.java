package br.com.fabioherberth.worstmovies.controller;

import br.com.fabioherberth.worstmovies.dto.WinnersDTO;
import br.com.fabioherberth.worstmovies.exception.ResourceNotFoundException;
import br.com.fabioherberth.worstmovies.model.NomineesAndWinners;
import br.com.fabioherberth.worstmovies.repository.NomineesAndWinnersRepository;
import br.com.fabioherberth.worstmovies.service.NomineesAndWinnersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(value = "API REST Indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.")
@CrossOrigin(origins = "*")
public class NomineesAndWinnersController {

    @Autowired
    private NomineesAndWinnersRepository nomineesRepository;

    @Autowired
    private NomineesAndWinnersService nomineesAndWinnersService;

    @GetMapping("/winners")
    @ApiOperation(value = "Retorna uma lista de vencedores com intervalos maiores e menores entre prêmios.")
    public ResponseEntity<WinnersDTO> getWinners() {
        return ResponseEntity.ok().body(nomineesAndWinnersService.getWinners());
    }

    @GetMapping("/nominees")
    @ApiOperation(value = "Retorna a lista completa dos indicados.")
    public List<NomineesAndWinners> getAllNominees() {
        return nomineesRepository.findAll();
    }

    @GetMapping("/nominees/{id}")
    @ApiOperation(value = "Retorna um indicado pelo ID informado.")
    public ResponseEntity<NomineesAndWinners> getAllNomineesById(@PathVariable(value = "id") Long nomineesId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(nomineesAndWinnersService.findNomineesAndWinnersById(nomineesId));
    }

    @DeleteMapping("/nominees/{id}")
    @ApiOperation(value = "Deleta o indicado pelo ID informado.")
    public Map<String, Boolean> deleteNominees(@PathVariable(value = "id") Long nomineesId) throws ResourceNotFoundException {
        return nomineesAndWinnersService.delete(nomineesId);
    }

}
