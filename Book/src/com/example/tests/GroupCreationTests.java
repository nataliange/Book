package com.example.tests;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
	
	
	@DataProvider
	//read test data from csv/xml file
	public Iterator<Object[]> groupsFromFile() throws IOException {
		/*return wrapGroupsForDataProvider (loadGroupsFromCsvFile(new File("groups.txt"))).iterator();*/
		return wrapGroupsForDataProvider (loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
	}


@Test(dataProvider = "groupsFromFile")//randomValidGroupGenerator
  public void testNonEmptyGroupCreation(GroupData group) throws Exception {
    app.getNavigationHelper().openMainPage();
    Thread.sleep(2000);
    app.getNavigationHelper().goToGroupsPage();
    
    //save old list of groups before creating new groups
    //1. take info about groups from the interface
    /*List<GroupData> oldList = app.getGroupHelper().getGroups();*/
    
    //2. take info about groups from the database
    List<GroupData> oldList = app.getHibernateHelper().listGroups();
    
    //take actions: create new groups
    app.getGroupHelper()
    .initGroupCreation()
	.fillGroupForm(group)
    .submitGroupCreation()
    .returnToGroupsPage();
    
    //save new groups list after creating new groups.Take info about groups from the interface
    List<GroupData> newList = app.getGroupHelper().getGroups();

    
    //compare sizes of lists
    //assertEquals(newList.size(), oldList.size() + 1);
    
    //oldList with a new element, which was added via Interface: see fillGroupForm(group)
    oldList.add(group);
    //sort the old list
   Collections.sort(oldList);
   //compare two lists: oldList (data from DB) and newList (data taken from the user interface) 
    assertEquals(newList, oldList);
  } 

}
