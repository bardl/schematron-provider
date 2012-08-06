package com.pagero.schematron;

import com.sun.org.apache.xerces.internal.dom.DOMInputImpl;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.SAXException;
import se.pagero.schematron.validation.SchematronErrorHandler;
import se.pagero.schematron.validation.SchematronSchemaFactory;

import java.io.InputStream;
import java.util.ServiceLoader;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public abstract class SchematronProvider {

    public static void writeProviders() {
        ServiceLoader<SchematronProvider> ldr = ServiceLoader.load(SchematronProvider.class);
        for (SchematronProvider provider : ldr) {
            System.out.println(provider.getClass().getSimpleName());
        }
        throw new Error ("No SchematronProviders registered");
    }

    public Validator setupValidator() throws SAXException {
        final String schematronPath = getSchematronPath();

        SchematronErrorHandler errorHandler = new SchematronErrorHandler();

        SchemaFactory factory = new SchematronSchemaFactory();
        factory.setErrorHandler(errorHandler);
        factory.setResourceResolver(new LSResourceResolver() {

            public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(schematronPath + systemId);
                return new DOMInputImpl(publicId, systemId, baseURI, inputStream, getSchematronEncoding());
            }
        });

        InputStream mainSchema = getClass().getClassLoader().getResourceAsStream(schematronPath + "NORWAY-UBL-T10.sch");
        return factory.newSchema(new StreamSource(mainSchema)).newValidator();
    }

    public abstract String getSchematronPath();

    public abstract String getSchematronEncoding();
}
