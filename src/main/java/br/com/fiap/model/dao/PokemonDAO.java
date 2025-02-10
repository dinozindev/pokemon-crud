package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.PokemonVO;
import br.com.fiap.model.vo.StatVO;
import br.com.fiap.model.vo.StatsVO;

public class PokemonDAO {
	// atributo de conexão
	private Connection minhaConexao;
	
	// construtor 
	public PokemonDAO() throws ClassNotFoundException, SQLException  {
		this.minhaConexao = ConexaoFactory.getConnection();
	}
	
	// insert
	public boolean inserir(PokemonVO pokemon) throws SQLException {
		
		int hp = 0, attack = 0, defense = 0, spAttack = 0, spDefense = 0, speed = 0;
        
        for (StatsVO stat : pokemon.getStats()) {
            switch (stat.getStat().getName()) {
                case "hp":
                    hp = stat.getBase_stat();
                    break;
                case "attack":
                    attack = stat.getBase_stat();
                    break;
                case "defense":
                    defense = stat.getBase_stat();
                    break;
                case "speed":
                    speed = stat.getBase_stat();
                    break;
                case "special-attack":
                    spAttack = stat.getBase_stat();
                    break;
                case "special-defense":
                    spDefense = stat.getBase_stat();
                    break;
            }
        }
        
		String sql = "INSERT INTO pokemon VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = minhaConexao.prepareStatement(sql);
		stmt.setInt(1, pokemon.getId());
		stmt.setInt(2, pokemon.getHeight());
		stmt.setString(3, pokemon.getName());
		stmt.setInt(4, pokemon.getWeight());
		stmt.setInt(5, hp);
		stmt.setInt(6, attack);
		stmt.setInt(7, defense);
		stmt.setInt(8, spAttack);
		stmt.setInt(9, spDefense);
		stmt.setInt(10, speed);
		int rows = stmt.executeUpdate();
		return rows > 0;
	}
	
	// delete
	public boolean deletar(int id) throws SQLException {
		String sql = "DELETE FROM pokemon WHERE id = ?";
		PreparedStatement stmt = minhaConexao.prepareStatement(sql);
		stmt.setInt(1, id);
		int rows = stmt.executeUpdate();
		stmt.close();
		return rows > 0;
	}
	
	// update
	public boolean atualizar(PokemonVO pokemon) throws SQLException {
		
	int hp = 0, attack = 0, defense = 0, spAttack = 0, spDefense = 0, speed = 0;
	        
	        for (StatsVO stat : pokemon.getStats()) {
	            switch (stat.getStat().getName()) {
	                case "hp":
	                    hp = stat.getBase_stat();
	                    break;
	                case "attack":
	                    attack = stat.getBase_stat();
	                    break;
	                case "defense":
	                    defense = stat.getBase_stat();
	                    break;
	                case "speed":
	                    speed = stat.getBase_stat();
	                    break;
	                case "special-attack":
	                    spAttack = stat.getBase_stat();
	                    break;
	                case "special-defense":
	                    spDefense = stat.getBase_stat();
	                    break;
	            }
	        }
		
		String sql = "UPDATE pokemon SET hp = ?, ataque = ?, defesa = ?, ataque_especial = ?, defesa_especial = ?, velocidade = ? WHERE id = ?";
		PreparedStatement stmt = minhaConexao.prepareStatement(sql);
		stmt.setInt(1, hp);
		stmt.setInt(2, attack);
		stmt.setInt(3, defense);
		stmt.setInt(4, spDefense);
		stmt.setInt(5, spAttack);
		stmt.setInt(6, speed);
		stmt.setInt(7, pokemon.getId());
		int rows = stmt.executeUpdate();
		stmt.close();
		return rows > 0;
	}
	
