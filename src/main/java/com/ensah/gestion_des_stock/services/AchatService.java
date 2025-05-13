package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Achat;

import java.util.List;

/**
 * @author $ {USER}
 **/
public interface AchatService {
    public List<Achat> searchAchat(String nom , Long id);
}
