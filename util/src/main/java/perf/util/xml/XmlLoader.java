package perf.util.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import perf.util.file.FileUtility;
import perf.util.hash.HashFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by wreicher
 * A wrapper for the common xml parsing configuration for loading wildfly / jboss configuration files without trying to load all the namespaces
 */
public class XmlLoader {

    private final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    private final XPathFactory xPathFactory = XPathFactory.newInstance();
    private DocumentBuilder builder;

    public XmlLoader(){
        docFactory.setValidating(false);
        docFactory.setNamespaceAware(true);
        try {
            docFactory.setFeature("http://xml.org/sax/features/namespaces",false);
            docFactory.setFeature("http://xml.org/sax/features/validation",false);
            docFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar",false);
            docFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);

            builder = docFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public String getValue(String pathValue){
        List<String> found = getValues(pathValue);
        if(found.isEmpty()){
            return "";
        }else{
            return found.get(0);
        }
    }

    public static void main(String[] args) {
        String basePath = "/home/wreicher/runtime/wildfly-10.0.0.Final-pool/modules/system/layers/base/org/apache/activemq/artemis/main/";
        String path = basePath+"module.xml"+FileUtility.SEARCH_KEY+"./resources/resource-root[@path]/@path";
        XmlLoader loader = new XmlLoader();
        HashFactory hasher = new HashFactory();
        loader.getValues(path).forEach(s -> {
            File f = new File(basePath+s);
            if(f.exists()) {
                if(f.isDirectory()){}
                else {
                    try {
                        String fileHash = hasher.getInputHash(new FileInputStream(f));
                        System.out.printf("  %-60s -> %s%n",s,fileHash);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public List<String> getValues(String pathValue){
        ArrayList<String> rtrn = new ArrayList<>();
        String searchPath;
        String filePath;
        int searchKeyIndex;
        if( (searchKeyIndex = pathValue.indexOf(FileUtility.SEARCH_KEY)) > -1 ){
            searchPath = pathValue.substring(searchKeyIndex+FileUtility.SEARCH_KEY.length());
            filePath = pathValue.substring(0,searchKeyIndex);
            File f = new File(filePath);
            if(f.exists()){
                Document doc = loadDocument(f.toPath());

                XPath xPath = xPathFactory.newXPath();
                try {
                    NodeList nodeList = (NodeList) xPath.evaluate(searchPath, doc.getDocumentElement(), XPathConstants.NODESET);
                    for(int i=0; i<nodeList.getLength(); i++){
                        Node node = nodeList.item(i);
                        rtrn.add(node.getNodeValue());
                    }
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
            }

        }
        return Collections.unmodifiableList(rtrn);
    }

    public Document loadDocument(String document){
        Document rtrn = null;
        try {
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(document));
            rtrn = builder.parse(is);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rtrn;
    }
    public Document loadDocument(Path path){
        Document rtrn = null;
        try {
            rtrn = builder.parse(path.toFile());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rtrn;
    }
}
