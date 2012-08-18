package com.pagero.schematron;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.validation.Validator;

import static com.pagero.schematron.SchematronProviderFactory.newInstance;

public class OIOUBLProviderTest {
    private final String providerId = "OIOUBL";

    @Test
    public void testId() throws NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider(providerId);
        Assert.assertEquals(provider.getId(), providerId);
    }

    @Test
    public void testSchematronPath() throws NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider(providerId);
        Assert.assertEquals(provider.getSchematronPath(), "schematron/");
    }

    @Test
    public void testSchematronEncoding() throws NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider(providerId);
        Assert.assertEquals(provider.getSchematronEncoding(), "utf-8");
    }

    @Test
    public void getInvoiceValidator() throws SAXException, NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider(providerId);
        assertValidatorExistsAndNotNullValidator(provider.getInvoiceValidator());
    }

    @Test
    public void getCreditNoteValidator() throws SAXException, NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider(providerId);
        assertValidatorExistsAndNotNullValidator(provider.getCreditNoteValidator());
    }

    @Test
    public void getReminderValidator() throws SAXException, NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider(providerId);
        Validator validator = provider.getReminderValidator();
        Assert.assertNotNull(validator);
        Assert.assertEquals(validator.getClass(), NullValidator.class);
    }

    private void assertValidatorExistsAndNotNullValidator(Validator validator) {
        Assert.assertNotNull(validator);
        Assert.assertNotSame(validator.getClass(), NullValidator.class);
    }
}
