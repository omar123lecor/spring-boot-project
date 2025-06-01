package com.ensah.gestion_des_stock.controller;

import com.ensah.gestion_des_stock.DTO.InventaireDto;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Inventaire;
import com.ensah.gestion_des_stock.services.EntropotService;
import com.ensah.gestion_des_stock.services.InventaireService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inventaires")
public class InventaireController {

    @Autowired
    private InventaireService inventaireService;

    @Autowired
    private EntropotService entropotService;

    @GetMapping({"", "/"})
    public String listerInventaires(Model model) {
        model.addAttribute("inventaires", inventaireService.lister());
        return "Inventaires/liste";
    }

    @GetMapping("/nouveau")
    public String nouveauInventaire(Model model) {
        model.addAttribute("inventaireForm", new InventaireDto());
        model.addAttribute("entrepots", entropotService.lister());
        return "Inventaires/formInventaire";
    }

    @PostMapping("/save")
    public String saveInventaire(@ModelAttribute("inventaireForm") @Valid InventaireDto inventaireForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("entrepots", entropotService.lister());
            return "Inventaires/formInventaire";
        }

        Inventaire i = new Inventaire();
        i.setId(inventaireForm.getId());
        i.setDateInventaire(inventaireForm.getDateInventaire());
        i.setEffectuePar(inventaireForm.getEffectuePar());
        i.setValidePar(inventaireForm.getValidePar());
        i.setEcart(inventaireForm.getEcart());

        Entropot entropot = entropotService.getEntropotByCode(inventaireForm.getEntrepotId());
        i.setEntropot(entropot);

        inventaireService.ajouter(i);
        return "redirect:/inventaires";
    }

    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id, Model model) {
        Inventaire i = inventaireService.getById(id);
        InventaireDto form = new InventaireDto();

        form.setId(i.getId());
        form.setDateInventaire(i.getDateInventaire());
        form.setEffectuePar(i.getEffectuePar());
        form.setValidePar(i.getValidePar());
        form.setEcart(i.getEcart());
        form.setEntrepotId(i.getEntropot().getCode());

        model.addAttribute("inventaireForm", form);
        model.addAttribute("entrepots", entropotService.lister());

        return "Inventaires/formInventaire";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        inventaireService.supprimer(id);
        return "redirect:/inventaires";
    }

    // === Recherches supplémentaires ===

    @GetMapping("/filtrer/entrepot/{code}")
    public String filtrerParEntrepot(@PathVariable String code, Model model) {
        Entropot entrepot = entropotService.getEntropotByCode(code);
        List<Inventaire> inventaires = inventaireService.filtrerParEntrepot(entrepot);
        model.addAttribute("inventaires", inventaires);
        return "Inventaires/liste";
    }

    @GetMapping("/filtrer/dates")
    public String filtrerParDate(@RequestParam("debut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date debut,
                                 @RequestParam("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
                                 Model model) {
        List<Inventaire> inventaires = inventaireService.filtrerParDate(debut, fin);
        model.addAttribute("inventaires", inventaires);
        return "Inventaires/liste";
    }

    @GetMapping("/filtrer/nom")
    public String filtrerParNomEffectuer(@RequestParam("mot") String mot, Model model) {
        List<Inventaire> inventaires = inventaireService.filtrerParNomEffectuer(mot);
        model.addAttribute("inventaires", inventaires);
        return "Inventaires/liste";
    }

    @GetMapping("/ecarts")
    public String afficherEcart(Model model) {
        model.addAttribute("inventaires", inventaireService.afficherEcart());
        return "Inventaires/liste";
    }

    @GetMapping("/search")
    public String rechercheCombinée(@RequestParam("code") String code,
                                    @RequestParam("debut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date debut,
                                    @RequestParam("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
                                    Model model) {
        Entropot entrepot = entropotService.getEntropotByCode(code);
        List<Inventaire> inventaires = inventaireService.searchInventaires(entrepot, debut, fin);
        model.addAttribute("inventaires", inventaires);
        return "Inventaires/liste";
    }
    @GetMapping("/telecharger")
    public void telecharger(HttpServletResponse response) throws IOException {
        inventaireService.telecharger(response);
    }

}
