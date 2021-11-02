package testsuite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;



@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Contacts Test Suite")
@SelectPackages("tests.chrome.Contacts")
@IncludePackages("tests.chrome")

public class ContactsTestSuite {
}
