package com.pagero.schematron;

public class EHFProvider extends SchematronProvider{

    @Override
    public String getSchematronPath() {
        return "schematron/";
    }

    @Override
    public String getSchematronEncoding() {
        return "utf-8";
    }
}
