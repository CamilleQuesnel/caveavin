package fr.eni.cave.bo.client;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
@Entity
@Table(name = "CAV_ADDRESS")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    Integer id;
    @Column(name = "STREET")
    String rue;
    @Column(name = "POSTL_CODE")
    String codepostal;
    @Column(name = "CITY")
    String ville;
}

