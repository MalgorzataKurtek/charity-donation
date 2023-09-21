package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationService donationService;

    public HomeController(InstitutionRepository institutionRepository, DonationService donationService) {
        this.institutionRepository = institutionRepository;
        this.donationService = donationService;
    }


    @GetMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutions = institutionRepository.findAll();
        Integer sumOfQuantity = donationService.getSumOfQuantity();

        model.addAttribute("institutions",institutions);
        model.addAttribute("sumOfQuantity", sumOfQuantity);
        return "index";
    }

}
