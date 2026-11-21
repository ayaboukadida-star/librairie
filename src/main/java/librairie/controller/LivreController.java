package librairie.controller;

import java.util.List;
import java.net.URI;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import librairie.model.Livre;
import librairie.service.LivreService;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    // Injection par constructeur : Spring fournit le service
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public List<Livre> lister(
            @RequestParam(name = "auteur", required = false) String auteur) {
        return livreService.lister(auteur);
    }

    @GetMapping("/nombre")
    public int compter() {
        return livreService.compter();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> trouver(@PathVariable("id") Long id) {
        return livreService.trouver(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Livre> creer(@RequestBody Livre livre) {
        Livre cree = livreService.enregistrer(livre);
        URI adresse = URI.create("/api/livres/" + cree.getId());
        return ResponseEntity.created(adresse).body(cree);
    }
}