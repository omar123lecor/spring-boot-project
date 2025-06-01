package com.ensah.gestion_des_stock.controller;

import com.ensah.gestion_des_stock.DTO.TransfertForm;
import com.ensah.gestion_des_stock.model.*;
import com.ensah.gestion_des_stock.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

import java.util.*;

@Controller
@RequestMapping("/transferts")
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    @Autowired
    private EntropotService entropotService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping({"", "/"})
    public String lister(@RequestParam(required = false) String nom,
                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                         Model model) {

        List<Transfere> transferts = (nom != null || date != null)
                ? transfertService.searchByNomAndDate(nom, date)
                : transfertService.lister();

        model.addAttribute("transferts", transferts);
        return "Transferts/listeTransferts";
    }

    @GetMapping("/nouveau")
    public String nouveau(Model model) {
        model.addAttribute("form", new TransfertForm());
        model.addAttribute("entrepots", entropotService.lister());
        return "Transferts/formTransfert";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("form") @Valid TransfertForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("entrepots", entropotService.lister());
            return "Transferts/formTransfert";
        }
        Transfere t = new Transfere();
        t.setNom(form.getNom());
        t.setUnite(form.getUnite());
        t.setQte(form.getQte());
        t.setDate_Transfere(form.getDateTransfere());
        t.setRemarque(form.getRemarque());

        Entropot source = new Entropot();
        source.setCode(form.getEntrepotSourceId());
        t.setEntrepot_source(source);

        Entropot dest = new Entropot();
        dest.setCode(form.getEntrepotDestinationId());
        t.setEntrepot_destination(dest);

        if (form.getId() != null) {
            transfertService.modifier(form.getId(), t);
        } else {
            transfertService.ajouter(t);
        }

        return "redirect:/transferts";
    }
/*
    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id, Model model) {
        Transfere transfere = transfertService.getById(id);
        model.addAttribute("transfere", transfere);
            return "Transferts/formTransfert";
    }

    @PostMapping("/formTransfert")
    public String updateT(@ModelAttribute("Transfere") @Valid Transfere transfere,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("transfere", transfere);
            return "redirect:/transferts";
        }

        transfertService.ajouter(transfere);
        return "redirect:/transferts";
    }*/
    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id, Model model) {
        Transfere t = transfertService.getById(id);
        TransfertForm form = new TransfertForm();

        form.setId(t.getId());
        form.setNom(t.getNom());
        form.setUnite(t.getUnite());
        form.setQte(t.getQte());
        form.setRemarque(t.getRemarque());
        form.setDateTransfere(t.getDate_Transfere());
        form.setEntrepotSourceId(t.getEntrepot_source().getCode());
        form.setEntrepotDestinationId(t.getEntrepot_destination().getCode());

        model.addAttribute("form", form);
        model.addAttribute("entrepots", entropotService.lister());

        return "Transferts/formTransfert";

    }




    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        transfertService.supprimer(id);
        return "redirect:/transferts";
    }
    @GetMapping("/search")
    public String rechercherTransferts(
            @RequestParam(required = false) String nom, @RequestParam(required = false) String dateTransfere,
            Model model) {

        Date parsedDate = null;
        try {
            if (dateTransfere != null && !dateTransfere.isEmpty()) {
                parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTransfere);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Transfere> transfertsFiltres = transfertService.searchByNomAndDate((nom != null && !nom.isEmpty()) ? nom : null ,parsedDate);
        model.addAttribute("transferts", transfertsFiltres);
        model.addAttribute("nom", nom);
        model.addAttribute("dateTransfere", dateTransfere);
        return "Transferts/listeTransferts";

    }
}

