package ie.cit.adf.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardServiceImplTest.class, UserServiceImplTest.class, LinkServiceImplTest.class })
public class AllServiceTests {

}
