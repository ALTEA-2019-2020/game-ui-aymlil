package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    PokemonTypeService pokemonTypeService ;

    @GetMapping(value="/pokedex")
    public ModelAndView pokedex(){
        // TODO
    }

    public void setPokemonTypeService(PokemonTypeService pokemonTypeService){
        this.pokemonTypeService = pokemonTypeService;
    }

}