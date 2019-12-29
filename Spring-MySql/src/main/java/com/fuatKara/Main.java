package com.fuatKara;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.fuatKara.dao.PersonelDao;
import com.fuatKara.model.Personel;

public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("SpringBeansConfig.xml");
		
		PersonelDao personelDao = (PersonelDao) context.getBean("personelDaoBean");
		
		//Personel personel = new Personel("Altunc", "Altindal", 50);
		
		Personel personel = new Personel();
		
		//personelDao.add(personel); // personelDaoBean'u almamizin amaci zaten icinde dataSourcein referans'i var.
		// System.out.println(personelDao.idSearch(2)); Boyle deriz veya boyle deriz;
		// asagidaki gibi
		
		
		//System.out.println(personelDao.idSearch(2));
		
		System.out.println(personelDao.nameSearch("furkan"));
		
		System.out.println(personelDao.surnameSearch("Kara"));
		
		System.out.println(personelDao.experienceSearch(5));
		
		context.close();              
	}
}
