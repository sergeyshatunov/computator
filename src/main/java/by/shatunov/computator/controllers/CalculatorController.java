package by.shatunov.computator.controllers;

import by.shatunov.computator.Calculate;
import by.shatunov.computator.operations.Solver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class CalculatorController {

    @Resource
    private Solver solver;

    @Resource
    private Calculate calculate;

    @RequestMapping(value = "/arithmetic")
    public String arithmetic(ModelMap model, @RequestParam("expression") String expression) {

        if (expression != null && expression.matches("[-+*/\\d.]+")) {
            String result = calculate.arithmetic(expression);
            model.addAttribute("expression", expression + " = " + result);
        } else {
            model.addAttribute("expression", "Wrong expression");
        }

        return "index";
    }

    @RequestMapping("/quadratic")
    public String quadratic(Model model,
                            @RequestParam("a") Double a,
                            @RequestParam("b") Double b,
                            @RequestParam("c") Double c) {

        solver.setA(a);
        solver.setB(b);
        solver.setC(c);
        Double resultX1 = solver.getX1();
        Double resultX2 = solver.getX2();

        model.addAttribute("resultX1", resultX1.toString());
        model.addAttribute("resultX2", resultX2.toString());

        return "index";
    }
}
