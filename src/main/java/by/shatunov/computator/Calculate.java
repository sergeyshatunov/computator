package by.shatunov.computator;

import by.shatunov.computator.operations.Calculator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Calculate {

    private static final Pattern PATTERN_MULTIPLY_AND_DIVIDE = Pattern.compile("([-]?[\\d.]+)[^-+]*?([*/])[^-+]*?([-]?[\\d.]+)");
    private static final Pattern PATTERN_ADD_AND_SUBTRACT = Pattern.compile("([-]?[\\d.]+)[^-]*?([+\\-])[^-]*?([-]?[\\d.]+)");

    @Resource(name = "addition")
    private Calculator ADDITION;

    @Resource(name = "subtraction")
    private Calculator SUBTRACTION;

    @Resource(name = "multiplication")
    private Calculator MULTIPLICATION;

    @Resource(name = "division")
    private Calculator DIVISION;

    private static Pattern currentPattern = PATTERN_MULTIPLY_AND_DIVIDE;
    private Calculator calculator = null;

    public String arithmetic(String expression) {

        while (!expression.matches("[\\d.]+")) {

            Matcher matcher = currentPattern.matcher(expression);

            if (matcher.find()) {
                String a = matcher.group(1);
                String operator = matcher.group(2);
                String b = matcher.group(3);

                switch (operator) {
                    case "+": calculator = ADDITION;
                        break;
                    case "-": calculator = SUBTRACTION;
                        break;
                    case "*": calculator = MULTIPLICATION;
                        break;
                    case "/": calculator = DIVISION;
                        break;
                }

                calculator.setA(Double.valueOf(a));
                calculator.setB(Double.valueOf(b));

                expression = expression.replaceFirst(currentPattern.toString(), calculator.calc().toString());
                expression = arithmetic(expression);

            } else {
                currentPattern = PATTERN_ADD_AND_SUBTRACT;
                matcher = currentPattern.matcher(expression);
                if (matcher.find()) {
                    expression = arithmetic(expression);
                } else {
                    System.out.println("Wrong expression");
                }
                currentPattern = PATTERN_MULTIPLY_AND_DIVIDE;
            }
        }

        if (expression.endsWith(".0")) {
            expression = expression.substring(0, expression.length()-2);
        }

        return expression;
    }
}
