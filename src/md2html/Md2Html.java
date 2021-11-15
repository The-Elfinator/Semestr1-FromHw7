package md2html;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import markup.*;

public class Md2Html {

    public static void main(String[] args) {
        final String input = args[0];
        final String output = args[1];
        try {
             MyScanner scanner = new MyScanner(new InputStreamReader(
                    new FileInputStream(input), "utf-8"
            ));
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(output), "utf-8"
                ));
                try {
                    StringBuilder s = new StringBuilder();
                    String line;
                    List<Markers> list = new ArrayList<>();
                    StringBuilder currentText = new StringBuilder();
                    boolean isHeading = false;
                    boolean isParagraph = false;
                    int level = 0;
                    int indStart = 0;
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        if (!line.isEmpty()) {
                            if (!currentText.isEmpty()) {
                                currentText.append(System.lineSeparator().charAt(0));
                            }
                            if (!isHeading) {
                                if (line.charAt(0) == '#') {
                                    for (int i = 0; i < line.length(); i++) {
                                        if (line.charAt(i) == '#') {
                                            level++;
                                        } else {
                                            if (line.charAt(i) != ' ') {
                                                isHeading = false;
                                                level = 0;
                                            } else {
                                                isHeading = true;
                                                indStart = i + 1;
                                            }
                                            break;
                                        }
                                    }
                                } else {
                                    isHeading = false;
                                }
                            }
                            if (!isHeading) {
                                isParagraph = true;
                            }
                            currentText.append(line.substring(indStart));
                            indStart = 0;
                        } else {
                            if (currentText.isEmpty()) {
                                if (!s.isEmpty()) {
                                    s = new StringBuilder();
                                }
                                continue;
                            }
                            if (isHeading) {
                                Headings heading = new Headings(List.of(
                                        new Text(currentText.toString())
                                ), level);
                                heading.toHtml(s);
                                isHeading = false;
                                level = 0;
                            } else if (isParagraph) {
                                level = 0;
                                //Suggest that we only have headings and paragraphs
                                Paragraph paragraph = new Paragraph(List.of(
                                        new Text(currentText.toString())
                                ));
                                paragraph.toHtml(s);
                            }
                            writer.write(s.toString());
                            s = new StringBuilder();
                            writer.newLine();
                            currentText = new StringBuilder();
                        }
                    }
                } finally {
                    writer.close();
                }
            } finally {
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't found file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Q('-'Q)   Couldn't read/write: " + e.getMessage());
        }

    }

}
