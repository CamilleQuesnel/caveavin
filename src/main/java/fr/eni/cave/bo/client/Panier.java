package fr.eni.cave.bo.client;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CAV_SHOPPING_CART")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOPPING_CART_ID")
    private Integer id;

    @Column(name = "ORDER_NUMBER")
    private String numCommande;

    @Column(name = "TOTAL_PRICE", precision = 2)
    private float prixTotal;

    @Column(name = "PAID")
    private boolean paye;


    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private List<LignePanier> lignes = new ArrayList<>();

}
