package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
public class DonationController {

    private CategoryService categoryService;
    private InstitutionService institutionService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.getCategories();
    }

    @ModelAttribute("institutions")
    public List<Institution> addInstitutions() {
        return institutionService.getInstitutions();
    }

    @GetMapping("/addDonation")
    public String addDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

}
