import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileDocGhi {
    public  void writeToFile(QuanLi ql, String file) throws Exception{
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(ql);
        out.close();
    }
    public Object readFromFile(String file)throws Exception{
        ObjectInputStream ois=  new ObjectInputStream(new FileInputStream(file));
        Object ql= ois.readObject();
//        ois.close();
        return ql;
    }
}
