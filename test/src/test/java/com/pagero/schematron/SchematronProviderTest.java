package com.pagero.schematron;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.validation.Validator;

public class SchematronProviderTest {

    @Test
    public void testListAllProviders() {
        SchematronProvider.writeProviders();
    }

    @Test
    public void initiateEHFProvider() throws SAXException {
        SchematronProvider provider = new EHFProvider();
        Validator validator = provider.setupValidator();
    }
}
