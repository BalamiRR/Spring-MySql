package com.fuatKara.dao;

import com.fuatKara.model.Personel;

public interface PersonelDao {

	// DAO'lar genellikle interface'dir. Class degildir.
	public void add(Personel personel);

	public Personel idSearch(int id); // idAr
	public Personel nameSearch(String name);
	public Personel surnameSearch(String surname);
	public Personel experienceSearch(int experience);
	// public Personel personelSearch(int id, String name, String surname, int
	// tecrube);
}


