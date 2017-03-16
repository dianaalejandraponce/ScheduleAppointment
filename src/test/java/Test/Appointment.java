package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Page.PageAppointment;

public class Appointment {
	
	public WebDriver driver;
	public PageAppointment pageAppoinment;


	
	public Appointment() {
		System.setProperty("webdriver.gecko.marionette", "/Users/macuser/Documents/Selenium/geckodriver");
		driver = new FirefoxDriver();
		pageAppoinment = new PageAppointment(driver);
	}
	
	//Enter data on the form
	@BeforeTest
	@Parameters({"page","date","patientId","doctorId","note"})
	public void formData(String page, String date, String patient,String doctor, String note){
	driver.get(page);
	pageAppoinment.enterData(date, patient, doctor, note);
	}
	
	//Validate the fields Patient and Doctor are required
	@Test
	@Parameters({"patientId","doctorId"})
	public void validateFieldsEmpty(String patient, String doctor){
		Assert.assertFalse(pageAppoinment.fieldsEmpty(patient,doctor),"The patient and/or Doctor are required");
	}
	
	//Validate that Patient Id and Doctor Id are numerics
	@Test
	@Parameters({"patientId","doctorId"})
	public void validateDatasNumerics(String patient, String doctor){
		Assert.assertTrue(pageAppoinment.isNumeric(patient, doctor),"The patient and/or Doctor should be numerics");
	}
	
	//Validate that the date is in the correct format MM-dd-yyyy 
	@Test
	@Parameters({"date"})
	public void validateDateFormat(String date){
		Assert.assertTrue(pageAppoinment.isDateFormat(date),"The format date is incorrect");
	}

	
	//Validate that the date appointment don't be less than current date
	@Test
	@Parameters({"date"})
	public void validateDateAppoinment(String date){
		Assert.assertFalse(pageAppoinment.isDateLess(date), "The date can not be less than the currente date");
		
	}

}
