package com.ensah.gestion_des_stock.controllers;

import com.ensah.gestion_des_stock.services.AchatService;
import com.ensah.gestion_des_stock.services.ReceptionService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author $ {USER}
 **/
@Controller
public class ReceptionController {

    @Autowired
    ReceptionService receptionService;

    @GetMapping("/receptionListe")
    public String getClients(Model model){
        var receptions  = receptionService.allReceptions();
        model.addAttribute("receptions",receptions);
        return "reception";
    }

}
