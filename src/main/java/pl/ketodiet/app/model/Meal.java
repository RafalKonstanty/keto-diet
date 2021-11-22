package pl.ketodiet.app.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meal_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDate date;
    private int count;
}
