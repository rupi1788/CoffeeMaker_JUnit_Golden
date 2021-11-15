/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.Assert.*;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}

	@Test
	public void testAddRecipe() throws RecipeException {
		CoffeeMaker coffeeMaker8= new CoffeeMaker();
		//Set up for r4

		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("75");
		assertTrue( coffeeMaker8.addRecipe(recipe4));

	}


	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}

	@Test
	public void testAddInventory1() throws InventoryException {
		CoffeeMaker coffeeMaker5 = new CoffeeMaker();
		coffeeMaker5.addInventory("15","15","0","15");
		assertEquals("Coffee: 30\nMilk: 30\nSugar: 15\nChocolate: 30\n", coffeeMaker5.checkInventory());
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {

		coffeeMaker.addInventory("4", "-1", "5", "3");
	}
	@Test(expected = InventoryException.class)
	public void testAddInventoryException1() throws InventoryException {
		coffeeMaker.addInventory("4", "6", "asdf", "3");
	}


	/**
	 * Given a coffee maker with one existing inventory
	 *    having each ingredient amount 15
	 * When we check inventory
	 * Then we get the correct list of ingredients
	 *   with correct amount available.
	 */
	@Test
	public void testCheckInventory(){
		CoffeeMaker coffeeMaker4 = new CoffeeMaker();
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMaker4.checkInventory());
	}




	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	//@Test
	//public void testMakeCoffee() {
	//	coffeeMaker.addRecipe(recipe1);
	//	assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	//}

	@Test
	public void testMakeCoffee1() throws RecipeException, InventoryException {
		CoffeeMaker coffeeMaker1 = new CoffeeMaker();
		//coffeeMaker1.addInventory("20","20", "20", "20");
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("2");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		coffeeMaker1.addRecipe(recipe1);
		assertEquals(75, coffeeMaker1.makeCoffee(0, 125));
	}

    @Test
	public void testMakeCoffee1A() throws RecipeException {
		coffeeMaker.addRecipe(recipe1);
		CoffeeMaker coffeeMaker1 = new CoffeeMaker();
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("2");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		coffeeMaker1.addRecipe(recipe1);
		assertEquals(0, coffeeMaker1.makeCoffee(0, 50));
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying less than
	 * 	the coffee costs
	 * Then we get the amount paid back.
	 */
	@Test
	public void testMakeCoffee2() throws RecipeException {
		CoffeeMaker coffeeMaker2 = new CoffeeMaker();
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("2");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		coffeeMaker2.addRecipe(recipe1);

		assertEquals(25, coffeeMaker2.makeCoffee(1, 25));
	}
	/**
	 * Given a coffee maker with one valid recipe but in
	 *   the inventory one ingredient is not available
	 * When we make coffee, selecting the valid recipe and paying more than
	 * 		the coffee costs
	 *
	 * Then we get the amount paid back.
	 */
	@Test
	public void testMakeCoffee3() throws RecipeException {
		CoffeeMaker coffeeMaker3 = new CoffeeMaker();
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		coffeeMaker3.addRecipe(recipe2);

		assertEquals(100, coffeeMaker3.makeCoffee(0, 100));
	}



}