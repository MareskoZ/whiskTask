package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Browser {
    private static WebDriver driver = null;
    static String baseDir = System.getProperty("user.dir");

    public WebDriver getDesktopChrome() {
        System.setProperty("webdriver.chrome.driver", baseDir + driverDetect());
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private static String driverDetect(){
        String macOSDriver = "/src/drivers/mac/chromedriver";
        String winDriver = "/src/drivers/windows/chromedriver.exe";
        String linuxDriver = "/src/drivers/linux/chromedriver";

        String strOSName = System.getProperty("os.name");

        if (strOSName.toLowerCase().contains("win"))
            return winDriver;
        else if (strOSName.toLowerCase().contains("mac"))
            return macOSDriver;
        else
            return linuxDriver;
    }
}