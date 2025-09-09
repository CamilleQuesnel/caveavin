package fr.eni.cave.bo.client;

import fr.eni.cave.bo.Client;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cav_adress")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_id")
    private Integer id;

    @Column(name = "street")
    private String rue;

    @Column(name = "postal_code")
    private String codePostal;

    @Column(name = "city")
    private String ville;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
}
