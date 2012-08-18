package com.pagero.schematron;

import org.schematron.validation.SchematronValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.validation.Validator;
import java.lang.reflect.Field;


public class SchematronProviderTest {

    @Test
    public void testSetupValidator() throws NoSchematronProviderFoundException, SAXException, NoSuchFieldException, IllegalAccessException {
        SchematronProviderFactory factory = SchematronProviderFactory.newInstance();
        SchematronProvider provider = factory.createSchematronProvider("testId");
        SchematronValidator validator = (SchematronValidator)provider.setupValidator("TestProvider_Schematron.xsl");
        Assert.assertNotNull(validator);
    }

}
