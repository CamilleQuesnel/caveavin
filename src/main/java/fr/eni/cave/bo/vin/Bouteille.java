package fr.eni.cave.bo.vin;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CAV_BOTTLE")
public class Bouteille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOTTLE_ID")
    private Integer id;

    @Column(name = "NAME")
    @NotBlank(message = "{bottle.name.blank-error}")
    @Size(max = 250, message = "{bottle.name.size-error}")
    private String nom;

    @Column(name = "SPARKLING")
    private boolean petillant;

    @Column(name = "VINTAGE")
    @Size(max = 100, message = "{bottle.vintage.size-error}")
    private String millesime;

    @Column(name = "QUANTITY")
    @Positive(message = "{bottle.quantity.min-error}")
    private int quantite;

    @Column(name = "PRICE")
    @Positive(message = "{bottle.price.min-error}")
    private float prix;

    @ManyToOne()
    @JoinColumn(name = "COLOR_ID")
    @EqualsAndHashCode.Exclude
    @NotNull(message = "{bottle.color.not-null}")
    private Couleur couleur;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @Builder.Default
    @JoinColumn(name = "REGION_ID")
    @EqualsAndHashCode.Exclude
    @NotNull(message = "{bottle.region.not-null}")
    private Region region;

}
