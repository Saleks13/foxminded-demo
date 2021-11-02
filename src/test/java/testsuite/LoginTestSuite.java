package testsuite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;



@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Login Test Suite")
@SelectPackages("tests.chrome.Login")
@IncludePackages("tests.chrome")
public class LoginTestSuite {

}
