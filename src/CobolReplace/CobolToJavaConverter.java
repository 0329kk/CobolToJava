package CobolReplace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CobolToJavaConverter {

    public static void main(String[] args) {
    	String filePath = "/Users/k.k/cobol/OTAMESHI.cbl"; 
//        String filePath = "/Users/k.k/cobol/OTAMESHI.cbl"; // COBOLプログラムのファイルパス
        String cobolCode = readCobolProgram(filePath);
        String packageName = extractFileNameWithoutExtension(filePath);
        String javaCode = convertCobolToJava(cobolCode, packageName);
        String srcDirectoryPath = "src" + File.separator + packageName; // クロスプラットフォーム対応
        new File(srcDirectoryPath).mkdirs();
//        String srcDirectoryPath = "src/" + packageName;
//        new File(srcDirectoryPath).mkdirs();
        

        // 出力するファイルのパスを指定
        String outputFilePath = srcDirectoryPath + File.separator + "Otameshi.java"; // クロスプラットフォーム対応
        writeToFile(javaCode, outputFilePath);
//        String fileName = "/Applications/Eclipse_2022-06.app/Contents/workspace/CobolToJava/src/javaAfterConversion/OTAMESHI.java";
//        String outputFilePath = srcDirectoryPath + "/Otameshi.java";
//        writeToFile(javaCode, outputFilePath);
    }
    
    private static String extractFileNameWithoutExtension(String filePath) {
        File f = new File(filePath);
        String fileName = f.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) return fileName; // 拡張子がない場合
        return fileName.substring(0, dotIndex);
    }

    private static String readCobolProgram(String filePath) {
        StringBuilder cobolCodeBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cobolCodeBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cobolCodeBuilder.toString();
    }

    private static String convertCobolToJava(String cobolCode, String packageName) {
    	String javaCode = "package " + packageName + ";\n\n";

        javaCode += "public class Otameshi {\n\n";

        javaCode += "    public static void main(String[] args) {\n";
        javaCode += "        new Otameshi().run();\n";
        javaCode += "    }\n\n";
        javaCode += "    private void run() {\n";

        // COBOLコードをJavaコードに変換するロジックを実装
        if (cobolCode.contains("MOVE")) {
            javaCode += "        String moji;\n\n";
            javaCode += "        moji = \"Hello World!!\";\n";
            javaCode += "        System.out.println(\"MOJI = \" + moji);\n";
        }

        javaCode += "    }\n";
        javaCode += "}\n";

        return javaCode;
    }

    private static void writeToFile(String content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

