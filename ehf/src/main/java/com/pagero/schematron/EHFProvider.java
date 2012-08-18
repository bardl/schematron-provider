package com.pagero.schematron;

import org.xml.sax.SAXException;

import javax.xml.validation.Validator;

public class EHFProvider extends SchematronProvider{

    @Override
    public String getId() {
        return "EHF";
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
        return setupValidator("NORWAY-UBL-T10.xsl");
    }

    @Override
    public Validator getCreditNoteValidator() throws SAXException {
        return setupValidator("NORWAY-UBL-T14.xsl");
    }

    @Override
    public Validator getReminderValidator() throws SAXException {
        return setupValidator("NORWAY-UBL-T17.xsl");
    }
}
