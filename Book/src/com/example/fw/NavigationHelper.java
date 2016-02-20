package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends WebDriverHelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}



	public void goToGroupsPage() {
		if (! onGroupsPage()) {	
			click(By.linkText("groups"));
		}
	}

	private boolean onGroupsPage() {
		if (driver.getCurrentUrl().contains("/group.php") 
			&& driver.findElements(By.name("name")).size() > 0) {
				return true;
		} else {
		return false;
	}
		}

	public void openMainPage() {
		driver.get(manager.baseUrl + "/addressbookv4.1.4/");
	}

/*	public void openMainPage() {
		if (! onMainPage()) {
			driver.get(manager.baseUrl + "/addressbookv4.1.4/");
		}
	}

	private boolean onMainPage() {
		return driver.findElements(By.id("maintable")).size() > 0;

	}*/



	public void returnToMainPage() {
		click(By.linkText("home page"));
	}



}
