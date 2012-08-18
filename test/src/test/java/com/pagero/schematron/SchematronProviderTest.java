package com.pagero.schematron;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.validation.Validator;
import java.util.List;

public class SchematronProviderTest {

    @Test
    public void testListAllProviders() {
        List<SchematronProvider> list = SchematronProviderFactory.newInstance().getSchematronProviders();
        Assert.assertEquals(list.size(), 2);
    }
}
