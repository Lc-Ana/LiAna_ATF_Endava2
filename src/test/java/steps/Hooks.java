package steps;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private TestContext testContext;

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void setup() {
        logger.info("Browser opens");
        testContext.getWebDriverManager().getDriver();
    }

    @After
    public void tearDown() {
        logger.info("Browser is closing");
        testContext.getWebDriverManager().closeDriver();
    }
}
