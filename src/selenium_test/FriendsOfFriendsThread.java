/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium_test;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Kelly
 */
public class FriendsOfFriendsThread extends Thread{
    private final ChromeDriver driver;
    private final int min;
    private final int max;
    
    public FriendsOfFriendsThread(ChromeDriver driver, int min, int max){
        this.driver = driver;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        Selenium_Test.getFriendsOfFirends(Selenium_Test.friendsURL, min, max, driver);
    }
    
}
