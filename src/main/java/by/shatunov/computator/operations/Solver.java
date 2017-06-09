package by.shatunov.computator.operations;


public class Solver implements Quadratic {

    private Calculator plus;
    private Calculator minus;
    private Calculator multiply;
    private Calculator divide;

    private Double a;
    private Double b;
    private Double c;

    private Double d = calculateDiscriminant(a, b, c);

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

    public Solver() {
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Calculator getPlus() {
        return plus;
    }

    public void setPlus(Calculator plus) {
        this.plus = plus;
    }

    public Calculator getMinus() {
        return minus;
    }

    public void setMinus(Calculator minus) {
        this.minus = minus;
    }

    public Calculator getMultiply() {
        return multiply;
    }

    public void setMultiply(Calculator multiply) {
        this.multiply = multiply;
    }

    public Calculator getDivide() {
        return divide;
    }

    public void setDivide(Calculator divide) {
        this.divide = divide;
    }

    public Double getX(Double a, Double b, Double c, Calculator calc) {
        Double mb = getResult(-1.0, b, multiply);
        Double sqrtD = Math.sqrt(d);
        Double up = getResult(mb, sqrtD, plus);
        Double down = getResult(2.0, a, multiply);
        return getResult(up, down, divide);
    }

    public Double getX1() {

        return 0.0;
    }

    public Double getX2() {
        return 0.0;
    }
}
