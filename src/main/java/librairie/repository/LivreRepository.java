package librairie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import librairie.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
}