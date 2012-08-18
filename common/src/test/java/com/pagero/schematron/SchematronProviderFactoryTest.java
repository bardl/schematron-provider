package com.pagero.schematron;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SchematronProviderFactoryTest {

    @Test
    public void testOnlyOneInstance() {
        SchematronProviderFactory factory1 = SchematronProviderFactory.newInstance();
        SchematronProviderFactory factory2 = SchematronProviderFactory.newInstance();
        Assert.assertTrue(factory1 == factory2, "SchematronProviderFactory should be the same instance for all factories.");
    }

    @Test
    public void createTestSchematronProvider() throws NoSchematronProviderFoundException {
        SchematronProvider schematronProvider = SchematronProviderFactory.newInstance().createSchematronProvider("testId");
        Assert.assertEquals(schematronProvider.getId(), "testId");
    }

    @Test(expectedExceptions = NoSchematronProviderFoundException.class)
    public void createNonExistingSchematronProvider() throws NoSchematronProviderFoundException {
        SchematronProvider schematronProvider = SchematronProviderFactory.newInstance().createSchematronProvider("!exists");
        Assert.fail("SchematronProvider should not exist.");
    }

    @Test
    public void testListAllRegisteredSchematronProvider() {
        List<SchematronProvider> schematronProviderList = SchematronProviderFactory.newInstance().getSchematronProviders();
        Assert.assertEquals(schematronProviderList.size(), 1);
        Assert.assertEquals(schematronProviderList.get(0).getId(), "testId");
    }
}