	// select 
	public List<PokemonVO> listar() throws SQLException {
		
		List<PokemonVO> listaPokemon = new ArrayList<PokemonVO>();
		
		String sql = "SELECT * FROM pokemon ORDER BY 1";
		PreparedStatement stmt = minhaConexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			List<StatsVO> listaStats = new ArrayList<StatsVO>();
			PokemonVO pokemon = new PokemonVO();
			
			pokemon.setId(rs.getInt(1));
			pokemon.setHeight(rs.getInt(2));
			pokemon.setName(rs.getString(3));
			pokemon.setWeight(rs.getInt(4));
			StatsVO hp = new StatsVO(rs.getInt(5), new StatVO("hp"));
			StatsVO attack = new StatsVO(rs.getInt(6), new StatVO("attack"));
			StatsVO defense = new StatsVO(rs.getInt(7), new StatVO("defense"));
			StatsVO spAttack = new StatsVO(rs.getInt(8), new StatVO("special-attack"));
			StatsVO spDefense = new StatsVO(rs.getInt(9), new StatVO("special-defense"));
			StatsVO speed = new StatsVO(rs.getInt(10), new StatVO("speed"));
			listaStats.add(hp);
			listaStats.add(attack);
			listaStats.add(defense);
			listaStats.add(spAttack);
			listaStats.add(spDefense);
			listaStats.add(speed);
			pokemon.setStats(listaStats);
			
			listaPokemon.add(pokemon);
		}
		return listaPokemon;
	}
	
	public PokemonVO buscarPokemonPorId(int id) throws SQLException {
		String sql = "SELECT * FROM pokemon WHERE id = ?";
		PokemonVO pokemon = null;
		List<StatsVO> listaStats = new ArrayList<StatsVO>();
		
		try (PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					
					int idPokemon = rs.getInt(1);
					int height = rs.getInt(2);
					String nome = rs.getString(3);
					int weight = rs.getInt(4);
					StatsVO hp = new StatsVO(rs.getInt(5), new StatVO("hp"));
					StatsVO attack = new StatsVO(rs.getInt(6), new StatVO("attack"));
					StatsVO defense = new StatsVO(rs.getInt(7), new StatVO("defense"));
					StatsVO spAttack = new StatsVO(rs.getInt(8), new StatVO("special-attack"));
					StatsVO spDefense = new StatsVO(rs.getInt(9), new StatVO("special-defense"));
					StatsVO speed = new StatsVO(rs.getInt(10), new StatVO("speed"));
					listaStats.add(hp);
					listaStats.add(attack);
					listaStats.add(defense);
					listaStats.add(spAttack);
					listaStats.add(spDefense);
					listaStats.add(speed);
					
					pokemon = new PokemonVO(idPokemon, height, nome, weight, listaStats);
				}
			}
		} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar pokemon:");
            e.printStackTrace();
        } return pokemon;
	} 
	
	public PokemonVO buscarPokemonPorNome(String nomePokemon) throws SQLException {
		String sql = "SELECT * FROM pokemon WHERE nome = ?";
		PokemonVO pokemon = null;
		List<StatsVO> listaStats = new ArrayList<StatsVO>();
		
		try (PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
			ps.setString(1, nomePokemon);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					
					int idPokemon = rs.getInt(1);
					int height = rs.getInt(2);
					String nome = rs.getString(3);
					int weight = rs.getInt(4);
					StatsVO hp = new StatsVO(rs.getInt(5), new StatVO("hp"));
					StatsVO attack = new StatsVO(rs.getInt(6), new StatVO("attack"));
					StatsVO defense = new StatsVO(rs.getInt(7), new StatVO("defense"));
					StatsVO spAttack = new StatsVO(rs.getInt(8), new StatVO("special-attack"));
					StatsVO spDefense = new StatsVO(rs.getInt(9), new StatVO("special-defense"));
					StatsVO speed = new StatsVO(rs.getInt(10), new StatVO("speed"));
					listaStats.add(hp);
					listaStats.add(attack);
					listaStats.add(defense);
					listaStats.add(spAttack);
					listaStats.add(spDefense);
					listaStats.add(speed);
					
					pokemon = new PokemonVO(idPokemon, height, nome, weight, listaStats);
				}
			}
		} catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar pokemon:");
            e.printStackTrace();
        } return pokemon;
	} 
}
