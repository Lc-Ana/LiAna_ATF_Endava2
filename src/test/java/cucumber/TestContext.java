package cucumber;

import managers.PageObjectsManager;
import managers.WebDriverManager;

public class TestContext {
    private WebDriverManager webDriverManager;
    private PageObjectsManager pageObjectsManager;
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
