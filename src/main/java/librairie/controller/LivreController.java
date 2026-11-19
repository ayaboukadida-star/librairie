package librairie.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import librairie.model.Livre;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final List<Livre> livres = new ArrayList<>();

    public LivreController() {
        livres.add(new Livre(1L, "Les Misérables", "Victor Hugo", 1862));
        livres.add(new Livre(2L, "Notre-Dame de Paris", "Victor Hugo", 1831));
        livres.add(new Livre(3L, "L'Étranger", "Albert Camus", 1942));
        livres.add(new Livre(4L, "La Peste", "Albert Camus", 1947));
        livres.add(new Livre(5L, "Le Petit Prince", "Antoine de Saint-Exupéry", 1943));
    }

    @GetMapping
    public List<Livre> lister(
            @RequestParam(name = "auteur", required = false) String auteur,
            @RequestParam(name = "anneeMin", required = false) Integer anneeMin) {
        return livres.stream()
                .filter(l -> auteur == null
                        || l.getAuteur().toLowerCase().contains(auteur.toLowerCase()))
                .filter(l -> anneeMin == null || l.getAnnee() >= anneeMin)
                .toList();
    }

    @GetMapping("/nombre")
    public int nombre() {
        return livres.size();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> trouver(@PathVariable("id") Long id) {
        return livres.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}