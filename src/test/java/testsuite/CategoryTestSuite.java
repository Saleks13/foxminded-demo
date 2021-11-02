package testsuite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Category Test Suite")
@SelectPackages({"tests.chrome.Category", "tests.selenide.Category"})

public class CategoryTestSuite {
}
