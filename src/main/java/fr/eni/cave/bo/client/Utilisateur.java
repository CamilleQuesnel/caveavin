package fr.eni.cave.bo.client;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "CAV_USER")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"pseudo"})
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @Column(name="LOGIN", unique=true, nullable=false)
    private String pseudo;

    @ToString.Exclude
    @Column(name="PASSWORD")
    private String password;

    @Column(name="LAST_NAME")
    private String nom;

    @Column(name="FIRST_NAME")
    private String prenom;
}
