package com.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewOrgLookUpImg;
	
	
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getCreateNewOrgLookUpImg() {
		return createNewOrgLookUpImg;
	}
	
	public void clickOnCreateOrgLookUp()
	{
		createNewOrgLookUpImg.click();
	}
	
}
