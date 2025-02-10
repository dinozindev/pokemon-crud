package br.com.fiap.model.vo;

import java.util.List;

public class PokemonVO {
	private int id;
	private int height;
	private String name;
	private int weight;
	private List<StatsVO> stats;
	
	public PokemonVO() {
		
	}
	
	public PokemonVO(int id, int height, String name, int weight, List<StatsVO> stats) {
		super();
		this.id = id;
		this.height = height;
		this.name = name;
		this.weight = weight;
		this.stats = stats;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}


	/**
	 * @return the stats
	 */
	public List<StatsVO> getStats() {
		return stats;
	}


	/**
	 * @param stats the stats to set
	 */
	public void setStats(List<StatsVO> stats) {
		this.stats = stats;
	}


	@Override
	public String toString() {
		return "PokemonVO [id=" + id + ", height=" + height + ", name=" + name + ", weight=" + weight + "]";
	}
	
}

