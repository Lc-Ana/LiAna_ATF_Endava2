package cucumber;

import managers.PageObjectsManager;
import managers.WebDriverManager;

public class TestContext {
    private WebDriverManager webDriverManager;
    private PageObjectsManager pageObjectsManager;

    public TestContext(){
        webDriverManager = new WebDriverManager();
        pageObjectsManager = new PageObjectsManager(webDriverManager.getDriver());
    }

    public WebDriverManager getWebDriverManager(){
        return webDriverManager;
    }

    public PageObjectsManager getPageObjectManager(){
        return pageObjectsManager;
    }
}
