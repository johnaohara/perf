package perf.util.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import perf.util.file.FileUtility;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * Created by wreicher
 * A convenience wrapper around XML that encapsulates some of the complexity in dealing with XML
 * Included features are
 *   Xpath searching
 *   pretty printing
 *   adding xml from strings
 *   modifying xml with operations from FileUtility
 *     see perf.util.xml.Xml#modify(java.lang.String)
 */
public class Xml {

    public static final String ATTRIBUTE_VALUE_KEY = "=";
    public static final String ATTRIBUTE_KEY = "@";
    public static final String TAG_START = "<";
    public static final String TAG_END = ">";

    public static final Xml EMPTY = new Xml(null);

    private static final String XPATH_DELIM = "/";

    private final XmlLoader xmlLoader = new XmlLoader();
    private final XPathFactory xPathFactory = XPathFactory.newInstance();
    private final Node node;

    protected Xml(Node node){
        this.node = node;
    }
    public boolean isEmpty(){return node==null;}

    public Xml get(String search){
        List<Xml> list = getAll(search);
        return list.isEmpty() ? EMPTY : list.get(0);
    }
    public List<Xml> getAll(String search){
        if(isEmpty()){
            return Collections.emptyList();
        }
        ArrayList<Xml> rtrn = new ArrayList<>();
        XPath xPath = xPathFactory.newXPath();
        try {
            NodeList nodeList = (NodeList) xPath.evaluate(search, node, XPathConstants.NODESET);
            for(int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                rtrn.add(new Xml(node));
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return rtrn;
    }
    public void set(String key,String value){
        if(isEmpty()){
            return;
        }
        if(key.startsWith(ATTRIBUTE_KEY)){
            String attributeName = key.substring(ATTRIBUTE_KEY.length());
            ((Element)node).setAttribute(attributeName,value);
        }else{

        }
    }

    public void trimEmptyText(){
        if(isEmpty()){
            return;
        }
        XPathExpression xpathExp = null;
        try {
            xpathExp = xPathFactory.newXPath().compile(
                    "//text()[normalize-space(.) = '']");
            NodeList emptyTextNodes = (NodeList)
                    xpathExp.evaluate(node, XPathConstants.NODESET);
            // Remove each empty text node from document.
            for (int i = 0; i < emptyTextNodes.getLength(); i++) {
                Node emptyTextNode = emptyTextNodes.item(i);
                emptyTextNode.getParentNode().removeChild(emptyTextNode);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
    public List<Xml> getChildren(){
        if(isEmpty()){
            return Collections.emptyList();
        }
        List<Xml> rtrn = new LinkedList<>();
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++){
            rtrn.add(new Xml(children.item(i)));
        }
        return rtrn;
    }
    protected void clearChildren(){
        if(isEmpty()){
            return;
        }
        while (node.hasChildNodes()) {
            node.removeChild(node.getFirstChild());
        }
    }

    public void setChild(String value){
        if(isEmpty()){
            return;
        }
        clearChildren();
        addChild(value);
    }
    private void addChild(String value){



        value = "<cld>"+value+"</cld>";
        List<Xml> xmls = xmlLoader.loadXml(value).getChildren();
        for(Xml xml : xmls){
            addChild(xml);
        }
    }
    private void addChild(Xml value){
        Node toImport = node.getOwnerDocument().importNode(value.node, true);
        ((Element)node).appendChild(toImport);
    }
    /**
     * add value to this. Will treat "<...>" as xml
     */
    public void add(String value){

        if(isEmpty()){
            return;
        }
        switch(node.getNodeType()){
            case Node.ATTRIBUTE_NODE:
                node.setNodeValue(node.getNodeValue()+value);
                break;
            case Node.ELEMENT_NODE:

                if(value.startsWith(TAG_START) && value.endsWith(TAG_END)){
                    addChild(value);
                }else {

                    node.setTextContent(node.getTextContent() + value);
                }
                break;
            default:
                System.out.println("add("+value+") "+node.getNodeType());
        }
    }
    /**
     * set the value of this. Will treat "<...>" as xml
     */
    public void set(String value){
        if(isEmpty()){
            return;
        }
        switch(node.getNodeType()){
            case Node.ATTRIBUTE_NODE:
                node.setNodeValue(value);
                break;
            case Node.ELEMENT_NODE:
                if(value.startsWith(TAG_START) && value.endsWith(TAG_END)){
                    setChild(value);
                }else {
                    node.setTextContent(value);
                }
                break;
            default:
                System.out.println("set("+value+") "+node.getNodeType());
        }
    }

    /**
     * Performs a modification to the associated Xml element. support sytanx:
     * value                 : sets the attribute value or text value depending if this represents an attribute or element
     * <value></value>...    : set the attribute value or the children of this (deletes existing values)
     * --                    : delete this from the parent (if possible)
     * ++ value              : add the value to the attribute value or text value depending if this represents an attribute or element
     * ++ <value></value>... : add the value to the attribute value or add to the children elements
     * ++ @key=value         : add the attribute with value to this (will replace any existing value)
     * == value              : sets the value same as [value]
     * == <value></value>... : sets the value same as [<value></value>]
     *
     */
    public void modify(String value){
        if(isEmpty()){
            return;
        }
        int opIndex = -1;
        switch(node.getNodeType()){
            case Node.ATTRIBUTE_NODE:
                if(value.startsWith(FileUtility.DELETE_OPERATION)){//--
                    delete();
                }else if (value.startsWith(FileUtility.ADD_OPERATION)){//++ value
                    String newValue = removeQuotes(value.substring(FileUtility.OPERATION_LENGTH).trim());
                    node.setNodeValue(node.getNodeValue()+newValue);

                }else if (value.startsWith(FileUtility.SET_OPERATION)){//== value
                    node.setNodeValue( removeQuotes( value.substring(FileUtility.OPERATION_LENGTH).trim() ) );

                }else{ //value
                    node.setNodeValue( removeQuotes( value ) );
                }
                break;
            case Node.ELEMENT_NODE:

                if(value.startsWith(FileUtility.DELETE_OPERATION)) { //--
                    delete();
                }else if(value.startsWith(FileUtility.ADD_OPERATION)) { //++ ?
                    String toAdd = value.substring(FileUtility.ADD_OPERATION.length()).trim();

                    if (toAdd.startsWith(ATTRIBUTE_KEY)) { //++ @key=value
                        int valueIndex = toAdd.indexOf(ATTRIBUTE_VALUE_KEY);
                        String attributeKey = toAdd.substring(ATTRIBUTE_KEY.length(), valueIndex);
                        String attributeValue = toAdd.substring(valueIndex + ATTRIBUTE_VALUE_KEY.length());
                        Element elm = (Element) node;
                        elm.setAttribute(attributeKey, removeQuotes(attributeValue));
                    } else {//++ value or //++ <value/><value/>... handled by add
                        add(removeQuotes(toAdd));
                    }
                }else if (value.startsWith(FileUtility.SET_OPERATION)){
                    String toSet = value.substring(FileUtility.SET_OPERATION.length()).trim();
                    set(removeQuotes(toSet)); // supports value and <value/>...
                }else{// value or <value/>...
                    set(removeQuotes(value));
                }
                break;
            default:
                System.out.println("set("+value+") "+node.getNodeType());
        }
    }
    public void delete(){
        if(node ==null){
            return;
        }
        switch (node.getNodeType()){
            case Node.ATTRIBUTE_NODE:
                Element attrParent =((Attr)node).getOwnerElement();
                attrParent.removeAttributeNode(((Attr)node));
                break;
            case Node.ELEMENT_NODE:
                Node nodeParent = node.getParentNode();
                nodeParent.removeChild(node);
                break;
            default:
                System.out.println("unknown type="+node.getNodeType());
        }

    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "";
        }
        switch(node.getNodeType()){
            case Node.ATTRIBUTE_NODE:
                return node.getNodeValue();
            default:
                return node.getNodeValue();
        }
    }
    public String getValue(){return isEmpty()? "" : node.getNodeValue();}
    public String documentString(){
        return documentString(4);
    }
    public String documentString(int indent){
        trimEmptyText();
        Transformer transformer = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(indent));
            DOMSource source = new DOMSource(node);
            StreamResult result = new StreamResult(baos);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return new String(baos.toByteArray());
    }
    private String removeQuotes(String value){
        String rtrn = value;
        if( (value.startsWith("\"")&& value.endsWith("\"")) || (value.startsWith("\'") && value.endsWith("\'"))) {
            rtrn =value.substring(1,value.length()-1);
        }
        return rtrn;
    }


    public static void main(String[] args) {
        String fooxml = "<foo name=\"foo\"><bar name=\"bar\"><biz name=\"biz\"/></bar><bar name=\"bar.bar\"/></foo>";
        Xml loaded = new XmlLoader().loadXml(fooxml);
        System.out.println("initial");
        System.out.println(loaded.documentString());

//        String modify = "++ @key=test";
//        loaded.get("/foo/bar[@name=\"bar\"]").modify(modify);
//
//        System.out.println("\n"+modify);
//        System.out.println(loaded.documentString());

        String setBlurp = "blurp";
        loaded.get("/foo/bar[@name=\"bar\"]").modify(setBlurp);

        System.out.println("\n"+setBlurp);
        System.out.println(loaded.documentString());

        String setFoo = "++ <foo/><foo/><foooo>:)</foooo>";
        loaded.get("/foo/bar[@name=\"bar\"]").modify(setFoo);

        System.out.println("\n"+setFoo);
        System.out.println(loaded.documentString());


    }
}
