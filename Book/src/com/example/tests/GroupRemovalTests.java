package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase {
	
	@Test
	
	public void deleteSomeGroup(){
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().goToGroupsPage();
		
		//save old list of groups before deleting a group
	    List<GroupData> oldList = app.getGroupHelper().getGroups();
	    
	    Random rnd = new Random();
	    
	    ////take actions: delete any element from 0 till size-1
	    int index = rnd.nextInt(oldList.size()-1);
		app.getGroupHelper()
		.deleteGroup(index)
		.returnToGroupsPage();
		
	    //save new groups list after deleting a group
	    List<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //remove by index and compare groups lists
	    oldList.remove(index);
	   Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}

}
