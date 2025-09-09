package fr.eni.cave.bo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name="cav_client")
public class Client {
    @Id
    @Column(name = "login", unique = true, nullable = false)
    private String pseudo;

    @Column (name = "password")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;

    @Column(name = "last_name")
    @EqualsAndHashCode.Exclude
    private String nom;

    @Column(name = "first_name")
    @EqualsAndHashCode.Exclude
    private String prenom;


}
