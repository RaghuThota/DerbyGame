package com.project.derby.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENTRIES")
public class Entries {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="entryId")
	private int entryId;

	@ManyToOne
	@JoinColumn(name = "uid")
	//@Column(name="uid")
	private Player player;
	
	@Column(name="derbyWeight")
	private double derbyWeight;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public double getDerbyWeight() {
		return derbyWeight;
	}

	public void setDerbyWeight(double derbyWeight) {
		this.derbyWeight = derbyWeight;
	}

}
