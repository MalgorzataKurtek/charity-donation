package pl.coderslab.charity.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonationController {
    @GetMapping("/addDonation")
    public String addDonation() {
        return "form";
    }
}
