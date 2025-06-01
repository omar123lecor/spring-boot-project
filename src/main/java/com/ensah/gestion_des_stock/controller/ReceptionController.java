package com.ensah.gestion_des_stock.controller;
import com.ensah.gestion_des_stock.DTO.AchatForm;
import com.ensah.gestion_des_stock.DTO.AchatGroup;
import com.ensah.gestion_des_stock.DTO.HorsAchatGroup;
import com.ensah.gestion_des_stock.DTO.ReceptionForm;
import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.model.Entropot;
import com.ensah.gestion_des_stock.model.Reception;
import com.ensah.gestion_des_stock.services.AchatService;
import com.ensah.gestion_des_stock.services.EntropotServiceImpl;
import com.ensah.gestion_des_stock.services.ReceptionService;
import jakarta.validation.Valid;
import org.aspectj.weaver.BindingScope;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;


/**
 * @author $ {USER}
 **/
@Controller
public class ReceptionController {

    @Autowired
    ReceptionService receptionService;

    @Autowired
    AchatService achatService;

    @Autowired
    EntropotServiceImpl entropotService;

    @ModelAttribute
    public void addAttributes(Model model){
        List<Entropot> entropots = entropotService.lister();
        model.addAttribute("magasin",entropots);
    }
    @GetMapping("/achatListe")
    public String getAchat(@RequestParam(value = "num",required = false) Long num ,@RequestParam(value = "nom",required = false) String nom,Model model){
       /* List<Achat> listOfAchat = achatService.allAchat();
        model.addAttribute("achats",listOfAchat);*/
        List<Achat> achats;
        boolean isNomPresent = (nom != null && !nom.trim().isEmpty());
        boolean isNumPresent = (num != null);
        if (isNumPresent || isNomPresent) {
            achats = achatService.searchAchat(nom, num);
        } else {
            achats = achatService.allAchat();
        }
        model.addAttribute("achats",achats);
        return "achat";
    }
    @GetMapping("/receptionListe")
    public String getReceptions(@RequestParam(value = "produit",required = false) String name ,
                                @RequestParam(value = "date1",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date1,
                                @RequestParam(value = "date2",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2 ,
                                @RequestParam(value = "magcode",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String magcode,
                                Model model){

        List<Reception> receptions;
        boolean isNomPresent = (name != null && !name.trim().isEmpty());
        boolean ismagCodePresent = (magcode != null && !magcode.trim().isEmpty());
        boolean isDate1Present = (date1 != null);
        boolean isDate2Present = (date2 != null );
        if (isNomPresent || ismagCodePresent || isDate1Present || isDate2Present) {
            receptions = receptionService.dynamiqueR(date1, date2,name,magcode);
            Achat achat = new Achat();
            achat.setId(Long.valueOf(0));
            for (Reception reception : receptions){
                if (reception.getAchat() == null){
                    reception.setAchat(achat);
                }
            }
        } else {
            receptions = receptionService.allReceptions();
            Achat achat = new Achat();
            achat.setId(Long.valueOf(00));
            for (Reception reception : receptions){
                if (reception.getAchat() == null){
                    reception.setAchat(achat);
                }
            }

        }
        model.addAttribute("receptions",receptions);
        return "reception";

        /*List<Reception> receptions  = receptionService.allReceptions();
        Achat achat = new Achat();
        achat.setId(Long.valueOf(00));
        for (Reception reception : receptions){
            if (reception.getAchat() == null){
                reception.setAchat(achat);
            }
        }
        model.addAttribute("receptions",receptions);
        return "reception";*/
    }

    @GetMapping("/add/reception")
    public String addReception(Model model ,@RequestParam(required = false) Long id,RedirectAttributes redirectAttributes){

        AchatForm achatForm = new AchatForm();

        if(id == null){
            model.addAttribute("achat",achatForm);
            return "hors";
        }

        if(receptionService.findByAchatt(id) != null){
            redirectAttributes.addFlashAttribute("successMessage", "Achat déjà ajouter");
            return "redirect:/achatListe";
        }
        else {
            Achat ach = achatService.findAchat(id);



            achatForm.setAchatNumber(ach.getId());
            achatForm.setDateOfBuy(ach.getDateAchat());
            achatForm.setQte(Long.valueOf(ach.getQte()));
            achatForm.setSource(ach.getSource());
            achatForm.setProductName(ach.getNom());
            achatForm.setUnite(ach.getUnite());

            model.addAttribute("achat",achatForm);

            return "addReception";
        }}


    @PostMapping("/reception/save/hors")
    public String processHorsAchat(@Validated({HorsAchatGroup.class}) @ModelAttribute("achat") AchatForm achatForm,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "hors"; // formulaire hors achat
        }
        Reception reception = new Reception();
        Entropot entropot = entropotService.getEntropotByCode(achatForm.getMagCode());
        reception.setDateReception(achatForm.getDateOfReception());
        reception.setNom(achatForm.getProductName());
        reception.setEntropot(entropot);
        reception.setUnite(achatForm.getUnite());
        reception.setQte(achatForm.getQte());
        reception.setSource(achatForm.getSource());
        reception.setType("HorsAchat");
        receptionService.saveReception(reception);
        redirectAttributes.addFlashAttribute("successMessage", "Hors achat enregistré avec succès.");
        return "redirect:/achatListe";
    }

    @PostMapping("/reception/save")
    public String process(@Validated({AchatGroup.class, HorsAchatGroup.class}) @ModelAttribute("achat") AchatForm achatForm, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            return "addReception";
        }

        Entropot entropot = entropotService.getEntropotByCode(achatForm.getMagCode());
        Reception reception = new Reception();


        Achat achat = achatService.findAchat(achatForm.getAchatNumber());
        reception.setDateReception(achatForm.getDateOfReception());
        reception.setSource(achatForm.getSource());
        reception.setNom(achatForm.getProductName());
        reception.setQte(achatForm.getQte());
        reception.setUnite(achatForm.getUnite());
        reception.setEntropot(entropot);
        reception.setRemarque(achatForm.getRemarque());
        reception.setAchat(achat);
        reception.setType("Achat");


        receptionService.saveReception(reception);
        redirectAttributes.addFlashAttribute("successMessage", "La réception a été crée avec succès.");

        return "redirect:/achatListe";
    }
    @GetMapping("/reception/delete")
    public String deleteR(@RequestParam Long id){
        receptionService.supprimer(id);
        return "redirect:/receptionListe";
    }

    @PostMapping("/reception/save/edit")
    public String saveEdit(@Valid @ModelAttribute("receptionF") ReceptionForm receptionForm,
                           BindingResult bindingResult,RedirectAttributes redirectAttributes,
                           @RequestParam Long id){
        if(bindingResult.hasErrors()){
            return "redirect:/reception/edit?id=" + id;
        }
        Entropot entropot = entropotService.getEntropotByCode(receptionForm.getMagcode());
        Reception reception = receptionService.filterParId(id);
        reception.setDateReception(receptionForm.getDateR());
        reception.setRemarque(receptionForm.getRemarque());
        reception.setEntropot(entropot);
        reception.setQte(receptionForm.getQte());
        reception.setSource(receptionForm.getSource());
        reception.setUnite(receptionForm.getUnite());
        reception.setNom(receptionForm.getNom());
        receptionService.saveReception(reception);

        redirectAttributes.addFlashAttribute("successMessage","La réception à été bine mise à jours");
        return "redirect:/receptionListe";
    }

    @GetMapping("/reception/edit")
    public String editR(Model model ,@RequestParam Long id){

        ReceptionForm receptionForm = new ReceptionForm();

        Reception reception = receptionService.filterParId(id);

        receptionForm.setDateR(reception.getDateReception());
        receptionForm.setMagcode(reception.getEntropot().getCode());
        receptionForm.setSource(reception.getSource());
        receptionForm.setNom(reception.getNom());
        receptionForm.setQte(reception.getQte());
        receptionForm.setUnite(reception.getUnite());
        receptionForm.setRemarque(reception.getRemarque());
        model.addAttribute("id",reception.getId());
        model.addAttribute("receptionF",receptionForm);

        return "editR";
    }

}