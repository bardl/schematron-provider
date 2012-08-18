package com.pagero.schematron;

import com.sun.org.apache.xerces.internal.dom.DOMInputImpl;
import org.apache.log4j.Logger;
import org.schematron.validation.SchematronErrorHandler;
import org.schematron.validation.SchematronSchemaFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class SchematronProvider {

    static Logger LOG = Logger.getLogger(SchematronProvider.class);

    public abstract String getId();

    public abstract String getSchematronPath();

    public abstract String getSchematronEncoding();

    public Validator setupValidator(String mainSchemaName) throws SAXException {
        final String schematronPath = getSchematronPath();

        SchematronErrorHandler errorHandler = new SchematronErrorHandler();

        SchematronSchemaFactory factory = new SchematronSchemaFactory();
        factory.setCompileSchematron(false);
        factory.setErrorHandler(errorHandler);        
        factory.setResourceResolver(new LSResourceResolver() {

            public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(schematronPath + systemId);
                return new DOMInputImpl(publicId, systemId, baseURI, inputStream, getSchematronEncoding());
            }
        });
        try {
            String message = "Loading schematron validator resource [" + getClass().getClassLoader().getResource("").getPath() + schematronPath + mainSchemaName + "].";
            FileOutputStream fos = new FileOutputStream("c:\\fff.txt");
            fos.write(message.getBytes());
            fos.close();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            try {
                FileOutputStream fos = new FileOutputStream("c:\\fff.txt");
                fos.write(sw.toString().getBytes());
                fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        System.out.println("Loading schematron validator resource [" + getClass().getClassLoader().getResource("").getPath() + schematronPath + mainSchemaName + "].");
        InputStream mainSchema = getClass().getClassLoader().getResourceAsStream(schematronPath + mainSchemaName);
        return factory.newSchema(new StreamSource(mainSchema)).newValidator();
    }

    public Validator getInvoiceValidator() throws SAXException {
        return new NullValidator();
    }

    public Validator getCreditNoteValidator() throws SAXException {
        return new NullValidator();
    }
    
    public Validator getReminderValidator() throws SAXException {
        return new NullValidator();
    }
}
