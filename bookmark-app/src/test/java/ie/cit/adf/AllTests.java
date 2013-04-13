package ie.cit.adf;

import ie.cit.adf.integration.DashboardIntegrationTests;
import ie.cit.adf.integration.UserIntegrationTests;
import ie.cit.adf.services.BoardServiceImplTest;
import ie.cit.adf.services.LinkServiceImplTest;
import ie.cit.adf.services.UserServiceImplTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardServiceImplTest.class, UserServiceImplTest.class, LinkServiceImplTest.class, UserIntegrationTests.class, DashboardIntegrationTests.class})
public class AllTests {

}
