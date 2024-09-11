package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try {
            String filePath = "D:\\bt\\huongsk\\hoadon.xml"; // Đường dẫn tới file trên ổ đĩa C

            // Load tệp XML
            Document doc = HoaDon_XML.loadXmlDocument(filePath);

            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n===== MENU =====");
                System.out.println("1. Xuất toàn bộ file XML");
                System.out.println("2. Xuất danh sách các hóa đơn có số lượng >= 60");
                System.out.println("3. Thêm 1 hóa đơn mới");
                System.out.println("4. Xóa các hóa đơn của công ty Minh Long có số lượng >= 50");
                System.out.println("0. Thoát");
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\n1. Toàn bộ file XML:");
                        HoaDon_XML.printXml(doc);
                        break;
                    case 2:
                        System.out.println("\n2. Danh sách các hóa đơn có số lượng >= 60:");
                        printInvoicesWithQuantityGreaterThan(doc, 60);
                        break;
                    case 3:
                        System.out.println("\n3. Thêm 1 hóa đơn mới:");
                        HoaDon newInvoice = new HoaDon("HD004", "Người Lập", "Công ty ABC", "Mô tả", 70);
                        HoaDon_XML.addInvoice(doc, newInvoice);
                        System.out.println("Hóa đơn mới đã được thêm vào.");
                        break;
                    case 4:
                        System.out.println("\n4. Xóa các hóa đơn của công ty Minh Long có số lượng >= 50:");
                        HoaDon_XML.removeInvoicesByCompanyAndQuantity(doc, "Minh Long", 50);
                        System.out.println("Các hóa đơn của công ty Minh Long có số lượng >= 50 đã được xóa.");
                        break;
                    case 0:
                        System.out.println("Kết thúc chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        break;
                }
            } while (choice != 0);

            // Lưu lại vào file trước khi thoát chương trình
            HoaDon_XML.saveChanges(doc, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để in danh sách các hóa đơn có số lượng >= x
    private static void printInvoicesWithQuantityGreaterThan(Document doc, int quantity) {
        NodeList nodeList = doc.getElementsByTagName("hoadon");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int soLuong = Integer.parseInt(element.getElementsByTagName("soluong").item(0).getTextContent());
                if (soLuong >= quantity) {
                    HoaDon_XML.printElement(element);
                }
            }
        }
    }
}
