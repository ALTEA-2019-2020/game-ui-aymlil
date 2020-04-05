package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate template;
    private String pokemonsUrl;

    @Override
    public List<PokemonType> listPokemonsTypes(){
        return Arrays.asList(template.getForObject(pokemonsUrl +"/pokemon-types/", PokemonType[].class));
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return template.getForObject(pokemonsUrl +"/pokemon-types/{id}",PokemonType.class,id);
    }

    @Autowired
    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonsUrl) {
        this.pokemonsUrl = pokemonsUrl;
    }
}
