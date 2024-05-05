package lt.viko.eif.m.trojanovskis.taksi.Transform;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class xmltohtml {

    public static void main(String[] args) {
        try {
            convertToHtml();
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method that converts XML file to HTML
     * @throws IOException
     * @throws TransformerException
     */
    public static void convertToHtml() throws IOException, TransformerException {

        StreamSource xmlSource = new StreamSource(new File("OrderXMLHtML.xml"));

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new File("OrderXSLHTML.xsl")));

        OutputStream out = new FileOutputStream("OrderHTML.html");
        try {
            Result result = new StreamResult(out);
            transformer.transform(xmlSource, result);
        } finally {
            out.close();
        }
    }
}
