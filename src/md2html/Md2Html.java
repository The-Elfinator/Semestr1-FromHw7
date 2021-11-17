package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import markup.*;


public class Md2Html {

    public static void main(String[] args) {
        final String input = args[0];
        final String output = args[1];
        try {
             MyScanner scanner = new MyScanner(new InputStreamReader(
                    new FileInputStream(input), StandardCharsets.UTF_8
            ));
            try {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(output), StandardCharsets.UTF_8
                ))) {
                    StringBuilder s = new StringBuilder();
                    String line;
                    List<Markers> list;
                    StringBuilder currentText = new StringBuilder();
                    boolean isHeading = false;
                    boolean isParagraph = false;
                    int level = 0;
                    int indStart = 0;
                    boolean flag = true;
                    while (scanner.hasNextLine() || flag) {
                        line = scanner.nextLine();
                        if (line != null && !line.isEmpty()) {
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
                                                level = 0;
                                            } else {
                                                isHeading = true;
                                                indStart = i + 1;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!isHeading) {
                                isParagraph = true;
                            }
                            currentText.append(line.substring(indStart));
                            indStart = 0;
                        } else {
                            if (!scanner.hasNextLine()) {
                                flag = false;
                            }
                            if (currentText.isEmpty()) {
                                if (!s.isEmpty()) {
                                    s = new StringBuilder();
                                }
                                continue;
                            }
                            list = currentTextToList(currentText);
                            if (isHeading) {
                                Headings heading = new Headings(list, level);
                                heading.toHtml(s);
                                isHeading = false;
                                level = 0;
                            } else if (isParagraph) {
                                level = 0;
                                //Suggest that we only have headings and paragraphs
                                Paragraph paragraph = new Paragraph(list);
                                paragraph.toHtml(s);
                            }
                            writer.write(s.toString());
                            s = new StringBuilder();
                            writer.newLine();
                            currentText = new StringBuilder();
                        }
                    }
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

    private static List<Markers> currentTextToList(StringBuilder currentText) {
        List<Markers> myList = new ArrayList<>();
        int indBeginMarker = 0, indEndMarker = 0;
        String currentTag = "";
        String tags = "*-`_'";
        boolean flag = false;
        boolean backSlash = false;
        int i = 0;
        while (i < currentText.length() + 1) {
            if (flag) {
                flag = false;
                i++;
            } else {
                if (backSlash) {
                    backSlash = false;
                    indEndMarker++;
                    i++;
                } else {
                    if (i < currentText.length() && currentText.charAt(i) == '\\') {
                        backSlash = true;
                        currentText.delete(i, i + 1);
                    } else {
                        if (i == currentText.length() || tags.indexOf(currentText.charAt(i)) != -1) {
                            if (currentTag.isEmpty()) {
                                if (i == currentText.length()) {
                                    myList.add(new Text(currentText.substring(indBeginMarker, indEndMarker)));
                                } else {
                                    if (indBeginMarker != indEndMarker) {
                                        myList.add(new Text(currentText.substring(indBeginMarker, indEndMarker)));
                                    }
                                    currentTag = String.valueOf(currentText.charAt(i));
                                    indBeginMarker = i + 1;
                                    indEndMarker = i + 1;
                                    if (currentText.charAt(i) == currentText.charAt(i + 1)) {
                                        currentTag = String.valueOf(currentText.charAt(i)) + currentText.charAt(i);
                                        indBeginMarker++;
                                        indEndMarker++;
                                        flag = true;
                                    }
                                }
                            } else {
                                if (i == currentText.length()) {
                                    if (currentTag.length() == 2) {
                                        indBeginMarker--;
                                    }
                                    myList.add(new Text(String.valueOf(currentText.charAt(indBeginMarker - 1))));
                                    i = indBeginMarker;
                                    indEndMarker = indBeginMarker;
                                    currentTag = "";
                                }
                                if ((currentTag.equals("**") || currentTag.equals("__")) &&
                                        currentText.charAt(i) == currentText.charAt(i + 1) &&
                                        currentTag.charAt(0) == currentText.charAt(i)) {
                                    myList.add(new Strong(currentTextToList(new StringBuilder(
                                            currentText.substring(indBeginMarker, indEndMarker)
                                    ))));
                                    indBeginMarker = i + 2;
                                    indEndMarker = i + 2;
                                    flag = true;
                                    currentTag = "";
                                } else if ((currentTag.equals("*") || currentTag.equals("_")) &&
                                        currentTag.charAt(0) == currentText.charAt(i)) {
                                    myList.add(new Emphasis(currentTextToList(new StringBuilder(
                                            currentText.substring(indBeginMarker, indEndMarker)
                                    ))));
                                    indBeginMarker = i + 1;
                                    indEndMarker = i + 1;
                                    currentTag = "";
                                } else if (currentTag.equals("''") &&
                                        currentText.charAt(i) == currentText.charAt(i + 1) &&
                                        currentText.charAt(i) == currentTag.charAt(0)) {
                                    myList.add(new Quotes(currentTextToList(new StringBuilder(
                                            currentText.substring(indBeginMarker, indEndMarker)
                                    ))));
                                    indBeginMarker = i + 2;
                                    indEndMarker = i + 2;
                                    flag = true;
                                    currentTag = "";
                                } else if (currentTag.equals("--") &&
                                        currentText.charAt(i) == currentText.charAt(i + 1) &&
                                        currentTag.charAt(0) == currentText.charAt(i)) {
                                    myList.add(new Strikeout(currentTextToList(new StringBuilder(
                                            currentText.substring(indBeginMarker, indEndMarker)
                                    ))));
                                    indBeginMarker = i + 2;
                                    indEndMarker = i + 2;
                                    flag = true;
                                    currentTag = "";
                                } else if (currentTag.equals("`") && currentTag.charAt(0) == currentText.charAt(i)) {
                                    myList.add(new Code(currentTextToList(new StringBuilder(
                                            currentText.substring(indBeginMarker, indEndMarker)
                                    ))));
                                    indBeginMarker = i + 1;
                                    indEndMarker = i + 1;
                                    currentTag = "";
                                } else {
                                    indEndMarker++;
                                }
                            }
                        } else {
                            if (currentText.charAt(i) == '<') {
                                currentText.replace(i, i+1, "&lt;");
                                i++;
                                indEndMarker++;
                            } else if (currentText.charAt(i) == '>') {
                                currentText.replace(i, i+1, "&gt;");
                            } else if (currentText.charAt(i) == '&' && !currentText.substring(i, i+2).equals("&l")
                                    && !currentText.substring(i, i+2).equals("&g")
                                    && !currentText.substring(i, i+2).equals("&a")) {
                                currentText.replace(i, i+1, "&amp;");
                            }
                            indEndMarker++;
                        }
                        i++;
                    }
                }
            }
        }

        return myList;

    }

}
