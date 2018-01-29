package com.project.derby.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Players")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="playerId")
	private int playerId;
	
	@Column(name="playerName")
	private String playerName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="contact")
	private String contact;

	@OneToMany(mappedBy = "entryId", cascade = CascadeType.ALL)
	@Column(name="contact")
	private List<Entries> entriesList;

	public Player() {
	}

	public List<Entries> getEntriesList() {
		return entriesList;
	}

	public void setEntriesList(List<Entries> entriesList) {
		this.entriesList = entriesList;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getUname() {
		return playerName;
	}

	public void setUname(String playerName) {
		this.playerName = playerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
