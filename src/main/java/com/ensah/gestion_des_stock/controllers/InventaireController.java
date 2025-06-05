package com.ensah.gestion_des_stock.controllers;

import com.ensah.gestion_des_stock.DTO.InventaireDto;
import com.ensah.gestion_des_stock.DTO.Invtdto;
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
import org.springframework.web.multipart.MultipartFile;

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

    @ModelAttribute
    public void addAttributes(Model model){
        List<Entropot> entropots = entropotService.lister();
        model.addAttribute("magasins",entropots);
    }
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
    public String rechercheCombinée(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "debut", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date debut,
            @RequestParam(value = "fin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
            Model model) {

        List<Inventaire> inventaires;

        if (code != null && !code.isEmpty()) {
            Entropot entrepot = entropotService.getEntropotByCode(code);
            if (debut != null && fin != null) {
                inventaires = inventaireService.searchInventaires(entrepot, debut, fin);
            } else {
                inventaires = inventaireService.filtrerParEntrepot(entrepot);
            }
        } else if (debut != null && fin != null) {
            inventaires = inventaireService.filtrerParDate(debut, fin);
        } else {
            inventaires = inventaireService.lister();
        }

        model.addAttribute("inventaires", inventaires);
        model.addAttribute("entrepots", entropotService.lister()); // Important pour garder la liste déroulante
        return "Inventaires/liste";
    }
    @GetMapping("/telecharger")
    public void telecharger(@RequestParam("code") String codeEntrepot,
                            HttpServletResponse response) throws IOException {
        inventaireService.telecharger(codeEntrepot, response);
    }
    @PostMapping("/comparer")
    @ResponseBody
    public List<Invtdto> comparerInventaire(@RequestParam("fichierInventaire") MultipartFile fichier,
                                            @RequestParam("codeEntrepot") String codeEntrepot) {
        return inventaireService.processInventoryFile(fichier, codeEntrepot);
    }






}
