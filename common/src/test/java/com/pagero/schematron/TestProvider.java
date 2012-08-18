package com.pagero.schematron;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;

public class TestProvider extends SchematronProvider {
    
    @Override
    public String getId() {
        return "testId";
    }

    @Override
    public String getSchematronPath() {
        return "schematron/";
    }

    @Override
    public String getSchematronEncoding() {
        return "utf-8";
    }

    @Override
    public Validator getInvoiceValidator() throws SAXException {
        return new TestValidator();
    }

    @Override
    public Validator getCreditNoteValidator() throws SAXException {
        return new TestValidator();
    }

    @Override
    public Validator getReminderValidator() throws SAXException {
        return new TestValidator();
    }

    public class TestValidator extends Validator {
        @Override
        public void reset() {}

        @Override
        public void validate(Source source, Result result) throws SAXException, IOException {}

        @Override
        public void setErrorHandler(ErrorHandler errorHandler) {}

        @Override
        public void setResourceResolver(LSResourceResolver lsResourceResolver) {}

        @Override
        public ErrorHandler getErrorHandler() {
            return null;
        }

        @Override
        public LSResourceResolver getResourceResolver() {
            return null;
        }
    }
}
