package lt.viko.eif.m.trojanovskis.taksi.Transform;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import org.apache.fop.apps.*;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class PdfTransform {
    public static void main(String[] args){
        try {
            convertToPDF();
        } catch (IOException | JAXBException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method that converts XML file to PDF
     * @throws IOException
     * @throws JAXBException
     */
    public static void convertToPDF() throws IOException, JAXBException {


        File xsltFile = new File("OrdersFop.xsl");
        StreamSource xmlSource = new StreamSource(new File("Orders.xml"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out = new java.io.FileOutputStream("Orders1.pdf");
        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Result res = new SAXResult((fop.getDefaultHandler()));

            transformer.transform(xmlSource, res);

        } catch (FOPException | TransformerException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }

}
