package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerS;

    @Autowired
    private PokemonTypeService pokemonS;

    public void setTrainerS(TrainerService trainerS){
        this.trainerS = trainerS;
    }

    @GetMapping("/trainers")
    public ModelAndView trainers(Principal p){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("trainers");
        List<Trainer> allTrainers = new ArrayList<Trainer>(trainerS.listTrainer());
        allTrainers.removeIf(trainer -> trainer.getName().equals(p.getName()));
        mav.addObject("user", trainerS.getTrainerByName(p.getName()));
        mav.addObject("trainers", allTrainers);

        return mav;
    }

    @GetMapping("/trainers/{name}")
    public ModelAndView trainersDetails(@PathVariable String name){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("trainerDetails");
        mav.addObject("trainers", trainerS.getTrainerByName(name));
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView profile(Principal p){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("profile");
        mav.addObject("trainer", trainerS.getTrainerByName(p.getName()));
        mav.addObject("user", trainerS.getTrainerByName(p.getName()));
        return mav;
    }
}
