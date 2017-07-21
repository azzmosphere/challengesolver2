package au.azzmosphere.xmlcs2configmgr;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class XMLCs2Builder {
    private final Logger logger = LoggerFactory.getLogger(XMLCs2Builder.class);
    private String xmlConfigSchema, xmlConfigFile;

    public XMLCs2Builder(String xmlConfigFile, String xmlConfigSchema) {
        this.xmlConfigFile = xmlConfigFile;
        this.xmlConfigSchema = xmlConfigSchema;
    }

    public void build() throws Exception {
        if (xmlConfigSchema == null || xmlConfigFile == null) {
            throw new FileNotFoundException("xmlConfigSchema and xmlConfigFile must be defined");
        }
        logger.debug("setting the XML config path to " + xmlConfigFile);
        readXMLProperties();
    }

    //TODO: Return back the categories so that they can be mapped.
    private void readXMLProperties() throws Exception {
        logger.debug("config services started");
        SchemaFactory factory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xmlConfigSchema));

//        JAXBContext jc = JAXBContext.newInstance(ChallengeConfigRoot.class);
//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        unmarshaller.setSchema(schema);
        File xml = new File(xmlConfigFile);
//        challenges = (ChallengeConfigRoot) unmarshaller.unmarshal(xml);
    }
}
