package by.shatunov.computator.controllers;

import by.shatunov.computator.Calculate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalculatorController {

    @RequestMapping(value = "/arithmetic")
    public String arithmetic(HttpServletRequest request, ModelMap model) {

        String expression = request.getParameter("expression");

        if (expression != null && expression.matches("[-+*/\\d.]+")) {
            String result = Calculate.arithmetic(expression);
            model.addAttribute("expression", expression +" = " + result);
        } else {
            model.addAttribute("expression", "Wrong expression");
        }

        return "index";
    }


    @RequestMapping("/quadratic")
    public String quadratic(HttpServletRequest request, ModelMap model) {
        String expression = request.getParameter("expression");

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());


        model.addAttribute("expression", expression);
        return "index";
    }
}
