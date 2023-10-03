package pl.coderslab.charity;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationService donationService;
    private final UserService userService;

    public HomeController(InstitutionRepository institutionRepository, DonationService donationService, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.donationService = donationService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutions = institutionRepository.findAll(Pageable.ofSize(4)).toList();
        Integer sumOfQuantity = donationService.getSumOfQuantity();
        long sumOfDonations = donationService.getSumOfDonations();

        model.addAttribute("institutions",institutions);
        model.addAttribute("sumOfQuantity", sumOfQuantity);
        model.addAttribute("sumOfDonations", sumOfDonations);
        return "index";
    }


}
