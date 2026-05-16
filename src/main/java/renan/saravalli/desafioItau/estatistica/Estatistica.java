package renan.saravalli.desafioItau.estatistica;


import lombok.Getter;

@Getter

public class Estatistica {
    private final long count;
    private final Double sum;
    private final Double avg;
    private final Double min;
    private final Double max;

    public Estatistica(long count, Double sum, Double avg, Double min, Double max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }
}
