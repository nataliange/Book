package com.example.fw;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.GroupData;


public class GroupHelper extends WebDriverHelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		return this;
	}

	public GroupHelper fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.getGroupname());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());
		return this;
	}

	public GroupHelper initGroupCreation() {
		click(By.name("new"));
		return this;
	}
	
	public GroupHelper returnToGroupsPage() {
		click(By.linkText("group page"));
		return this;
	}

	public GroupHelper deleteGroup(int index) {
		selectGroupByIndex(index);
		click(By.name("delete"));
		return this;
	}

	public GroupHelper selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]']["+ (index+1) +"]"));
		return this;
	}

	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		return this;
	}
	
	public List<GroupData> getGroups() {
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			String groupname = title.substring("Select (".length(), title.length() - ")".length());
			groups.add(new GroupData().withName(groupname));
		}
		return groups;
	}

}
