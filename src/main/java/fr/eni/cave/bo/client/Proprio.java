package fr.eni.cave.bo.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "CAV_OWNER")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Proprio extends Utilisateur{

    @Column(name = "CLIENT_NUMBER")
    private String siret;
}
