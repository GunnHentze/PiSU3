package dk.dtu.compute.se.pisd.list.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dk.dtu.compute.se.pisd.list.List;

/**
 * This is an abstract test class for all kinds of list implementations.
 * Sub-classes must create a {@see dk.dtu.compute.se.pisd.stack.Stack}
 * of the specific kind in their {@see #setUp()} method.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public abstract class TestList {

	protected List list;

	final int TESTLIMIT = 100;
	
	@Before
	abstract public void setUp() throws Exception;

	@Test
	public void isEmptyOnCreation() {
		Assert.assertTrue("List is not empty initally", list.isEmpty());
	}
	
	@Test
	public void addValuesAtHead() {
		for (int i = 0; i <= TESTLIMIT; i++) {
			list.add(0,i);
			int value = list.get(0);
			Assert.assertEquals(
					"Added value is not first value",
					i,
					value);
		}

		Assert.assertEquals("Size of list is incorrect",
				TESTLIMIT+1,
				list.size());
		
		for (int i = 0; i <= TESTLIMIT; i++) {
			int value = list.get(i);
			Assert.assertEquals(
					"Wrong value at position", 
					TESTLIMIT-i,
					value);
		}
	}
	
	@Test
	public void addValuesAtTail() {
		for (int i = 0; i <= TESTLIMIT; i++) {
			// insert at position a bit (5 positions) higher than
			// size of the list
			list.add(i+5,i);
			int value = list.get(i);
			Assert.assertEquals(
					"Added value is not last list value",
					i,
					value);
		}

		Assert.assertEquals("Size of list is incorrect",
				TESTLIMIT+1,
				list.size());
		
		for (int i = 0; i <= TESTLIMIT; i++) {
			int value = list.get(i);
			Assert.assertEquals(
					"Wrong value at position", 
					i,
					value);
		}
	}
	
	@Test
	public void addValuesToMiddleAndSort() {
		// Here we do parallel testing: we compare whether the
		// implemented list behaves as the Java built-in ArrayList
		ArrayList<Integer> parallelList = new ArrayList<Integer>();
		
		for (int i = 0; i <= TESTLIMIT; i++) {
			int pos = list.size()/2;
			list.add(pos,i);
			parallelList.add(pos,i);
			int value = list.get(pos);
			Assert.assertEquals(
					"Added value at position incorrect",
					i,
					value);
		}
		
		Assert.assertEquals("Size of list is incorrect",
				TESTLIMIT+1,
				list.size());
		
		for (int i = 0; i <= TESTLIMIT; i++) {
			int value = list.get(i);
			int parallelListValue = (int) parallelList.get(i);
			Assert.assertEquals(
					"Wrong value at position " + i, 
					parallelListValue,
					value);
		}
	}
	
	@Test
	public void addAndRemoveValues() {
		for (int i = 0; i <= TESTLIMIT; i++) {
			list.add(0,i);
		}

		Assert.assertEquals("Size of list is incorrect",
				TESTLIMIT+1,
				list.size());
		
		for (int i = TESTLIMIT; i >= 0; i=i-2) {
			int value = list.remove(i);
			Assert.assertEquals(
					"Wrong value at position", 
					TESTLIMIT-i,
					value);
		}
		
		Assert.assertEquals("Size of list is incorrect",
				(TESTLIMIT+1) / 2,
				list.size());
		
		for (int i = 0; i < list.size(); i++) {
			int value = list.get(i);
			Assert.assertEquals(
					"Value is wrong at position " + i,
					(TESTLIMIT-i*2-(TESTLIMIT+1)%2),
					value);
		}

	}
	
	@Test
	public void getFirstOnEmptyStack() {
		Assert.assertNull("get(0) on empty list not null", list.get(0));	
	}

	@Test
	public void sortReversedOrderList() {
		for (int i = 0; i <= TESTLIMIT; i++) {
			list.add(0,i);
		}
		
		list.sort();
		
		for (int i = 0; i <=  TESTLIMIT; i++) {
			
			int value = list.get(i);
		//	System.out.println(list.get(i));
			//System.out.println(i);
			Assert.assertEquals(
					"Wrong value at position " + i, 
					i,
					value);

		}
	}
	
	@Test
	public void testIndexOf() {
		for(int i=0; i<=TESTLIMIT; i++) {
			list.add(0, i);
		}
		list.sort();
		
		for(int i=0; i<=TESTLIMIT; i++) {
			int value = list.indexOf(i);
			
			Assert.assertEquals(
					"Wrong value at position " + i, 
					i,
					value);
		}
	}

}
