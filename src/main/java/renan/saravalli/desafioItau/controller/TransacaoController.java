package renan.saravalli.desafioItau.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import renan.saravalli.desafioItau.dto.TransacaoDTO;
import renan.saravalli.desafioItau.repository.TransacaoRepository;
import renan.saravalli.desafioItau.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @PostMapping
    public ResponseEntity adicionar(@RequestBody TransacaoDTO transacao) {

        try {
            transacaoService.validarTransacao(transacao);
            transacaoRepository.salvarTransacao(transacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e ) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deletar() {
        transacaoRepository.apagarList();
        return ResponseEntity.ok().build();
    }

}
