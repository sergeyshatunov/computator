package by.shatunov.computator.operations;

import lombok.Data;

@Data
public class Subtraction implements Calculator {

    private Double a;
    private Double b;

    public Subtraction() {
    }

    public Subtraction(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Double calc() {
        return a - b;
    }
}
