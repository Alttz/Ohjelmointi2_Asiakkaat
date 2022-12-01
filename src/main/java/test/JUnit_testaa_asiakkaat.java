package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import model.Asiakas;
import model.dao.Dao;

@TestMethodOrder(OrderAnnotation.class)
class JUnit_testaa_asiakkaat {

	@Test
	@Order(1) 
	public void testPoistaKaikkiAsiakkaat() {
		Dao dao = new Dao();
		dao.removeAllItems("Nimda");
		ArrayList<Asiakas> asiakkaat = dao.getAllItems();
		assertEquals(0, asiakkaat.size());		
	}
	
	@Test
	@Order(2) 
	public void testLisaaAsiakas() {
		Dao dao = new Dao();
		Asiakas asiakas_1 = new Asiakas("Antti", "Ahkera", "010-1111111", "ensimmainen@gmail.com");
		Asiakas asiakas_2 = new Asiakas("Bert", "Bergman", "020-2222222", "toinen@gmail.com");
		Asiakas asiakas_3 = new Asiakas("Cecilia", "Casanova", "030-3333333", "kolmas@gmail.com");
		Asiakas asiakas_4 = new Asiakas("David", "Dawnhorn", "040-4444444", "neljas@gmail.com");
		assertEquals(true, dao.addItem(asiakas_1)); //tai assertTrue(dao.addItem(asiakas_1));	
		assertEquals(true, dao.addItem(asiakas_2));
		assertEquals(true, dao.addItem(asiakas_3));
		assertEquals(true, dao.addItem(asiakas_4)); 	
		assertEquals(4, dao.getAllItems().size());		
	}
	
	@Test
	@Order(3) 
	public void testMuutaAsiakas() {
		//Muutetaan asiakas_1 puhelinnumero 050-5555555
		Dao dao = new Dao();		
		ArrayList<Asiakas> asiakkaat = dao.getAllItems("010-1111111");		
		asiakkaat.get(0).setPuhelin("050-5555555");		
		dao.changeItem(asiakkaat.get(0));
		asiakkaat = dao.getAllItems("050-5555555");
		assertEquals("050-5555555", asiakkaat.get(0).getPuhelin());
		assertEquals("Antti", asiakkaat.get(0).getEtunimi());
		assertEquals("Ahkera", asiakkaat.get(0).getSukunimi());
		assertEquals("ensimmainen@gmail.com", asiakkaat.get(0).getSposti());	
	}
	
	@Test
	@Order(4) 
	public void testPoistaAsiakas() {
		//Poistetaan se asiakas, jonka puhelin on 050-5555555
		Dao dao = new Dao();
		ArrayList<Asiakas> asiakkaat = dao.getAllItems("050-5555555");
		dao.removeItem(asiakkaat.get(0).getAsiakas_id());
		assertEquals(0, dao.getAllItems("050-5555555").size());					
	}
	
	@Test
	@Order(5) 
	public void testHaeOlematonAsiakas() {
		//Haetaan asiakas,jonka asiakas_id on -1
		Dao dao = new Dao();
		assertNull(dao.getItem(-1));
	}
}