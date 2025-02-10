package br.com.fiap.model.vo;

public class StatsVO {
	private int base_stat;
	private StatVO stat;
	
	public StatsVO(int base_stat, StatVO stat) {
		super();
		this.base_stat = base_stat;
		this.stat = stat;
	}
	
	public StatsVO() {
		
	}

	/**
	 * @return the base_stat
	 */
	public int getBase_stat() {
		return base_stat;
	}

	/**
	 * @param base_stat the base_stat to set
	 */
	public void setBase_stat(int base_stat) {
		this.base_stat = base_stat;
	}

	/**
	 * @return the stat
	 */
	public StatVO getStat() {
		return stat;
	}

	/**
	 * @param stat the stat to set
	 */
	public void setStat(StatVO stat) {
		this.stat = stat;
	}
	
	
}


