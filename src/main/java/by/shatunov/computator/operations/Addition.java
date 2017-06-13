package by.shatunov.computator.operations;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Addition implements Calculator {

    private Double a;
    private Double b;

    public Addition() {
    }

    public Addition(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Double calc() {
        return a + b;
    }
}
