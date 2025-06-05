package com.ensah.gestion_des_stock.controllers;

import com.ensah.gestion_des_stock.DTO.EntrepotForm;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.services.EntropotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrepots")
public class EntrepoController {

    @Autowired
    private EntropotService e;


    // Afficher la liste des entrepôts
    @GetMapping({"", "/"})
    public String getEntrepo(@RequestParam(value = "editCode", required = false) String code, Model model) {
        List<Entropot> entrepots = e.lister();
        model.addAttribute("entrepots", entrepots);

        if (code != null) {
            Entropot entrepotToEdit = e.getEntropotByCode(code); // Méthode à implémenter dans le service
            model.addAttribute("entrepot", entrepotToEdit);
        } else {
            model.addAttribute("newEntrepot", new Entropot());
        }

        return "entropots/index";
    }


    // Ajouter un entrepôt
    @PostMapping("/save")
    public String ajouterEntrepot(@ModelAttribute Entropot entropot) {
        e.ajouterEntropot(entropot);
        return "redirect:/entrepots";
    }

    // Supprimer un entrepôt
    @GetMapping("/delete")
    public String supprimerEntrepot(@RequestParam String code) {
        e.supprimerEntropot(code);
        return "redirect:/entrepots";
    }

    @PostMapping("/update")
    public String modifier(@Valid @ModelAttribute("entrepotForm") EntrepotForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "entrepots/edit";
        }

        Entropot updated = new Entropot();
        updated.setCode(form.getCodef());
        updated.setNom(form.getNomf());
        updated.setAddress(form.getAddressf());

        e.modifierEntropot(form.getCodef(), updated);
        return "redirect:/entrepots";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam String code, Model model) {
        Entropot entrepot = e.getEntropotByCode(code); // Récupérer l'entrepôt existant
        if (entrepot == null) {
            return "redirect:/entrepots"; // Redirige si l'entrepôt n'existe pas
        }

        // Mapper l'entité vers le DTO (ou utiliser directement Entropot si tu préfères)
        EntrepotForm form = new EntrepotForm();
        form.setCodef(entrepot.getCode());
        form.setNomf(entrepot.getNom());
        form.setAddressf(entrepot.getAddress());

        model.addAttribute("entrepotForm", form);
        return "Entropots/edit"; // la nouvelle page
    }

}



