package com.example.windows.infomuslim.Model;

import com.google.gson.annotations.SerializedName;

public class HeroesItem{

	@SerializedName("imageurl")
	private String imageurl;

	@SerializedName("name")
	private String name;

	public void setImageurl(String imageurl){
		this.imageurl = imageurl;
	}

	public String getImageurl(){
		return imageurl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"HeroesItem{" + 
			"imageurl = '" + imageurl + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}