package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "donation")
@Setter
@Getter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Ilość nie może być pusta")
    @Min(value = 1, message = "Minimalna ilość to 1")
    private Integer quantity;
    @NotNull(message = "Musisz wybrać co najmniej jedną kategorię")
    @Size(min = 1, message = "Musisz wybrać co najmniej jedną kategorię")
    @ManyToMany
    @JoinTable(name = "donation_category",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    @NotNull(message = "Musisz wybrać instytucję")
    @ManyToOne
    private Institution institution;
    @NotBlank(message = "Ulica nie może być pusta")
    private String street;
    @NotBlank(message = "Miasto nie może być puste")
    private String city;
    @NotBlank(message = "Kod pocztowy nie może byc pusty")
    private String zipCode;
    @NotNull(message = "Musisz wybrać datę odbioru")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate pickUpDate;
    @NotNull(message = "Musisz wybrać godzinę odbioru")
    private LocalTime pickUpTime;
    private String pickUpComment;
    @NotBlank(message = "Numer telefonu nie może być pusty")
    private String phone;
}
