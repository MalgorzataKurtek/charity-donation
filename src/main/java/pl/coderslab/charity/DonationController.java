package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class DonationController {

    private CategoryService categoryService;
    private InstitutionService institutionService;
    private DonationService donationService;
    private UserService userService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
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

    @PostMapping("/addDonation")
    public String processDonation(@Valid @ModelAttribute("donation") Donation donation, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        donationService.saveDonation(donation);
        return "form-confirmation";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }


    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        User existingUser = userService.findUserByEmail(user.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "An account with this email address already exists.");
        }


        userService.saveUser(user);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {
        System.out.println("Login successful!");
        return "login";
    }
}
