package com.br.worldcupbet.WorldCupBetWebApp;

public class Bet {
	private String punterName;
	public String getPunterName() {
		return punterName;
	}
	public void setPunterName(String punterName) {
		this.punterName = punterName;
	}
	public String getTeamOnWhichBet() {
		return teamOnWhichBet;
	}
	public void setTeamOnWhichBet(String teamOnWhichBet) {
		this.teamOnWhichBet = teamOnWhichBet;
	}
	public double getBetValue() {
		return betValue;
	}
	public void setBetValue(double betValue) {
		this.betValue = betValue;
	}
	private String teamOnWhichBet;	
	private double betValue;
}
