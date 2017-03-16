package Page;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAppointment {
	
	public WebDriver driver;

	public PageAppointment(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[for='patient-identification'] + input")
	public WebElement patientBox;
	
	@FindBy(css="[for='doctor-identification'] + input")
	public WebElement doctorBox;
	
	@FindBy(id="datepicker")
	public WebElement dateDataPicker;
	
	@FindBy(css="[for='note'] + textarea")
	public WebElement noteTextAre;
	
	@FindBy(css=".btn.btn-primary.pull-right")
	public WebElement saveButton;
	
	//Method to enter data on the form
	public void enterData(String Date, String patientID, String doctorID, String note){
		dateDataPicker.sendKeys(Date);
		patientBox.sendKeys(patientID);
		doctorBox.sendKeys(doctorID);
		noteTextAre.sendKeys(note);
		saveButton.click();
	}
	
	//Method to validate if Patient and doctor are empties
	public boolean fieldsEmpty(String patient, String doctor){	
		if (patient.isEmpty() || doctor.isEmpty()){
    	return true;
		}
		return false;
    }
	
	//Method to validate if the Patient identification and doctor identification are numerics
	public static boolean isNumeric(String patient, String doctor)  
	{  
	  try  
	  {  
	    double patientId = Double.parseDouble(patient); 
	    double doctorId = Double.parseDouble(doctor);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	//Method to validate if the Date Appointment has the correct format
	public boolean isDateFormat(String date){		
		Date dateFormat = null;
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		    dateFormat = sdf.parse(date);
		    if (!date.equals(sdf.format(date))) {
		        date = null;
		    }
		} catch (ParseException ex) {
		    ex.printStackTrace();
		}
		if (date == null) {
			return false;
		} else {
			return true;
		}

		
		/*SimpleDateFormat dateFormat =  new SimpleDateFormat("MM-dd-yyyy");
		Date dateAppointment=null;
		
		try {
			dateAppointment = dateFormat.parse(date);
			} catch (ParseException e) {
				return false;
				}
		return true;*/
		}
	
	//Method to validate if date appointment is less than current date
	public boolean isDateLess (String date){		
	    if(date.isEmpty() || date.trim().equals("")){
	        return true;
	    }else{
	            SimpleDateFormat dateFormat =  new SimpleDateFormat("MM-dd-yyyy");
	            Date dateAppointment=null;
	            Date currentDate=null;
	            String today= getToday("MM-dd-yyyy");
	            try {
	       	        dateAppointment = dateFormat.parse(date);
	                currentDate = dateFormat.parse(today);
	                if(currentDate.compareTo(dateAppointment) <0){
	                    return false;
	                }else if(dateAppointment.compareTo(currentDate)==0){// both date are same
	                	return false;
	                }else{//expired
	                    return true;
	                }
	            } catch (ParseException e) {
	                e.printStackTrace();                    
	                return true;
	            }
	    }
	}

	  public static String getToday(String format){
	     Date date = new Date();
	     return new SimpleDateFormat(format).format(date);
	 }
	  

}
	
