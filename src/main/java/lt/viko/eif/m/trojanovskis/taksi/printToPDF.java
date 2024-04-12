package lt.viko.eif.m.trojanovskis.taksi;

import org.apache.fop.apps.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class printToPDF {
    public static void convertToPDF() throws IOException {
        File xsltFile = new File("OrdersFop.xsl");
        StreamSource xmlSource = new StreamSource(new File("Orders.xml"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out = new java.io.FileOutputStream("Orders1");
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
