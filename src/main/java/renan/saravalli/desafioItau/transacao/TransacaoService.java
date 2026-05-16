package renan.saravalli.desafioItau.transacao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    public void validarTransacao(TransacaoDTO transacao) {
        if (transacao.getValor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro: Isso não é uma transação válida. O valor deve estar presente");
        }

        if (transacao.getDataHora() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro: Isso não é uma transação válida. A data deve estar presente");
        }

        if (transacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Erro: Isso não é uma transação válida. Valor da trasação deve ser maior ou igual a 0");
        }

        if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Erro: Isso não é uma transação válida. Data da transação deve ser menor ou igual que a atual");
        }
    }

}
