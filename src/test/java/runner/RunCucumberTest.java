package runner;


/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Jan-25
 */

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"step_definitions"},
        tags = "@login",
        plugin = {"pretty", "json:target/cucumber.json", "html:target/cucumber-report.html"}
)

public class RunCucumberTest extends AbstractTestNGCucumberTests {

    private static final Logger logger = Logger.getLogger(RunCucumberTest.class.getName());
    private static Properties properties = new Properties();
    static{
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config.properties").toString()));
        try(InputStream inputStream = Files.newInputStream(configPath)) {
            properties.load(inputStream);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load config.properties", e);
        }
    }

    public static void main(String[] args) {
        TestNG testNG = new TestNG();

        // Create a new TestNG XML suite
        XmlSuite suite = new XmlSuite();

        //Get the thread count from the properties file
        int threadCount = getThreadCount();
        System.out.println("Configured thread count: " + threadCount);
        logger.info("Configured thread count: " + threadCount);

        // Set the number of threads for the data provider
        suite.setDataProviderThreadCount(threadCount);

        // Create a new TestNG test and add it to the suite
        XmlTest test = new XmlTest(suite);
        test.setName("RunCucumberTest");

        // Add the test class to the test
        test.setXmlClasses(Collections.singletonList(new XmlClass(RunCucumberTest.class)));

        // Disable default listeners (Will disable TestNG reports from being generated)
        testNG.setUseDefaultListeners(false);

        // Add the suite to the TestNG instance
        testNG.setXmlSuites(Collections.singletonList(suite));

        // Run the TestNG with the configured suite
        testNG.run();
    }

    //Method to get the thread count
    private static int getThreadCount() {
        return Integer.parseInt(properties.getProperty("thread.count", "1"));
    }


    // Data Provider Method. Used for parallel execution
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();  //Provide data for the tests, enabling parallel execution
    }

}
