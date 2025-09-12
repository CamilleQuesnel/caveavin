package fr.eni.cave.bo.client;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="CAV_CLIENT")
public class Client extends Utilisateur {


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;
}

