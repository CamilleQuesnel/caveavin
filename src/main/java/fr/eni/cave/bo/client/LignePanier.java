package fr.eni.cave.bo.client;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode (of = {"id"})
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
}
