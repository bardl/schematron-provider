package com.pagero.schematron;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.validation.Validator;

import static com.pagero.schematron.SchematronProviderFactory.*;

public class EHFProviderTest {

    @Test
    public void testId() throws NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider("EHF");
        Assert.assertEquals(provider.getId(), "EHF");
    }

    @Test
    public void testSchematronPath() throws NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider("EHF");
        Assert.assertEquals(provider.getSchematronPath(), "schematron/");
    }

    @Test
    public void testSchematronEncoding() throws NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider("EHF");
        Assert.assertEquals(provider.getSchematronEncoding(), "utf-8");
    }

    @Test
    public void getInvoiceValidator() throws SAXException, NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider("EHF");
        assertValidatorExistsAndNotNullValidator(provider.getInvoiceValidator());
    }

    @Test
    public void getCreditNoteValidator() throws SAXException, NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider("EHF");
        assertValidatorExistsAndNotNullValidator(provider.getCreditNoteValidator());
    }

    @Test
    public void getReminderValidator() throws SAXException, NoSchematronProviderFoundException {
        SchematronProvider provider = newInstance().createSchematronProvider("EHF");
        assertValidatorExistsAndNotNullValidator(provider.getReminderValidator());
    }

    private void assertValidatorExistsAndNotNullValidator(Validator validator) {
        Assert.assertNotNull(validator);
        Assert.assertNotSame(validator.getClass(), NullValidator.class);
    }
}
