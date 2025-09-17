package fr.eni.cave.controller;


import fr.eni.cave.bll.BouteilleService;
import fr.eni.cave.bo.vin.Bouteille;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/caveavin/bouteilles")
public class BouteilleController {

    private final BouteilleService bouteilleService;

    @GetMapping
    public ResponseEntity getBouteilles() {
        final List <Bouteille> bouteilles = bouteilleService.chargerToutesBouteilles();
        if(bouteilles == null || bouteilles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bouteilles);
    }


    @GetMapping("/{id}")
    public ResponseEntity getBouteilleParId(@PathVariable("id")String idPath) {

        try {
            int id = Integer.parseInt(idPath);


            Bouteille bouteille = bouteilleService.chargerBouteilleParId(id);

            return ResponseEntity.ok(bouteille);

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("votre identifiant n'est pas un entier");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/region/{id}")
    public ResponseEntity getBouteilleParRegion(@PathVariable("id")String idPath) {

        try{
            int id = Integer.parseInt((idPath));

            List<Bouteille> bouteilles = bouteilleService.chargerBouteillesParRegion(id);

            return ResponseEntity.ok(bouteilles);
        }catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("votre identifiant n'est pas un entier");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("couleur/{id}")
    public ResponseEntity getBouteilleParCouleur(@PathVariable("id")String idPath) {
        try {
            int id = Integer.parseInt((idPath));

            List<Bouteille> bouteilles = bouteilleService.chargerBouteillesParCouleur(id);
            return ResponseEntity.ok(bouteilles);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("votre identifiant n'est pas un entier");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity ajouterBouteille(@Valid @RequestBody Bouteille b) {
        if (b == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La bouteille est obligatoire");
        }

        if (b.getId() != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Impossible de sauver  votre bouteille");
        }

        try {
            final Bouteille bDB = bouteilleService.ajouter(b);
            return ResponseEntity.ok(bDB);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity miseAJourBouteille(@Valid @RequestBody Bouteille b) {
        if (b == null || b.getId() == null || b.getId() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("La bouteille est obligatoire et l'identifiant sont obligatoires");
        }

        try {
            final Bouteille bDB = bouteilleService.ajouter(b);
            return ResponseEntity.ok(bDB);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity supprimerBouteille(@PathVariable("id") String idPath) {
        try {
            final int idBouteille = Integer.parseInt(idPath);
            bouteilleService.supprimer(idBouteille);
            return ResponseEntity.ok("Bouteille (id : " + idBouteille + ") est supprimée");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre identifiant n'est pas un entier");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
