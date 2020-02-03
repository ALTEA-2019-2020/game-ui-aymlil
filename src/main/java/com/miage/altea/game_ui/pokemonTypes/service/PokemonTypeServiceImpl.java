package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

    private RestTemplate restTemplate;

    private String pokemonServiceUrl = "";


    public List<PokemonType> listPokemonsTypes() {
        var url = pokemonServiceUrl + "/pokemon-types";
        var pokemons = restTemplate.getForObject(url, PokemonType[].class);
        return Arrays.asList(pokemons);
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate ;

    }

    //@Value("${pokemon.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }


}
