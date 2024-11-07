package org.hl7.fhir.dstu2.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.dstu2.utils.Translations;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TranslationsDocLoaderTest {

    @Test
    public void loadXml() {
        Translations translations = new Translations();
        try {
            translations.load("src/test/resources/doc.xml");
        } catch (java.net.UnknownHostException e) {
            Assert.assertFalse("Affected by loading unknow data: " + e, e.getMessage().contains("my_fake_foobar_site.123"));
        } catch (org.xml.sax.SAXParseException e) {
            Assert.assertTrue(e.getMessage().contains("DOCTYPE is disallowed when the feature \"http://apache.org/xml/features/disallow-doctype-decl\" set to true"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unexpected error when testing: " + e);
        }
    }
}
