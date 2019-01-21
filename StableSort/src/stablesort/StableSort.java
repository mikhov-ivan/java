package stablesort;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class StableSort {

    private BufferedReader bufferedReader = null;
    private InputStreamReader inputStreamReader = null;
    private FileInputStream fileInputStream = null;
    private PrintWriter printWriter = null;

    private List<Pair> list = null;

    private class Pair implements Comparable<Pair> {

        private final int index;
        private final String str;

        public Pair(int index, String str) {
            this.index = index;
            this.str = str;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Pair pair) {
            if (index < pair.index) {
                return -1;
            } else if (index > pair.index) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return index + " " + str;
        }
    }

    public static void main(String[] args) {
        new StableSort().init(args);
    }

    private void init(String[] args) {
        printWriter = new PrintWriter(System.out);
        list = new ArrayList<>();

        process(args);

        try {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Can not close input stream");
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                System.err.println("[ERROR] Can not close input stream");
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    System.err.println("[ERROR] Can not close input stream");
                } finally {
                    if (printWriter != null) {
                        printWriter.close();
                    }
                }
            }
        }
    }

    private void process(String[] args) {
        if (args.length == 1 && args[0].equals("-console")) {
            try {
                inputStreamReader = new InputStreamReader(System.in, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (UnsupportedEncodingException e) {
                System.err.println("[ERROR] Unsupported encoding");
                return;
            }
        } else if (args.length == 2 && args[0].equals("-file")) {
            try {
                fileInputStream = new FileInputStream(args[1]);
                inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[1]), "UTF-8"));
            } catch (FileNotFoundException e) {
                System.err.println("Can not find file \"" + args[1] + "\"");
                return;
            } catch (UnsupportedEncodingException e) {
                System.err.println("[ERROR] Unsupported encoding");
                return;
            }
        } else {
            System.err.println("[ERROR] Malformed input. Use \"-console\" or \"-file filename\"");
            return;
        }

        String str;
        do {
            try {
                str = bufferedReader.readLine();
            } catch (IOException e) {
                System.err.println("[ERROR] Can not read the file");
                return;
            }

            if (str != null && !str.equals("exit")) {
                String[] parts = str.split(" ");
                if (parts.length == 0) {
                    System.err.println("[ERROR] Malformed input");
                    return;
                }

                String operation = parts[0];

                if (operation.equals("add") && parts.length == 3) {
                    int index;
                    try {
                        index = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("[ERROR] Incorrect index \"" + parts[1] + "\"");
                        return;
                    }

                    String string = parts[2];
                    add(index, string);
                } else if (operation.equals("remove") && parts.length == 2) {
                    int index;
                    try {
                        index = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("[ERROR] Incorrect index \"" + parts[1] + "\"");
                        return;
                    }

                    remove(index);
                } else if (operation.equals("print") && parts.length == 1) {
                    print();
                } else {
                    System.err.println("[ERROR] Malformed input (unsupported operation)");
                    return;
                }
            }
        } while (str != null && !str.equals("exit"));
    }

    private void add(int index, String str) {
        Pair pair = new Pair(index, str);
        list.add(pair);

        printWriter.println("Added element: \"" + pair + "\"");
        printWriter.flush();
    }

    private void remove(int index) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIndex() == index) {
                list.remove(i);
                count++;
                i--;
            }
        }

        printWriter.println("Removed elements with index " + index + ": " + count);
        printWriter.flush();
    }

    private void print() {
        Collections.sort(list);
        list.stream().forEach((pair) -> {
            printWriter.println(" " + pair);
        });
        printWriter.flush();
    }
}
