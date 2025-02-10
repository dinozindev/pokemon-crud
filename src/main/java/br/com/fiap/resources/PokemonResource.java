package br.com.fiap.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.model.bo.PokemonBO;
import br.com.fiap.model.vo.PokemonVO;
import br.com.fiap.service.PokeApiService;


@Path("/pokemon")
public class PokemonResource {

    private PokemonBO pokemonBO = new PokemonBO();
    private PokeApiService pokeService = new PokeApiService();

    // Inserir Pokemon (POST)
    @POST
    @Path("/{nomePokemon}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarPokemon(@PathParam("nomePokemon") String nomePokemon, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
    	PokemonVO pokemon = pokeService.getPokemon(nomePokemon);
    	
        if (pokemon == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Pokémon não encontrado").build();
        }
    	
    	String resultado = pokemonBO.inserirBO(pokemon);
    	if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(pokemon.getName());
        return Response.created(builder.build()).build();
    }
    
    // Atualizar Pokemon (PUT)
    @PUT
    @Path("/{idPokemon}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPokemon(PokemonVO pokemon, @PathParam("idPokemon") int idPokemon) throws ClassNotFoundException, SQLException {
        pokemon.setId(pokemon.getId());
        String resultado = pokemonBO.atualizarBO(pokemon);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    // Deletar Pokemon (DELETE)
    @DELETE
    @Path("/{idPokemon}")
    public Response deletarPokemon(@PathParam("idPokemon") int idPokemon) throws ClassNotFoundException, SQLException {
        String resultado = pokemonBO.deletarBO(idPokemon);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    // Selecionar todos os Pokemons (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PokemonVO> listarPokemons() throws ClassNotFoundException, SQLException {
    	return (ArrayList<PokemonVO>) pokemonBO.selecionarBO();
    }
    
}