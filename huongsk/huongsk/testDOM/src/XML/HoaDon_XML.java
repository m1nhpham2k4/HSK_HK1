package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class HoaDon_XML {
    public static Document loadXmlDocument(String filePath) throws Exception {
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(file);
    }

    public static void printElement(Element element) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(node.getNodeName() + ": " + node.getTextContent());
            }
        }
    }

    public static void printXml(Document doc) {
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                printElement((Element) node);
            }
        }
    }

    public static void addInvoice(Document doc, HoaDon hoaDon) {
        Element root = doc.getDocumentElement();
        Element newInvoice = doc.createElement("hoadon");

        Element maHDNode = doc.createElement("mahoadon");
        maHDNode.appendChild(doc.createTextNode(hoaDon.getHoaDonID()));
        newInvoice.appendChild(maHDNode);

        Element tenCongTyNode = doc.createElement("tencongty");
        tenCongTyNode.appendChild(doc.createTextNode(hoaDon.getCongTy()));
        newInvoice.appendChild(tenCongTyNode);

        Element soLuongNode = doc.createElement("soluong");
        soLuongNode.appendChild(doc.createTextNode(String.valueOf(hoaDon.getSoLuong())));
        newInvoice.appendChild(soLuongNode);

        root.appendChild(newInvoice);
    }

    public static void removeInvoicesByCompanyAndQuantity(Document doc, String tenCongTy, int quantity) {
        NodeList nodeList = doc.getElementsByTagName("hoadon");
        for (int i = nodeList.getLength() - 1; i >= 0; i--) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String congTy = element.getElementsByTagName("congty").item(0).getTextContent();
                int soLuong = Integer.parseInt(element.getElementsByTagName("soluong").item(0).getTextContent());
                if (congTy.equals(tenCongTy) && soLuong >= quantity) {
                    element.getParentNode().removeChild(element);
                }
            }
        }
    }

    public static void saveChanges(Document doc, String filePath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
        System.out.println("Lưu các thay đổi thành công!");
    }
}
