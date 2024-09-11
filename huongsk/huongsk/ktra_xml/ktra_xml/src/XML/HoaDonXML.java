package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class HoaDonXML {
    private static String filename = "D:\\bt\\huongsk\\hoadon.xml";
    private DocumentBuilderFactory builderFactory;
    private DocumentBuilder builder;
    private static Document document;

    public HoaDonXML() {
        builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = builderFactory.newDocumentBuilder();
            document = builder.parse(filename);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addHoaDon(HoaDon hd)  {


        Element rootCheck = document.getDocumentElement();
        NodeList pList = rootCheck.getElementsByTagName("hoadon");
        for (int i = 0;i<pList.getLength();i++) {
            Element pNodeCheck = (Element) pList.item(i);
            String hoaDonId = pNodeCheck.getAttribute("id");
            if(hd.getHoaDonID().equalsIgnoreCase(hoaDonId)) return false;
        }
        Node nodeName;
        Element root;
        Element pNode;
        root = document.getDocumentElement();
        pNode = document.createElement("hoadon");
        root.appendChild(pNode);
        pNode.setAttribute("id",hd.getHoaDonID());
        nodeName = document.createElement("nguoilap");
        pNode.appendChild(nodeName);
        nodeName.setTextContent(hd.getNguoiLap());
        nodeName = document.createElement("congty");
        pNode.appendChild(nodeName);
        nodeName.setTextContent(hd.getCongTy());
        nodeName = document.createElement("mota");
        pNode.appendChild(nodeName);
        nodeName.setTextContent(hd.getMoTa());
        nodeName = document.createElement("soluong");
        pNode.appendChild(nodeName);
        nodeName.setTextContent(Integer.toString(hd.getSoLuong()));

        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void deleteHoaDon()  {

        Element rootCheck = document.getDocumentElement();
        NodeList pList = rootCheck.getElementsByTagName("hoadon");
        for (int i = 0;i<pList.getLength();i++) {
            Element pNodeCheck = (Element) pList.item(i);
            String congty = pNodeCheck.getElementsByTagName("congty").item(0).getTextContent();
            int soLuong = Integer.parseInt(pNodeCheck.getElementsByTagName("soluong").item(0).getTextContent());
            if(congty.equalsIgnoreCase("Minh Long") && soLuong>= 50) {
                rootCheck.removeChild(pNodeCheck);
                break;
            }
        }

        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void printHoaDon() {
        TransformerFactory transformerFactory = null;
        Transformer transformer = null;

        transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(new DOMSource(document),new StreamResult(System.out));
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printHoaDonOver60() {
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("hoadon");

            System.out.println("Danh sách các hóa đơn có số lượng >=60:");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int soluong = Integer.parseInt(element.getElementsByTagName("soluong").item(0).getTextContent());
                    if (soluong >= 60) {
                        System.out.println("ID: " + element.getAttribute("id"));
                        System.out.println("Nguoilap: " + element.getElementsByTagName("nguoilap").item(0).getTextContent());
                        System.out.println("Congty: " + element.getElementsByTagName("congty").item(0).getTextContent());
                        System.out.println("Mota: " + element.getElementsByTagName("mota").item(0).getTextContent());
                        System.out.println("Soluong: " + soluong);
                        System.out.println("--------------------");
                    }
                }
            }

    }



}
