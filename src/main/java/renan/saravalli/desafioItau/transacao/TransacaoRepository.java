package renan.saravalli.desafioItau.transacao;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import renan.saravalli.desafioItau.estatistica.EstatisticaDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Repository
@Getter
public class TransacaoRepository {
    private final List<TransacaoDTO> transacaoList = new ArrayList<>();

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

    public EstatisticaDTO estatistica(OffsetDateTime tempoLimite) {
        List<TransacaoDTO> transacaoFilterList = transacaoList.stream()
                .filter(t -> t.getDataHora().isAfter(tempoLimite) || t.getDataHora().isEqual(tempoLimite))
                .toList();

        final DoubleSummaryStatistics summary = transacaoFilterList
                .stream()
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();

        if (transacaoFilterList.isEmpty()) {
            return new EstatisticaDTO(0,0d,0d,0d,0d);
        }

//        double sum = transacaoFilterList.stream().map(TransacaoDTO::getValor).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
//        double avg = sum / transacaoFilterList.stream().count();
//        double min = transacaoFilterList.stream().mapToDouble(t -> t.getValor().doubleValue()).min().orElse(0);
//        double max = transacaoFilterList.stream().mapToDouble(t -> t.getValor().doubleValue()).max().orElse(0);

        return new EstatisticaDTO(summary.getCount(), summary.getSum(), summary.getAverage(), summary.getMin(), summary.getMax());
    }
}
