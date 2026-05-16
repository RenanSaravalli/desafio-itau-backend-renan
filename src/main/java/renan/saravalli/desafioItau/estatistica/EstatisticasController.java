package renan.saravalli.desafioItau.estatistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import renan.saravalli.desafioItau.transacao.TransacaoRepository;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {
    //Crair rota de estatistica e uma logiaca para trabalhar com os dados

    @Autowired
    private EstatisticaProperties estatisticaProperties;

    @Autowired
    private TransacaoRepository transacaoRepository;


    @GetMapping
    public ResponseEntity calcularEstatistica() {
        // Calcular as transacoes em N segundos
        final var tempoLimite = OffsetDateTime.now().minusSeconds(estatisticaProperties.segundos());


        return ResponseEntity.ok(transacaoRepository.estatistica(tempoLimite));
    }
}
