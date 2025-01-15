package browser;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Jan-25
 */


import com.microsoft.playwright.*;

import java.awt.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserManager {
    // A ThreadLocal is like a personal locker for each thread, so they don't share data with other threads.
    // Think of threads as individual workers in a factory, each with their own toolbox.

    // used to create an instance of the Chromium, firefox, or webkit browsers
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    //public Playwright playwright;  // used to create an instance of the Chromium, firefox, or webkit browsers

    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    //public Browser browser;  // represents the browser instance

    // Represents a browser context (a separate browsing session); Each context has its own set of cookies, cache, and storage.
    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    // public BrowserContext browserContext

    // Represents a single web page within a context( represents a single tab or page in the browser)
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
    // public Page page



    public Properties properties;
    private static final Logger logger = Logger.getLogger(BrowserManager.class.getName());

    public BrowserManager() {
        properties = new Properties();
        Path configPath = Paths.get(System.getProperty("config.path", Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config.properties").toString()));
        try(InputStream inputStream = Files.newInputStream(configPath)) {
            properties.load(inputStream);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load config.properties", e);
        }
    }


    public Page getPage() {
        return page.get();
    }
    public void setPage(Page newPage) {
        page.set(newPage);
    }

    public BrowserContext getBrowserContext() {
        return browserContext.get();
    }

    public byte[] takeScreenshot() {
        if(page.get() != null){
            return page.get().screenshot();
        }
       return new byte[0];
    }

    public void setUp() {
        logger.info("Setting up Playwright...");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        try {
            playwright.set(Playwright.create());

            String browserName = System.getProperty("BROWSER");
            if(browserName == null || browserName.isEmpty()) {
                browserName = properties.getProperty("browser", "chromium");
            }
            switch (browserName) {
                // case "firefox" -> browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false))
                case "firefox" -> browser.set(playwright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                // case "webkit" -> browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false))
                case "webkit" -> browser.set(playwright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                // default -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                default -> browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            }

            // browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(screenWidth, screenHeight))
            browserContext.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(screenWidth, screenHeight)));
            // page = browserContext.newPage()
            page.set(browserContext.get().newPage());

            // Set timeouts from properties file
            int navigationTimeout = Integer.parseInt(properties.getProperty("navigation.timeout", "30000"));
            int actionTimeout = Integer.parseInt(properties.getProperty("action.timeout", "15000"));
            page.get().setDefaultNavigationTimeout(navigationTimeout);
            page.get().setDefaultTimeout(actionTimeout);
            logger.info("Playwright setup complete...");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to setup Playwright!!!!!", e);
        }
    }

    public void tearDown() {
        try{
            logger.info("Tearing down Playwright...");
            if(page.get() != null) {
                page.get().close();
            }
            if(browserContext.get() != null) {
                browserContext.get().close();
            }
            if(browser.get() != null) {
                browser.get().close();
            }
            if(playwright.get() != null) {
                playwright.get().close();
            }
            logger.info("Playwright teardown complete...");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to teardown Playwright resources", e);
        }
    }

}
