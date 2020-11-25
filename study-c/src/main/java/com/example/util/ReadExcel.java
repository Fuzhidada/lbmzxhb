package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *  读取数据量庞大的excel。
 *  为什么需要读取数据量大的excel而不使用csv。
 */

public class ReadExcel {

    private String filename;
    private SheetContentsHandler handler;

    public ReadExcel(String filename) {
        this.filename = filename;
    }

    public ReadExcel setHandler(SheetContentsHandler handler) {
        this.handler = handler;
        return this;
    }

    public void parse() {
        OPCPackage pkg = null;
        InputStream sheetInputStream = null;

        try {
            pkg = OPCPackage.open(filename, PackageAccess.READ);
            XSSFReader xssfReader = new XSSFReader(pkg);

            StylesTable styles = xssfReader.getStylesTable();
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
            sheetInputStream = xssfReader.getSheetsData().next();

            processSheet(styles, strings, sheetInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (sheetInputStream != null) {
                try {
                    sheetInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            if (pkg != null) {
                try {
                    pkg.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }

    private void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream) throws SAXException, ParserConfigurationException, IOException {
        XMLReader sheetParser = SAXHelper.newXMLReader();

        if (handler != null) {
            sheetParser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, handler, false));
        } else {
            sheetParser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, new SimpleSheetContentsHandler(), false));
        }

        sheetParser.parse(new InputSource(sheetInputStream));
    }

    public static class SimpleSheetContentsHandler implements SheetContentsHandler {
        protected List<String> row = new ArrayList<>();
        int cellLength = 7;


        @Override
        public void startRow(int rowNum) {
            row = new ArrayList<>(cellLength);
            for (int i = 0; i < cellLength; i++) {
                row.add("");
            }
        }

        @Override
        public void endRow(int rowNum) {

        }

        @Override
        public void cell(String cellReference, String formattedValue, XSSFComment comment) {
            int index = getCellIndex(cellReference);//转换A1,B1,C1等表格位置为真实索引位置
            row.set(index, formattedValue);
        }

        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {
        }

        static int getCellIndex(String cellReference) {
            String ref = cellReference.replaceAll("\\d+", "");
            int num = 0;
            int result = 0;
            for (int i = 0; i < ref.length(); i++) {
                char ch = cellReference.charAt(ref.length() - i - 1);
                num = (int) (ch - 'A' + 1);
                num *= Math.pow(26, i);
                result += num;
            }
            return result - 1;
        }
    }


}
