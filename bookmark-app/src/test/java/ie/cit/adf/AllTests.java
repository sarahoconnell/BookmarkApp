package ie.cit.adf;

import ie.cit.adf.integration.DashboardIntegrationTest;
import ie.cit.adf.integration.LoginIntegrationTest;
import ie.cit.adf.integration.UserIntegrationTest;
import ie.cit.adf.rest.RestClientTests;
import ie.cit.adf.services.BoardServiceImplTest;
import ie.cit.adf.services.LinkServiceImplTest;
import ie.cit.adf.services.UserServiceImplTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// NOTE: Localhost server is required for RestClientTest.

@RunWith(Suite.class)
@SuiteClasses({ BoardServiceImplTest.class, UserServiceImplTest.class, LinkServiceImplTest.class, UserIntegrationTest.class, DashboardIntegrationTest.class, LoginIntegrationTest.class})
public class AllTests {

}
