package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{
	
	@Test(dataProvider = "randomValidGroupGenerator")
	public void modifySomeGroup(GroupData group){
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().goToGroupsPage();
		
		//save old list of groups before modifying a group
		//1. take info about groups from the interface
		 List<GroupData> oldList = app.getGroupHelper().getGroups();
		 
		    Random rnd = new Random();
		    //modify any element from 0 till size-1
		    int index = rnd.nextInt(oldList.size()-1);
	    
		  //take actions: create new groups
		app.getGroupHelper()
		.initGroupModification(index)
		.fillGroupForm(group)
		.submitGroupModification()
		.returnToGroupsPage();
		
		//save new groups list after modifying a group.Take info about groups from the interface
	    List<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //modification is splitted into two steps: delete and add
	    oldList.remove(index);
	    oldList.add(group);
	    
	    Collections.sort(oldList);
	    
	  //compare two lists: oldList and newList
	     assertEquals(newList, oldList);
	}

}
