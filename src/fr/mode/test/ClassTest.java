package fr.mode.test;
import static org.junit.Assert.*;

import org.junit.Test;

import fr.mode.model.Obstacle;
import fr.mode.model.ObstacleRect;


public class ClassTest{


	Obstacle a = new ObstacleRect(40, 400, 80, 50);

	@Test
	public void test1() {
		assertTrue(true);
	}

	@Test
	public void testPosX(){
		assertEquals((int)a.getCorpsPosX(),40);
	}

	@Test
	public void testPosY(){
		assertNotEquals((int)a.getCorpsPosY(),10);
	}

	@Test
	public void testHeight(){
		assertEquals((int)a.getDimensionsHeight(),80);
	}

	@Test
	public void testLenght(){
		assertNotEquals((int)a.getDimensionsLenght(),10);
	}






}
