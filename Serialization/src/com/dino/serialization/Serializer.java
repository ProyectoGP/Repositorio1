/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dino.serialization;
/*
 * mysql> CREATE TABLE java_objects ( 
 * id INT AUTO_INCREMENT, 
 * name varchar(128), 
 * object_value BLOB, 
 * primary key (id));
 **/

import com.dino.object.Empleado;
import com.dino.object.Usuario;
import com.dino.object.database.connection.MySQLConnection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Serializer {

//    private static final String WRITE_OBJECT_SQL = "INSERT INTO java_objects(name, object_value) VALUES (?, ?)";
//    private static final String READ_OBJECT_SQL = "SELECT object_value FROM java_objects WHERE id = ?";
//    private Connection mySqlConnection = null;

    private File file = null;

    public Serializer() throws ClassNotFoundException, SQLException, IOException {
//        mySqlConnection = MySQLConnection.getConnection();
        file = new File("file/object/objects.bin");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

//    public long writeObjectToDB(Object object,Empleado empleado) throws SQLException {
//        String className = object.getClass().getName();
//        Empleado empl=new Empleado();
//        empl=(Empleado) object;
//        int id;
//        // set input parameters
//        try (PreparedStatement pstmt = mySqlConnection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS)) {
//            // set input parameters
//            pstmt.setString(1, empl.getUsuario().getNombres());
//            pstmt.setObject(2, object);
//            pstmt.executeUpdate();
//            mySqlConnection.commit();
//            try ( // get the generated key for the id
//                    ResultSet rs = pstmt.getGeneratedKeys()) {
//                id = -1;
//                if (rs.next()) {
//                    id = rs.getInt(1);
//                }
//            }
//        }
//        //System.out.println("writeObjectToDB: done serializing: " + className);
//        return id;
//    }
//
//    public Object readObjectFromDB(long id) throws SQLException, IOException, ClassNotFoundException {
//        PreparedStatement pstmt = mySqlConnection.prepareStatement(READ_OBJECT_SQL);
//        pstmt.setLong(1, id);
//        ResultSet rs = pstmt.executeQuery();
//        rs.next();
//
//        byte[] buf = rs.getBytes("object_value");
//        ObjectInputStream objectIn = null;
//        if (buf != null) {
//            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
//        }
//        Object object = objectIn.readObject();
//        String className = object.getClass().getName();
//        rs.close();
//        pstmt.close();
////        System.out.println("Deserialization Successful."
////                + "\nDeserialized Class: " + className);
//        return object;
//    }
//
    public Integer writeObjectToFile(Object object) throws FileNotFoundException, IOException, ClassNotFoundException {

        //Hashtable<Integer, Object> hs = null;
        Integer key = 1;

        //       File f = new File("file/object/object.bin");
        HashMap<Integer, Object> hs = (HashMap<Integer, Object>) readHashMapFromFile();
        if (hs != null) {

            key = hs.size() + 1;
            //System.out.println("no null " + key);
            if (hs.get(key) != null) {
                do {
                    key++;
                } while (hs.get(key) != null);
            }
        } else {
            hs = new HashMap<>();
        }

        hs.put(key, object);

//        Iterator it = hs.entrySet().iterator();
//        while (it.hasNext()) {
//            Entry pairs = it.next();
//            System.out.println(pairs.getKey() + " = " + pairs.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
//        }

        for (Map.Entry<Integer, Object> entry : hs.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue() + "\n\n");
        }

        FileOutputStream fout = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(hs);

        oos.close();

        return key;
    }

    private Object readHashMapFromFile() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(file);
        Object object = null;
        try {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                object = ois.readObject();
            }
        } catch (EOFException ex) {
            //When file is empty
            Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return object;
    }

    public Object readObjectFromFile(Integer key) throws IOException, ClassNotFoundException {

//        FileInputStream streamIn = new FileInputStream(file);
//        ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
//        Object object = objectinputstream.readObject();
//        recordList.add(readCase);
//        System.out.println(recordList.get(i));
        HashMap hs = (HashMap<Integer, Object>) readHashMapFromFile();

//        if (objectinputstream != null) {
//            objectinputstream.close();
//        }
        return hs.get(key);

    }

//    public byte[] writeObjectToMemory(Object object) throws FileNotFoundException, IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
//        try (ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut)) {
//            objectOut.writeObject(object);
//        }
//        return baos.toByteArray();
//    }
//
//    public Object readObjectFromMemory(byte[] bytes) throws IOException, ClassNotFoundException {
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//        GZIPInputStream gzipIn = new GZIPInputStream(bais);
//        Object object;
//        try (ObjectInputStream objectIn = new ObjectInputStream(gzipIn)) {
//            object = objectIn.readObject();
//        }
//
//        return object;
//    }

    public static void main(String args[]) {

        try {
            Serializer smsql = new Serializer();

            Empleado emp = new Empleado("SALES", 1222.22f);
            Usuario user = new Usuario();
            user.setNombres("Dino Alberto");
            user.setApellidos("Bravo Muñoz");
            user.setQuota(10000);
            user.setUsername("dabravo");
            user.setPassword("1234");
            emp.setUsuario(user);

            System.out.println("Empleado:\n" + emp);

//            long objectID = smsql.writeObjectToDB(emp,emp);
////            System.out.println("Serialized objectID => " + objectID);
//            Empleado userFromDatabase = (Empleado) smsql.readObjectFromDB(objectID);

            //System.out.println("Empleado desde base de datos:\n" + userFromDatabase);
            Integer key = smsql.writeObjectToFile(emp);
            Empleado userFromFile = (Empleado) smsql.readObjectFromFile(key);
            System.out.println("Empleado desde archivo:\n" + userFromFile);

//            byte[] userInBytes = smsql.writeObjectToMemory(emp);
//            Empleado userFromMemory = (Empleado) smsql.readObjectFromMemory(userInBytes);
//            System.out.println("Empleado desde memoria:\n" + userFromMemory);
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
