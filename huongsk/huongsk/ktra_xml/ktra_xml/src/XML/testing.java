package XML;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

public class testing {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        int luaChon;
        HoaDonXML dom = new HoaDonXML();
        boolean flag = true;
        do {
            createMenu();
            luaChon = sc.nextInt();
            switch (luaChon) {
                case 1: {
                   dom.printHoaDon();
                   break;
                }
                case 2 : {
                    dom.printHoaDonOver60();
                    break;
                }
                case 3 : {
                    sc.nextLine();
                   HoaDon  hd =  createHoaDon();

                   if(dom.addHoaDon(hd)){
                       System.out.println("Thêm thành công");
                   }else System.out.println("Thêm thất bại vì mã trùng");
                   break;
                }
                case 4: {
                    dom.deleteHoaDon();
                    break;
                }

                default: {
                    flag = false;
                }
            }
        }while (flag);
    }

    private static HoaDon createHoaDon() {
        System.out.println("Nhập id:");
        String id = sc.nextLine();
        System.out.println("Nhập người lập: ");
        String nguoi = sc.nextLine();
        System.out.println("Nhập công ty: ");
        String congty = sc.nextLine();
        System.out.println("Nhập mô tả: ");
        String mota = sc.nextLine();
        System.out.println("Nhập số lượng: ");
        int soLuong = sc.nextInt();
        return new HoaDon(id,nguoi,congty,mota,soLuong);
    }

    private static void createMenu() {
        System.out.println("==========Menu========");
        System.out.println("1. Xuất toàn bộ file XML");
        System.out.println("2. Xuất danh sách các hóa đơn có số luượng >=60");
        System.out.println("3. Thêm 1 hóa đơn vào file XML");
        System.out.println("4. Xóa các hóa đơn của công ty Minh Long có số lượng >=50");
        System.out.println("0. Thoát");
        System.out.println("Mời lựa chon: ");
    }
}
