//
// Source code recreated from a .class file by IntelliJ IDEA

import javax.swing.SwingUtilities;

public class Starting {
    public Starting() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrmDanhMucSach frm = new FrmDanhMucSach();
                frm.setVisible(true);
            }
        });
    }
}
