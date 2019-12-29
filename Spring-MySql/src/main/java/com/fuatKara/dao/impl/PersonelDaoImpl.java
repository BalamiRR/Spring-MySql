package com.fuatKara.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fuatKara.dao.PersonelDao;
import com.fuatKara.model.Personel;

public class PersonelDaoImpl implements PersonelDao {
	// Veriyi veritabanina baglanmamiz lazim.
	private Connection connect = null;// baglanti //Hackerler ulasmasin diye private koyun diyor :D
	private String sqlCode = null;// sqlKodu // null dedeik cunku ortada bisey yok suanlik.
	private DataSource dataBase;// veriKaynagi //bize veri kaynagi lazim

	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}


	public DataSource getDataBase() {
		return dataBase;
	}

	public void setDataBase(DataSource dataBase) {
		this.dataBase = dataBase;
	}

	@Override
	public void add(Personel personel) {
		sqlCode = "INSERT INTO companydb.employee ( name, surname, experience) " + "VALUES ( ?, ?, ?);\r\n";
		// idemployee sildik kendisi otomatik artiyor, cunku primary key.

		Connection connect = null;

		try {
			// Burda hata meydana gelebilicek
			connect = dataBase.getConnection(); // nereye baglanacagini soyluyor.
			// Bir yerde (?, ? ,?) soru isaretli paramatre goruyorsan sql de hemen
			// PreparedStatement yaziyoruz.
			PreparedStatement preparedStatement;
			preparedStatement = connect.prepareStatement(sqlCode); // Insert olan sql aldik icine koyduk
			preparedStatement.setString(1, personel.getName());
			preparedStatement.setString(2, personel.getSurname());
			preparedStatement.setInt(3, personel.getExperience());
			// SSimdi prepareStatementi calistiricaz.
			preparedStatement.executeUpdate(); // Sadece bi kayit ekledgiimiz icin update ediyor.
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println("Error Fuat !" + e);
			//throw new RuntimeException();
			// Hata oldugunda yapilcak islem
		} finally { // Burdada hata olsun olmasin yapicak islem.
			if (connect != null) {// baglanti null degilse
				try {
					connect.close();
				} catch (SQLException e) {

				}
			}

		}
	}

	@Override
	public Personel idSearch(int id) { // BURDAN GELIYOR
		sqlCode = "SELECT * FROM companydb.employee WHERE idemployee = ? ";
		// idemployee sildik kendisi otomatik artiyor, cunku primary key.
		// Connection connect = null; Bunu comment'a aldik cunku yukaridaki variable'i
		// cagirdik.
			
		try {
			// Burda hata meydana gelebilicek
			connect = dataBase.getConnection();
			
			PreparedStatement preparedStatement = connect.prepareStatement(sqlCode);
			preparedStatement.setInt(1, id); // Direk id yazdik cunku yukaridaki parametreden geliyr bu employee id'sini
												// bulmaya calisiyoruz(YUKARIDAKI BURDAN GELIYOR YAZISINA BAK)
			Personel personel = null;

			ResultSet resultSet = preparedStatement.executeQuery(); // executeUpdate() yapmiyoruz bu add() de yaptigmiz
																	// gibi, cunku biz sadece id'si su olani
																	// cagirdiyoruz,bisey eklemiyoruz.eklesek
																	// exectureUpdate yapilir.

			if (resultSet.next()) {// Sorguyu calistir, sonucu getir, buraya depola.
				personel = new Personel(resultSet.getInt("idemployee"), resultSet.getString("name"),resultSet.getString("surname"), resultSet.getInt("experience"));
			}
			// Eger burda bi kayit varsa ilerle(next kullanarak),if kullandik cunku kayit
			// vardir veya yoktur bilmiyoruz.
			resultSet.close();
			preparedStatement.close();
			return personel;
			} catch (Exception e) {
				System.out.println("Error Fuat !" + e);
				throw new RuntimeException();
			} 
			finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
	
					}
				}
			}
		//return null;//Bunun-yerine yukari yaziyor cunku islemimiz yukarida bitiyor.
	}

	@Override
	public Personel nameSearch(String name) {
		sqlCode = "SELECT * FROM companydb.employee WHERE name = ? ";

		try {
			connect = dataBase.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connect.prepareStatement(sqlCode);
			preparedStatement.setString(1, name); 

			Personel personel = null;
			ResultSet resultSet = preparedStatement.executeQuery(); 

			if (resultSet.next()) {
				personel = new Personel(resultSet.getInt("idemployee"), resultSet.getString("name"),resultSet.getString("surname"), resultSet.getInt("experience"));
			}
			
			resultSet.close();
			preparedStatement.close();
			return personel;
		} catch (Exception e) {
			System.out.println("Error Fuat !" + e);
			throw new RuntimeException();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {

				}
			}
		}
	}

	@Override
	public Personel surnameSearch(String surname) {
		sqlCode = "SELECT * FROM companydb.employee WHERE surname = ? ";

		try {
			connect = dataBase.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connect.prepareStatement(sqlCode);
			preparedStatement.setString(1, surname); 

			Personel personel = null;
			ResultSet resultSet = preparedStatement.executeQuery(); 

			if (resultSet.next()) {
				personel = new Personel(resultSet.getInt("idemployee"), resultSet.getString("name"),resultSet.getString("surname"), resultSet.getInt("experience"));
			}
			
			resultSet.close();
			preparedStatement.close();
			return personel;
		} catch (Exception e) {
			System.out.println("Error Fuat !" + e);
			throw new RuntimeException();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {

				}
			}
		}
	}

	@Override
	public Personel experienceSearch(int experience) {
		sqlCode = "SELECT * FROM companydb.employee WHERE experience = ? ";

		try {
			connect = dataBase.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connect.prepareStatement(sqlCode);
			preparedStatement.setInt(1, experience); 

			Personel personel = null;
			ResultSet resultSet = preparedStatement.executeQuery(); 

			if (resultSet.next()) {
				personel = new Personel(resultSet.getInt("idemployee"), resultSet.getString("name"),resultSet.getString("surname"), resultSet.getInt("experience"));
			}
			
			resultSet.close();
			preparedStatement.close();
			return personel;
		} catch (Exception e) {
			System.out.println("Error Fuat !" + e);
			throw new RuntimeException();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {

				}
			}
		}
	}
}
