package grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class Grep {
    static Bor bor;
    
    public static void main(String[] args) {
       
        bor = new Bor();
        System.out.println("");
        for (int i = 0; i < args.length; i++) {
            bor.addPattern(args[i], i + 1);
        }        

        FileSystem fileSystem = FileSystems.getDefault();
        ArrayList<Path> directories = new ArrayList<>();

        directories.add(new File("C:\\test").toPath());
        for (int i = 0; i < directories.size(); i++) {
            getFiles(directories.get(i));
        }
        if (!bor.anyFound) {
            System.out.println("Nothing was found...");
        }
    }

    public static void getFiles(Path path) {
        try {
            File file = path.toFile();
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                File currentFile = new File(path + "\\" + files[i]);
                if (currentFile.isDirectory()) {
                    getFiles(currentFile.toPath());
                } else if (currentFile.isFile()) {
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(currentFile);
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                                                
                        int numRead, start = 0, l = 1, pos = 1;
                        boolean found = false, newl = false;
                        byte[] bytes = new byte[10];
                        String text, curLine = "";
                        Node cur = bor.root;
                        while ((numRead = fis.read(bytes, start, bytes.length - start)) >= 0) {
                            if (start + numRead < bytes.length) {
                                break;
                            }
                            text = new String(bytes, "UTF-8");
                            text = text.substring(0, text.length() - 1);
                            for (int j = 0; j < text.length(); j++) {
                                char c = text.charAt(j);
                                cur = bor.getGo(cur, c);
                                curLine += c;
                                if (cur.terminal) {
                                    if (cur.charToParent == '\n') {
                                        curLine = "";
                                        found = false;
                                        l++;
                                    } else if (!found) {
                                        System.out.print("[L" + l + "] " + path + "\\" + files[i] + ": ");
                                        System.out.println(curLine + "...");
                                        bor.anyFound = true;
                                        found = true;
                                    }
                                }
                            }
                            pos += text.length();
                            int fact = text.getBytes().length;
                            start = bytes.length - fact;
                            for (int j = 0; j < start; j++) {
                                bytes[j] = bytes[bytes.length - start + j];
                            }
                        }
                        int length = numRead == -1 ? start : start + numRead;
                        byte[] rest = new byte[length];
                        System.arraycopy(bytes, 0, rest, 0, length);
                        text = new String(rest, "UTF-8");
                        for (int j = 0; j < text.length(); j++) {
                            char c = text.charAt(j);
                            cur = bor.getGo(cur, c);
                            if (cur.terminal) {
                                if (cur.charToParent == '\n') {
                                    curLine = "";
                                    found = false;
                                    l++;
                                } else if (!found) {
                                    System.out.print("[L" + l + "] " + path + "\\" + files[i] + ": ");
                                    System.out.println(curLine + "...");
                                    bor.anyFound = true;
                                    found = true;
                                }
                            }
                        }
                        fis.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("FFILENOTFOUNDEXCEPTION");
                    } catch (IOException e) {
                        try {
                            System.out.println("IOEXCEPTION 1");
                            fis.close();
                        } catch (IOException ce) {
                            System.out.println("IOEXCEPTION 2");
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("NULL POINTER EXCEPTION");
        }
    }
}