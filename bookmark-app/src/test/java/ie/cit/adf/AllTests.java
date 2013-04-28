package ie.cit.adf;

import ie.cit.adf.integration.DashboardIntegrationTests;
import ie.cit.adf.integration.LoginIntegrationTests;
import ie.cit.adf.integration.UserIntegrationTests;
import ie.cit.adf.rest.RestClientTest;
import ie.cit.adf.services.BoardServiceImplTest;
import ie.cit.adf.services.LinkServiceImplTest;
import ie.cit.adf.services.UserServiceImplTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// NOTE: Localhost server is required for RestClientTest.

@RunWith(Suite.class)
@SuiteClasses({ BoardServiceImplTest.class, UserServiceImplTest.class, LinkServiceImplTest.class, UserIntegrationTests.class, DashboardIntegrationTests.class, RestClientTest.class, LoginIntegrationTests.class})
public class AllTests {

}
