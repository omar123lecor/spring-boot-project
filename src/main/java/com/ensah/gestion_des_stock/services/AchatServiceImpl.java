package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.repositories.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author $ {USER}
 **/
@Service
public class AchatServiceImpl implements AchatService {

    @Autowired
    private AchatRepository achatRepository;

    @Override
    public List<Achat> searchAchat(String nom , Long id){
        return achatRepository.findByIdOrNom(id,nom);
    }

}
