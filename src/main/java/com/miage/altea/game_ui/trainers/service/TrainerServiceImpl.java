package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate template;
    private String trainersUrl;
    private PokemonTypeService pokemonTypeS;

    @Override
    public List<Trainer> listTrainer() {
        Trainer[] tList =  template.getForObject(trainersUrl +"/trainers/", Trainer[].class);
        List<Trainer> trainersList = Arrays.asList(tList);
        for(Trainer t : tList){
            for(Pokemon pok : t.getTeam()){
                pok.setPt(pokemonTypeS.getPokemonType(pok.getPokemonTypeId()));
            }
        }
        return trainersList;
    }

    @Override
    public Trainer getTrainerByName(String name) {
        Trainer t = template.getForObject(trainersUrl +"/trainers/"+name+"/", Trainer.class);
        for(Pokemon pok : t.getTeam()){
            pok.setPt(pokemonTypeS.getPokemonType(pok.getPokemonTypeId()));
        }
        return t;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    @Value("${trainer.service.url}")
    public void setTrainersUrl(String trainersUrl) {
        this.trainersUrl = trainersUrl;
    }

    @Autowired
    public void setPokemonTypeS(PokemonTypeService pokemonTypeS) {
        this.pokemonTypeS = pokemonTypeS;
    }
}
