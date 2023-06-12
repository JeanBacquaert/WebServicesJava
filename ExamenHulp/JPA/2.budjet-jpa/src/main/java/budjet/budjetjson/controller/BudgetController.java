package budjet.budjetjson.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import budjet.budjetjson.jpa.Budget;
import budjet.budjetjson.jpa.BudgetRepository;



@Controller
public class BudgetController {
    @Autowired
    private BudgetRepository budgetRepository;

    @GetMapping("/")
    public String index() {
       return "redirect:/uitgaven";
    }

    @GetMapping("/uitgaven")
    public String uitgaven() {
        return "uitgaven";
    }

    @GetMapping("/maandlimiet")
    public String list(Model model) {
        model.addAttribute("budgets", budgetRepository.findAll());
        return "maandlimiet";
    }

    @PostMapping("/budget")
    public String saveBudget(@RequestParam("amount") int amount, @RequestParam("description") String description,
            @RequestParam("date") Date date, RedirectAttributes redirectAttributes, Model model) {
        //set limit =500
        int limit = 500;
        if(amount < limit) {
            budgetRepository.save(new Budget(amount, description, date));
            model.addAttribute("budgets", budgetRepository.findAll());
            return "maandlimiet";
        } else {
            redirectAttributes.addFlashAttribute("error", "Het bedrag mag niet hoger zijn dan 500");
            return "uitgaven";
        }
    }
}
