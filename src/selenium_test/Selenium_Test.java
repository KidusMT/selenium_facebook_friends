/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium_test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import org.openqa.selenium.Keys;

/**
 *
 * @authors KidusMT, Samu-el
 */
public class Selenium_Test {

    public static List<String> friendsURL = new ArrayList<>();

    public static void getMyFacebookFriends(String uname, String pass) {
        //Select the driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromedriver.exe");

        //install the ultrasurf chrome extension
        ChromeDriver driver = installExtension("C:\\Users\\ultrasurf.crx");

        //Go to the login page of facebook
        driver.get("https://www.facebook.com/login");

        System.out.println("get");

        //Insert username
        WebElement username = driver.findElement(By.name("email"));
        username.sendKeys(uname);

        //Insert password
        WebElement password = driver.findElement(By.name("pass"));
        password.sendKeys(pass);

        password.submit();

        //TODO: Check if login was succesful or not
        System.out.println("submitted and alert dismissed");

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(elementToBeClickable(By.xpath("//*[@title='Profile']"))); // wait until the 

        driver.findElement(By.xpath("//*[@title='Profile']")).click(); // throws erros: must be handled

        //get the number of friends from the profile page in the friends tab next to the word Friends 
        wait.until(presenceOfElementLocated(By.xpath("//*[@class='_gs6']")));
        String friendsCount = driver.findElement(By.xpath("//*[@class='_gs6']")).getText();

        int count = Integer.parseInt(friendsCount);
        System.out.println(friendsCount);
        wait.until(presenceOfElementLocated(By.xpath("//*[@data-tab-key='friends']")));
        driver.findElement(By.xpath("//*[@data-tab-key='friends']")).click();

        wait.until(presenceOfElementLocated(By.xpath("//*[@class='fsl fwb fcb']")));
        //get all the names of the friends displayed in the page
        List<WebElement> friendsNameList = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
        List<WebElement> friendsListItem = driver.findElements(By.xpath("//*[@data-testid=\"friend_list_item\"]"));
        //See how many friends were found

        int found = friendsNameList.size();

        //If all of the friends are not found we need to scroll the page and load all of them
        while (found <= count) {
            //Get the coordinates of the last friend displayed on the page
            Coordinates coordinate = ((Locatable) friendsNameList.get(found - 1)).getCoordinates();

            //scroll the page
            coordinate.onPage();
            coordinate.inViewPort();

            //add all firends names to the list
            friendsNameList = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
            found = friendsNameList.size();
            //System.out.println(found);
            //all of the friends are found so start printing
            if (count/2 <= found) {
                for (int i = 0; i < found; i++) {
                    friendsURL.add(friendsListItem.get(i).findElement(By.tagName("a")).getAttribute("href"));
                    WebElement image = friendsListItem.get(i).findElement(By.tagName("img"));
                    try {
                        getProfilePicture(driver, image);
                    } catch (IOException ex) {
                        System.out.println("Error downloading Profile picture");
                    }
                }
                exportData(friendsListItem, driver, "friends.csv");
                break;
            }

        }
        //start 4 threads that start 4 new tabs to get the friends of friends of the user concurrently
        FriendsOfFriendsThread t1 = new FriendsOfFriendsThread(driver, 0, friendsURL.size() / 4);
        FriendsOfFriendsThread t2 = new FriendsOfFriendsThread(driver, friendsURL.size() / 4, friendsURL.size() / 2);
        FriendsOfFriendsThread t3 = new FriendsOfFriendsThread(driver, friendsURL.size() / 2, 3 * friendsURL.size() / 4);
        FriendsOfFriendsThread t4 = new FriendsOfFriendsThread(driver, 3 * friendsURL.size() / 4, friendsURL.size());

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    public static ChromeDriver installExtension(String path) {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(path));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);//disable notifs
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false); //disable save password pop up option
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capablities = new DesiredCapabilities();
        capablities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capablities);
        return driver;
    }

    public static void getFriendsOfFirends(List<String> profileUrl, int min, int max, ChromeDriver driver) {
        for (int j = min; j < max; j++) {
            String url = profileUrl.get(j);
            String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, "t");
            driver.findElement(By.tagName("body")).sendKeys(selectLinkOpeninNewTab);
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(elementToBeClickable(By.xpath("//*[@title='Profile']"))); // wait until the 

            driver.findElement(By.xpath("//*[@title='Profile']")).click();

            wait.until(presenceOfElementLocated(By.xpath("//*[@class='_gs6']")));
            String friendsCount = driver.findElement(By.xpath("//*[@class='_gs6']")).getText();
            int count = Integer.parseInt(friendsCount);

            wait.until(presenceOfElementLocated(By.xpath("//*[@data-tab-key='friends']")));
            driver.findElement(By.xpath("//*[@data-tab-key='friends']")).click();

            wait.until(presenceOfElementLocated(By.xpath("//*[@class='fsl fwb fcb']")));

            List<WebElement> friendsOfFriends = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
            List<WebElement> friendsListItem = driver.findElements(By.xpath("//*[@data-testid=\"friend_list_item\"]"));
            //See how many friends were found

            int found = friendsOfFriends.size();

            //If all of the friends are not found we need to scroll the page and load all of them
            while (found <= count) {
                //Get the coordinates of the last friend displayed on the page
                Coordinates coordinate = ((Locatable) friendsOfFriends.get(found - 1)).getCoordinates();

                //scroll the page
                coordinate.onPage();
                coordinate.inViewPort();

                //add all firends names to the list
                friendsOfFriends = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
                found = friendsOfFriends.size();

                if (count/2< found - 1) { // after finding 2% of friend export data (for testing)
                    exportData(friendsListItem, driver, "friends_of_" + url.substring(25, url.indexOf("?")) + ".csv");
                    break;
                }

            }

        }

    }

    public static void getProfilePicture(ChromeDriver driver, WebElement img) throws MalformedURLException, IOException {

        String urlImage = img.getAttribute("src").substring(0, img.getAttribute("src").indexOf("?"));
        URL url = new URL(urlImage);
        ByteArrayOutputStream out;
        try (InputStream in = new BufferedInputStream(url.openStream())) {
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
        }
        byte[] response = out.toByteArray();
        //C://Users//
        FileOutputStream fos = new FileOutputStream("C:\\Users\\profile_pics" + img.getAttribute("aria-label") + new Date().getTime() + ".jpg");

        fos.write(response);
        fos.close();
    }

    public static void exportData(List<WebElement> lst, ChromeDriver driver, String filename) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("C:\\Users\\" + filename);
            writer.append("Name");
            writer.append(',');
            writer.append("Phone Number");
            writer.append('\n');
            for (WebElement i : lst) {
                //String name = i.findElement(By.tagName("a")).getText();
                String name = i.getText();
                // String email = driver.findElement(By.id(value)).getAttribute("title");
                writer.append(name);
                //writer.append(',');
                //writer.append(email);
                writer.append('\n');
                writer.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Selenium_Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Selenium_Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
