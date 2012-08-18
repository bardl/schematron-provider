package com.pagero.schematron;

import org.xml.sax.SAXException;

import javax.xml.validation.Validator;

public class OIOUBLProvider extends SchematronProvider{

    @Override
    public String getId() {
        return "OIOUBL";
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
        return setupValidator("OIOUBL_CreditNote_Schematron.xsl");
    }

    @Override
    public Validator getCreditNoteValidator() throws SAXException {
        return setupValidator("OIOUBL_Invoice_Schematron.xsl");
    }    
}
