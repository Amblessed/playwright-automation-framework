package step_definitions.hooks;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 13-Jan-25
 */


import browser.BrowserManager;
import io.cucumber.java.*;

public class Hooks {

    private final BrowserManager browserManager;

    public Hooks(BrowserManager browserManager){
        this.browserManager = browserManager;
    }

    @BeforeAll
    public static void beforeAll(){
        System.out.println("\nExecuting test suite.....");
    }


    @AfterAll
    public static void afterAll(){
        System.out.println("\nTest suite execution completed.......");
    }


    @Before
    public void setUp(Scenario scenario){
        System.out.println("Executing Scenario: " + scenario.getName());
        browserManager.setUp();
    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("Scenario execution completed: " + scenario.getName());
        if( scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
            byte[] screenshot = browserManager.takeScreenshot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        browserManager.tearDown();
    }
}
