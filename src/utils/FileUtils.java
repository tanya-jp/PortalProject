package utils;

import java.io.*;

public class FileUtils {

    public static File[] getFilesInDirectory(String path) {
        return new File(path).listFiles();
    }
    public static void fileWriter(String content,String p) {
        String fileName = getProperFileName(content);
        File newFile = new File(p+fileName+".txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(content);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    public static String fileReader(File file) {
        String string = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            int cnt;
            char[] buffer = new char[2048];
            while (reader.ready()) {
                cnt = reader.read(buffer);
                string += new String(buffer, 0, cnt);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
        return string;
    }

    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
}
