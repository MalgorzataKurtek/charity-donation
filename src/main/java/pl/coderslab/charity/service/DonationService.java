package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {
    private final DonationRepository donationRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Integer getSumOfQuantity() {
        return donationRepository.sumOfQuantity().orElse(0);
    }

    public long getSumOfDonations() {
        return donationRepository.count();
    }

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }
}
