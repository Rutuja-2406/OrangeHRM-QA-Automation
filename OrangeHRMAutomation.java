package Selenium25.Selenium25;
	
import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;

	public class OrangeHRMAutomation {
	    public static void main(String[] args) throws InterruptedException {
	        
	        // 1. Launch browser
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        
	      
	        // 2. Open OrangeHRM login page
	        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	        Thread.sleep(5000); // Wait for login page

	        // 3. Login
	        driver.findElement(By.name("username")).sendKeys("Admin");
	        driver.findElement(By.name("password")).sendKeys("admin123");
	        driver.findElement(By.cssSelector("button[type='submit']")).click();
	        Thread.sleep(5000);

	        // 4. Click on PIM module
	        driver.findElement(By.xpath("//span[text()='PIM']")).click();
	        Thread.sleep(5000);

	        // 5. Add employees
	        String[][] employees = {
	                {"Rutuja", "Deshmukh"},
	                {"Parikshit", "Deshmukh"},
	                {"Mayuri", "Deshmukh"}
	        };

	        for (String[] emp : employees) {
	            driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
	            Thread.sleep(5000);

	            driver.findElement(By.name("firstName")).sendKeys(emp[0]);
	            driver.findElement(By.name("lastName")).sendKeys(emp[1]);
	            driver.findElement(By.xpath("//button[@type='submit']")).click();

	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//span[text()='PIM']")).click();
	            Thread.sleep(2000);
	        }

	        // 6. Go to Employee List
	        driver.findElement(By.xpath("//a[text()='Employee List']")).click();
	        Thread.sleep(5000);

	        // 7. Verify employee names
	        for (String[] emp : employees) {
	            String fullName = emp[0] + " " + emp[1];
	            List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));

	            boolean found = false;
	            for (WebElement row : rows) {
	                if (row.getText().contains(fullName)) {
	                    System.out.println("Name Verified: " + fullName);
	                    found = true;
	                    break;
	                }
	            }
	            if (!found) {
	                System.out.println("Name NOT Found: " + fullName);
	            }
	        }

	        // 8. Log out
	        driver.findElement(By.className("oxd-userdropdown-name")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("//a[text()='Logout']")).click();

	        // 9. Close browser
	        Thread.sleep(3000);
	        driver.quit();
	    }
	}
