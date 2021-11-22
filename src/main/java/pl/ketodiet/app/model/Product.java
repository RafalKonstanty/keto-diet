package pl.ketodiet.app.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("ALL")
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    @NotBlank(message = "To pole nie może być puste.")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double kcal;
    private double protein;
    private double fat;
    private double carbohydrates;

}
