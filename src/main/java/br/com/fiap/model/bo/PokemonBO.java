package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.model.dao.PokemonDAO;
import br.com.fiap.model.vo.PokemonVO;
import br.com.fiap.model.vo.StatsVO;


public class PokemonBO {

    PokemonDAO pokemonDAO = null;

    public boolean validarIdExistente(int id) throws ClassNotFoundException, SQLException {
    	pokemonDAO = new PokemonDAO();
    	return pokemonDAO.buscarPokemonPorId(id) != null;
    }

    private boolean validarValorNumerico(int valor) {
    	return valor > 0 && valor <= 9999;
    }
    
    private boolean validarNomeExistente(String nomePokemon) throws SQLException, ClassNotFoundException {
    	pokemonDAO = new PokemonDAO();
    	return pokemonDAO.buscarPokemonPorNome(nomePokemon) != null;
    }

    public String inserirBO(PokemonVO pokemon) throws ClassNotFoundException, SQLException {
    	
    	pokemonDAO = new PokemonDAO();
    	
        if (pokemon == null) {
            return "Pokemon não pode ser nula.";
        }
        if (validarIdExistente(pokemon.getId())) {
        	return "ID já existente.";
        }
        if (validarNomeExistente(pokemon.getName())) {
            return "Nome do pokemon já existente.";
        }
			return pokemonDAO.inserir(pokemon) ? "OK" : "Falha ao inserir o pokemon";
    }


    public String atualizarBO(PokemonVO pokemon) throws ClassNotFoundException, SQLException {
    	
    	pokemonDAO = new PokemonDAO();
    	
    	if (pokemon == null) {
            return "Pokémon não pode ser nulo.";
        }
        if (!validarIdExistente(pokemon.getId())) {
        	return "ID não existente.";
        }
        if (!validarNomeExistente(pokemon.getName())) {
            return "Nome não existente.";
        }
 
        for (StatsVO stat : pokemon.getStats()) {	
		    if (!validarValorNumerico(stat.getBase_stat())) {
		    	return "Valor numérico inválido";
		    }
		}
			return pokemonDAO.atualizar(pokemon) ? "OK" : "Falha ao atualizar o pokemon";
		
    }


    public String deletarBO(int id) throws ClassNotFoundException, SQLException {
    	
    	pokemonDAO = new PokemonDAO();
    	
        if (!validarIdExistente(id)) {
            return "ID não existe.";
        }
        
			return pokemonDAO.deletar(id) ? "OK" : "Falha ao deletar o pokemon";
    }

    public ArrayList<PokemonVO> selecionarBO() throws ClassNotFoundException, SQLException {
    	
    	pokemonDAO = new PokemonDAO();
        
		return (ArrayList<PokemonVO>)pokemonDAO.listar();
		} 
    }
