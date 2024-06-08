package dev.ArkNLA.GridMap;

import java.io.Serializable;

public class EntityObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int coordX;
	private int coordY;
	private String name;
	private String group;
	
	EntityObject() {
		coordX = 0;
		coordY = 1000;
		name = "Instructions";
		group = "-";
	}
	EntityObject(String name, int X, int Y, String group) {
		this.name = name;
		this.coordX = X;
		this.coordY = Y;
		this.group = group;
	}
	
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	
}
