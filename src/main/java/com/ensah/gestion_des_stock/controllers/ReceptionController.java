package com.ensah.gestion_des_stock.controllers;

import com.ensah.gestion_des_stock.DTO.AchatForm;
import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.services.AchatService;
import com.ensah.gestion_des_stock.services.ReceptionService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @author $ {USER}
 **/
@Controller
public class ReceptionController {

    @Autowired
    ReceptionService receptionService;

    @Autowired
    AchatService achatService;

    @GetMapping("/achatListe")
    public String getAchat(Model model){
        List<Achat> listOfAchat = achatService.allAchat();
        model.addAttribute("achats",listOfAchat);
        return "achat";
    }
    @GetMapping("/receptionListe")
    public String getReceptions(Model model){
        var receptions  = receptionService.allReceptions();
        model.addAttribute("receptions",receptions);
        return "reception";
    }

    @GetMapping("/add/reception")
    public String addReception(Model model , @RequestParam Long id){
        Optional<Achat> ach = achatService.findAchat(id);
        if(ach.isEmpty()){
            return "redirect:/achatListe";
        }
        AchatForm achatForm = new AchatForm();
        Achat achat = ach.get();
        achatForm.setAchatNumber(achat.getId());
        achatForm.setDateOfBuy(achat.getDateAchat());
        achatForm.setQte(achat.getQte());
        achatForm.setSource(achat.getSource());
        achatForm.setProductName(achat.getNom());
        achatForm.setUnite(achat.getUnite());

        model.addAttribute("achat",achatForm);
        model.addAttribute("magasin", List.of("mag1", "mag2", "mag3"));
        return "addReception";

    }
}
