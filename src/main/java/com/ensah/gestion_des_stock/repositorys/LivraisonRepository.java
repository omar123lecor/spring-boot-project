package com.ensah.gestion_des_stock.repositorys;

import com.ensah.gestion_des_stock.model.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    List<Livraison> findByDateLiv(Date dateLiv);
    List<Livraison> findByNom(String nom); // hérité de Produit
    List<Livraison> findByDestination(String destination);
    @Query("SELECT l FROM Livraison l WHERE (:nom IS NULL OR l.nom = :nom) AND (:entrepot IS NULL OR l.source = :entrepot) AND (:dateLiv IS NULL OR l.dateLiv = :dateLiv)")
    List<Livraison> chercherLivraisons(
            @Param("nom") String nom,
            @Param("entrepot") String entrepot,
            @Param("dateLiv") Date dateLiv
    );
}
