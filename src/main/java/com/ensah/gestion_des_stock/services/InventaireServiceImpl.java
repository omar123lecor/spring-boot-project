package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.repositories.InventaireRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Service
public class InventaireServiceImpl implements InventaireService {

    @Autowired
    private InventaireRepository inventaireRepository;

    @Override
    public Inventaire ajouter(Inventaire inventaire) {
        return inventaireRepository.save(inventaire);
    }

    @Override
    public List<Inventaire> lister() {
        return inventaireRepository.findAll();
    }

    @Override
    public List<Inventaire> filtrerParEntrepot(Entropot entrepot) {
        return inventaireRepository.findByEntropot(entrepot);
    }

    @Override
    public List<Inventaire> filtrerParDate(Date debut, Date fin) {
        return inventaireRepository.findByDateInventaireBetween(debut, fin);
    }

    @Override
    public List<Inventaire> filtrerParNomEffectuer(String motCle) {
        return inventaireRepository.findByEffectueParContaining(motCle);
    }

    @Override
    public List<Inventaire> afficherEcart() {
        return inventaireRepository.findByEcartIsNotNull();
    }

    public List<Inventaire> searchInventaires(Entropot entrepot, Date d1, Date d2) {
        return inventaireRepository.searchInventaires(entrepot, d1, d2);
    }



    @Override
    public Inventaire getById(Long id) {
        return inventaireRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(Long id) {
        inventaireRepository.deleteById(id);
    }

    @Override
    public void telecharger(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"inventaires.csv\"");

        List<Inventaire> inventaires = inventaireRepository.findAll();

        PrintWriter writer = response.getWriter();
        writer.println("ID,Date,Effectué Par,Validé Par,Écart,Entrepôt");

        for (Inventaire inv : inventaires) {
            writer.printf("%d,%s,%s,%s,%d,%s%n",
                    inv.getId(),
                    inv.getDateInventaire(),
                    inv.getEffectuePar(),
                    inv.getValidePar(),
                    inv.getEcart(),
                    inv.getEntropot().getNom());
        }

        writer.flush();
    }

    @Override
    public List<Inventaire> filtrerParEntrepotEtDates(Entropot entrepot, Date debut, Date fin) {
        return inventaireRepository.searchInventaires(entrepot, debut, fin);
    }

}



