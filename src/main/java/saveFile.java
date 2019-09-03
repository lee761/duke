//import java.io.File;
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class saveFile {
//    saveFile() {
//    }
//
//    void writeFile(List<Task> task) {
//        try {
//            File getFile = new File("D:\\data\\duke.txt");
//            File mainDir = getFile.getParentFile();
//            if (!mainDir.exists()) mainDir.mkdirs();
//            FileWriter writeTxt = new FileWriter(getFile);
//            for (Task t : task) {
//                String in = t.toString();
//                writeTxt.append(in).append(String.valueOf('\n'));
//            }
//            writeTxt.flush();
//            writeTxt.close();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    List<Task> readFile() {
//        List<Task> task = new ArrayList<>();
//        try {
//            File getFile = new File("D:\\data\\duke.txt");
//            File mainDir = getFile.getParentFile();
//            if (!mainDir.exists()) mainDir.mkdirs();
//            Scanner scan = new Scanner(getFile);
//            while (scan.hasNextLine()) {
//                String input = scan.nextLine();
//
//            }
//        }
//    }
//}
