package aqbitig.lib.io;

import aqbitig.lib.basic.T;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class FileManager {

    public void save(Model o) {
        FileOutputStream f = null;
        try {
            // Serialize today's date to a file.
            f = new FileOutputStream("data.jo");
            ObjectOutput s = new ObjectOutputStream(f);
            s.writeObject(o);
            s.flush();
        } catch (FileNotFoundException ex) {
            T.o("fehler 1");
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            T.o("fehler 2");
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void load() {
        FileInputStream in = null;
        try {
            // Deserialize a string and date from a file.
            in = new FileInputStream("data.jo"); // java object = jo
            ObjectInputStream s = new ObjectInputStream(in);
            Model m;
            while (null != (m = (Model) s.readObject())) {
                T.o(m.data);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void backup(String fileName) {
        try {
            if (!new File("backup_1." + fileName).exists()
                    || !FileManager.isFileBinaryEqual(new File(fileName), new File("backup_1." + fileName))) {
                T.o("Files not equal");

                // BACKUP DB
                for (int i = 4; i > 0; i--) {
                    String oldF = "backup_" + i + "." + fileName;
                    String newF = "backup_" + (i + 1) + "." + fileName;
                    File f = new File(oldF);
                    if (f.exists()) {
                        Files.copy(Paths.get(oldF), Paths.get(newF), REPLACE_EXISTING);
                    }
                }

                Path source = Paths.get(fileName);
                Path target = Paths.get("backup_1." + fileName);
                Files.copy(source, target, REPLACE_EXISTING);
            } else {
                T.o("Files equal");
            }
        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }

    public static boolean isFileBinaryEqual(File first, File second) throws IOException {
        boolean retval = false;

        if ((first.exists()) && (second.exists())
                && (first.isFile()) && (second.isFile())) {
            if (first.getCanonicalPath().equals(second.getCanonicalPath())) {
                retval = true;
            } else {
                FileInputStream firstInput = null;
                FileInputStream secondInput = null;
                BufferedInputStream bufFirstInput = null;
                BufferedInputStream bufSecondInput = null;

                try {
                    firstInput = new FileInputStream(first);
                    secondInput = new FileInputStream(second);
                    bufFirstInput = new BufferedInputStream(firstInput, 65536);
                    bufSecondInput = new BufferedInputStream(secondInput, 65536);

                    int firstByte;
                    int secondByte;

                    while (true) {
                        firstByte = bufFirstInput.read();
                        secondByte = bufSecondInput.read();
                        if (firstByte != secondByte) {
                            break;
                        }
                        if ((firstByte < 0) && (secondByte < 0)) {
                            retval = true;
                            break;
                        }
                    }
                } finally {
                    try {
                        if (bufFirstInput != null) {
                            bufFirstInput.close();
                        }
                    } finally {
                        if (bufSecondInput != null) {
                            bufSecondInput.close();
                        }
                    }
                }
            }
        }

        return retval;
    }

}
