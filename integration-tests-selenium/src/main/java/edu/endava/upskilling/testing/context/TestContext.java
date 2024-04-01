package edu.endava.upskilling.testing.context;

import edu.endava.upskilling.testing.managers.PageObjectsManager;
import edu.endava.upskilling.testing.managers.WebDriverManager;

public class TestContext {
    private final WebDriverManager webDriverManager;
    private final PageObjectsManager pageObjectsManager;
    public ScenarioContext scenarioContext;

    public TestContext(){
        webDriverManager = new WebDriverManager();
        pageObjectsManager = new PageObjectsManager(webDriverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public WebDriverManager getWebDriverManager(){
        return webDriverManager;
    }

    public PageObjectsManager getPageObjectManager(){
        return pageObjectsManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
