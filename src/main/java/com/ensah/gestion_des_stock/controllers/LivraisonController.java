package com.ensah.gestion_des_stock.controllers;

import com.ensah.gestion_des_stock.DTO.LivraisonForm;
import com.ensah.gestion_des_stock.model.Commande;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Livraison;
import com.ensah.gestion_des_stock.services.CommandeService;
import com.ensah.gestion_des_stock.services.EntropotService;
import com.ensah.gestion_des_stock.services.LivraisonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @Autowired
    private EntropotService entropotService;

    @Autowired
    private CommandeService commandeService;

    @GetMapping({"", "/"})
    public String getLivraisons(Model model){

        List<Livraison> livraisons = livraisonService.lister();
        for(Livraison livraison : livraisons){
            if(livraison.getCommande() == null){
                Commande commande = new Commande();
                commande.setId(Long.valueOf(0));
                livraison.setCommande(commande);
            }
        }
        model.addAttribute("livraisons", livraisons);
        model.addAttribute("entropots", entropotService.lister());
        return "livraisons/index";
    }

    @GetMapping("/search")
    public String rechercherLivraisons(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String entropot,
            @RequestParam(required = false) String dateLiv,
            Model model) {

        Date parsedDate = null;
        try {
            if (dateLiv != null && !dateLiv.isEmpty()) {
                parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateLiv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Livraison> resultats = livraisonService.rechercherLivraisons(
                (nom != null && !nom.isEmpty()) ? nom : null,
                (entropot != null && !entropot.isEmpty()) ? entropot : null,
                parsedDate
        );

        model.addAttribute("livraisons", resultats);
        model.addAttribute("entropots", entropotService.lister());
        model.addAttribute("nom", nom);
        model.addAttribute("entropot", entropot);
        model.addAttribute("dateLiv", dateLiv);

        return "livraisons/index";
    }

    @GetMapping("/delete")
    public String supprimerLivraison(@RequestParam("id") Long id) {
        livraisonService.supprimerLivraison(id);
        return "redirect:/livraisons";
    }

    @GetMapping("/edit")
    public String modifierLivraison(@RequestParam("id") Long id, Model model) {
        Livraison livraison = livraisonService.getLivraisonById(id);
        model.addAttribute("livraison", livraison);
        return "livraisons/edit";
    }

    @PostMapping("/edit")
    public String updateLivraison(@ModelAttribute("livraison") @Valid Livraison livraison,
                                  BindingResult result,
                                  @RequestParam(value = "numc", required = false) Long numc,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("livraison", livraison);
            return "livraisons/edit";
        }

        if (numc == null || numc == 0) {
            // Cas d'une livraison sans commande liée
            livraison.setCommande(null);
        } else {
            // Cas d'une livraison avec commande liée
            Commande commande = commandeService.getCommandeById(numc);
            livraison.setCommande(commande);
        }

        livraisonService.ajouterLivraison(livraison); // ou .updateLivraison selon ton design
        return "redirect:/livraisons";
    }


    @GetMapping("/add")
    public String showSearchCommandes(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String numCommande,
            Model model) {

        List<Commande> commandes;

        if ((nom != null && !nom.isEmpty()) || (numCommande != null && !numCommande.isEmpty())) {
            commandes = commandeService.rechercherCommandes(
                    nom != null && !nom.isEmpty() ? nom : null,
                    (numCommande != null && !numCommande.isEmpty()) ? Long.parseLong(numCommande) : null
            );
        } else {
            commandes = commandeService.findAll();
        }

        model.addAttribute("commandes", commandes);
        model.addAttribute("nom", nom);
        model.addAttribute("numCommande", numCommande);

        return "livraisons/search_commande";
    }

    @GetMapping("/livrer")
    public String livrerCommande(@RequestParam("id") Long id, Model model, RedirectAttributes redirectAttributes) {


        if(livraisonService.findByCommande(id) != null){
            redirectAttributes.addFlashAttribute("successMessage", "Commande déjà ajouter");
            return "redirect:/livraisons/add";
        }
        else{
        Commande commande = commandeService.getCommandeById(id);
        /*if (commande == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande non trouvée.");
        }*/

        LivraisonForm form = new LivraisonForm();
        form.setCommandeNumber(commande.getId());
        form.setDateOfCmd(commande.getDateComd());
        form.setProductName(commande.getNom());
        form.setUnite(commande.getUnite());
        form.setQte(commande.getQte());
        form.setDestination(commande.getClient());
        form.setSource(commande.getEntrepot());
        form.setRemarque(commande.getRemarque());
        form.setCommandeNumber(commande.getId());

        model.addAttribute("livraisonForm", form);

        return "livraisons/form_livraison"; // htta Thymeleaf devra utiliser livraisonForm
    }}

    @PostMapping("/save")
    public String saveFromCommande(
            @ModelAttribute("livraisonForm") LivraisonForm form,
            BindingResult result,
            Model model
           ) {

        if (result.hasErrors()) {
            model.addAttribute("livraisonForm", form);
            return "livraisons/form_livraison";
        }
        Livraison livraison = new Livraison();


        Commande commande = commandeService.getCommandeById(form.getCommandeNumber());
        livraison.setDateLiv(form.getDateOfLivraison());
        livraison.setNom(form.getProductName());
        livraison.setUnite(form.getUnite());
        livraison.setQte(form.getQte());
        livraison.setSource(form.getSource());
        livraison.setDestination(form.getDestination());
        livraison.setRemarque(form.getRemarque());
        livraison.setCommande(commande);

        livraisonService.ajouterLivraison(livraison);
        return "redirect:/livraisons";
    }


    @GetMapping("/nouvelle")
    public String nouvelleLivraison(Model model) {
        LivraisonForm form = new LivraisonForm();
        List<Entropot> entropots = entropotService.lister();
        model.addAttribute("livraisonForm", form);
        model.addAttribute("entropots", entropots);
        return "livraisons/form_livraison_horsCmd";
    }


    @PostMapping("/nouvelle")
    public String enregistrerLivraisonHorsCommande(
            @ModelAttribute("livraisonForm") LivraisonForm form,

            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("entropots", entropotService.lister());
            return "livraisons/form_livraison_horsCmd";
        }
        Livraison livraison = new Livraison();
        livraison.setDateLiv(form.getDateOfLivraison());
        livraison.setNom(form.getProductName());
        livraison.setUnite(form.getUnite());
        livraison.setQte(form.getQte());
        livraison.setSource(form.getSource());
        livraison.setDestination(form.getDestination());
        livraison.setRemarque(form.getRemarque());

        livraisonService.ajouterLivraison(livraison);
        return "redirect:/livraisons";
    }



}
