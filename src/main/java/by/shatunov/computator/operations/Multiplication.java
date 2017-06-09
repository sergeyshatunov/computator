package by.shatunov.computator.operations;

import lombok.Data;

@Data
public class Multiplication implements Calculator {

    private Double a;
    private Double b;

    public Multiplication() {
    }

    public Multiplication(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Double calc() {
        return a * b;
    }
}
