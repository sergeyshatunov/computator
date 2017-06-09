package by.shatunov.computator;

import by.shatunov.computator.operations.Calculator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    private static final Pattern PATTERN_MULTIPLY_AND_DIVIDE = Pattern.compile("([-]?[\\d.]+)[^-+]*?([*/])[^-+]*?([-]?[\\d.]+)");
    private static final Pattern PATTERN_ADD_AND_SUBTRACT = Pattern.compile("([-]?[\\d.]+)[^-]*?([+\\-])[^-]*?([-]?[\\d.]+)");
    private static final Calculator ADDITION = (Calculator) context.getBean("addition");
    private static final Calculator SUBTRACTION = (Calculator) context.getBean("subtraction");
    private static final Calculator MULTIPLICATION = (Calculator) context.getBean("multiplication");
    private static final Calculator DIVISION = (Calculator) context.getBean("division");

    private static Pattern currentPattern = PATTERN_MULTIPLY_AND_DIVIDE;
    private static Calculator calculator = null;

    public static String arithmetic(String expression) {

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
