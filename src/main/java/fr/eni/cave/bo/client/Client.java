package fr.eni.cave.bo.client;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"pseudo"})
@Builder
@Entity
@Table(name="CAV_CLIENT")
public class Client {
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;
}

