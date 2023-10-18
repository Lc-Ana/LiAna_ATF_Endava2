package hooks;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void setup() {
        testContext.getWebDriverManager().getDriver();
    }

    @After
    public void tearDown() {
        testContext.getWebDriverManager().closeDriver();
    }
}
