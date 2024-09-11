package qlsach;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LuuTru {

    public boolean LuuFile(Object obj, String filePath) throws Exception{
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(obj);
        oos.close();
        return true;
    }
    public Object DocFile(String filePath) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
