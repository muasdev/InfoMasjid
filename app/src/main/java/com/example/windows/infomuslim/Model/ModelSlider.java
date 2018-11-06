package com.example.windows.infomuslim.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ModelSlider{

	@SerializedName("heroes")
	private ArrayList<HeroesItem> heroes;

	public void setHeroes(ArrayList<HeroesItem> heroes){
		this.heroes = heroes;
	}

	public ArrayList<HeroesItem> getHeroes(){
		return heroes;
	}

	@Override
 	public String toString(){
		return 
			"ModelSlider{" + 
			"heroes = '" + heroes + '\'' + 
			"}";
		}
}