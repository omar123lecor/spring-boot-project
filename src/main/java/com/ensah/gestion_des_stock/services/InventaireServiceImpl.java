package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.DTO.Invtdto;
import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import com.ensah.gestion_des_stock.repositories.InventaireRepository;
import com.ensah.gestion_des_stock.repositories.ReceptionRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

@Service
public class InventaireServiceImpl implements InventaireService {

    @Autowired
    private InventaireRepository inventaireRepository;

    @Autowired
    private EntropotService entropotService;

    @Autowired
    private ReceptionService receptionService;

    @Autowired
    private ReceptionRepository receptionRepository;

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

    @Override
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
    public void telecharger(String codeEntrepot, HttpServletResponse response) throws IOException {
        Entropot entropot = entropotService.getEntropotByCode(codeEntrepot);

        if (entropot == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Entrepôt introuvable");
            return;
        }

        List<Reception> receptions = receptionService.filtrerParEntrepot(entropot);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"receptions_" + codeEntrepot + ".csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Nom,Date,Source,Type,Quantité,Unité,Entrepôt");

        for (Reception rec : receptions) {
            writer.printf("%d,%s,%s,%s,%s,%d,%s,%s%n",
                    rec.getId(),
                    rec.getNom(),
                    rec.getDateReception(),
                    rec.getSource(),
                    rec.getType(),
                    rec.getQte(),
                    rec.getUnite(),
                    rec.getEntropot().getNom()
            );
        }

        writer.flush();
    }

    @Override
    public List<Inventaire> filtrerParEntrepotEtDates(Entropot entrepot, Date debut, Date fin) {
        return inventaireRepository.searchInventaires(entrepot, debut, fin);
    }
    
    @Override
    public List<Invtdto> processInventoryFile(MultipartFile file, String codeEntrepot) {
        List<Invtdto> resultats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            Map<String, Long> quantitesApres = new HashMap<>();
            Map<String, String> unitesApres = new HashMap<>();

            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] columns = line.split(",");

                // Vérifier que la ligne a assez de colonnes (au moins 8)
                if (columns.length < 8) {
                    continue; // ignorer les lignes incomplètes
                }

                try {
                    String produit = columns[1].trim(); // Nom (index 1)
                    String unite = columns[6].trim();   // Unité (index 6)

                    // Quantité est à l'index 5
                    Long qteApres;
                    try {
                        qteApres = Long.parseLong(columns[5].trim());
                    } catch (NumberFormatException e) {
                        // Si la conversion échoue, ignorer cette ligne
                        continue;
                    }

                    quantitesApres.put(produit, qteApres);
                    unitesApres.put(produit, unite);
                } catch (Exception e) {
                    // Logger l'erreur si nécessaire
                    continue; // Passer à la ligne suivante en cas d'erreur
                }
            }

            Entropot entropot = entropotService.getEntropotByCode(codeEntrepot);
            if (entropot == null) {
                throw new RuntimeException("Entrepôt non trouvé avec le code: " + codeEntrepot);
            }

            List<Reception> receptions = receptionRepository.findByEntropot(entropot);

            Map<String, Long> quantitesAvant = new HashMap<>();
            Map<String, String> unitesAvant = new HashMap<>();

            for (Reception r : receptions) {
                quantitesAvant.put(r.getNom(), r.getQte());
                unitesAvant.put(r.getNom(), r.getUnite());
            }

            Set<String> tousLesProduits = new HashSet<>();
            tousLesProduits.addAll(quantitesApres.keySet());
            tousLesProduits.addAll(quantitesAvant.keySet());

            for (String produit : tousLesProduits) {
                Long qteApres = quantitesApres.getOrDefault(produit, 0L);
                Long qteAvant = quantitesAvant.getOrDefault(produit, 0L);
                String unite = unitesApres.getOrDefault(produit,
                        unitesAvant.getOrDefault(produit, "N/A"));

                Invtdto dto = new Invtdto();
                dto.setProduit(produit);
                dto.setUnite(unite);
                dto.setQuantiteAvant(qteAvant);
                dto.setQuantiteApres(qteApres);
                dto.setEcart(Math.abs(qteApres - qteAvant));

                resultats.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du traitement du fichier d'inventaire", e);
        }

        return resultats;
    }
}
