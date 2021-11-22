package pl.ketodiet.app.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "userEntity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "To pole nie może być puste")
    @Size(min = 5, message = "Nazwa musi posiadać przynajmniej 5 znaków")
    private String name;
    @NotBlank(message = "To pole nie może być puste")
    private String password;
    private double tdee;

    @OneToMany(mappedBy = "userEntity")
    private List<Product> product;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tdee=" + tdee +
                '}';
    }
}
