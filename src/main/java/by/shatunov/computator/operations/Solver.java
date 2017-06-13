package by.shatunov.computator.operations;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@Component
public class Solver implements Quadratic {

    @Resource(name = "addition")
    private Calculator plus;

    @Resource(name = "subtraction")
    private Calculator minus;

    @Resource(name = "multiplication")
    private Calculator multiply;

    @Resource(name = "division")
    private Calculator divide;

    private Double a;
    private Double b;
    private Double c;

    public Solver() {
    }

    public Solver(Calculator plus, Calculator minus, Calculator multiply, Calculator divide) {
        this.plus = plus;
        this.minus = minus;
        this.multiply = multiply;
        this.divide = divide;
    }

    @Override
    public Double getX1() {
        return getX(plus);
    }

    @Override
    public Double getX2() {
        return getX(minus);
    }

    private Double getX(Calculator calculator) {
        Double d = calculateDiscriminant(a, b, c);
        Double mb = getResult(-1.0, b, multiply);
        Double sqrtD = Math.sqrt(d);
        Double up = getResult(mb, sqrtD, calculator);
        Double down = getResult(2.0, a, multiply);

        return getResult(up, down, divide);
    }

    private Double calculateDiscriminant(Double a, Double b, Double c) {
        Double bb = getResult(b, b, multiply);
        Double a4 = getResult(4.0, a, multiply);
        Double ac4 = getResult(a4, c, multiply);

        return getResult(bb, ac4, minus);
    }

    private Double getResult(Double a, Double b, Calculator calc) {
        calc.setA(a);
        calc.setB(b);

        return calc.calc();
    }
}
