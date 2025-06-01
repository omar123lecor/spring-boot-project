package com.ensah.gestion_des_stock.services;

import com.ensah.gestion_des_stock.model.Achat;
import com.ensah.gestion_des_stock.repositories.AchatRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Achat> allAchat(){ return  achatRepository.findAll();}

    @Override
    public Achat findAchat(Long id){
        return achatRepository.findById(id).orElse(null);
    }
}
