package testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CategoryTestSuite.class,
        CompanyTestSuite.class,
        ContactsTestSuite.class,
        DashboardTestSuite.class,
        DepartmentTestSuite.class,
        DevicesTestSuite.class,
        LoginTestSuite.class,
        ManagerTestSuite.class,
        TicketsTestSuite.class
})

public class AllSuites {
}
