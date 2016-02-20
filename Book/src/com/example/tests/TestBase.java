package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import com.example.fw.ApplicationManager;
import static com.example.tests.GroupDataGenerator.generateRandomGroups;

public class TestBase {
	
	protected ApplicationManager app;

	@BeforeClass
	public void setUp() throws Exception {
		Properties properties = new Properties();
		//properties for browser and URL
		properties.load(new FileReader(new File("application.properties")));
		app = new ApplicationManager(properties);
	  }
	
	@AfterClass
	public void tearDown() throws Exception {
		app.stop();
	  }
	
	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator() {
		List<GroupData> groups = generateRandomGroups(5);
		List<Object[]> list = wrapGroupsForDataProvider(groups);
		return list.iterator();
	}
	

	public static List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (GroupData group : groups) {
			list.add(new Object[] {group});
		}
		return list;
	}


}
