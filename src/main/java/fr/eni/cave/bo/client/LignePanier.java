package fr.eni.cave.bo.client;


import fr.eni.cave.bo.vin.Bouteille;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "CAV_LINE")
public class LignePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID")
    private Integer id;

    @Column(name = "QUANTITE")
    private int qte_commande;

    @Column(name = "PRICE")
    private float prix;

    @ManyToOne()
    @JoinColumn(name = "BOTTLE_ID")
    @EqualsAndHashCode.Exclude
    private Bouteille bouteille;
}
