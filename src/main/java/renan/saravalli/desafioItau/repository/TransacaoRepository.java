package renan.saravalli.desafioItau.repository;

import org.springframework.stereotype.Repository;
import renan.saravalli.desafioItau.dto.TransacaoDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {
    List<TransacaoDTO> transacaoList = new ArrayList<>();

    // Salvar os dados em uma lista
    public void salvarTransacao(TransacaoDTO transacao) {
        transacaoList.add(transacao);
    }

    // Apagar essa lista depois de 60 segundos
    public void apagarListAgendada() {

    }

    // Apagar todas as transações da lista
    public void apagarList() {
        transacaoList.clear();
    }
}
