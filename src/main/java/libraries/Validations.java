package libraries;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.aerogear.security.otp.Totp;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Validations {
    public static Logger logger = LogManager.getLogger(Validations.class);

    public static boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isTimeStampCompleteValid(String inputString) {
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(inputString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isTimeStampValid(String inputString) {
//        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(inputString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static int getNumberOfOccurancesInPdfDoc(String doc, String text) {
        String output = "";
        boolean flag = false;
        int count = 0;
        try {
            File file = new File(doc);
            PDDocument document = null;
            try {
                document = PDDocument.load(file);
                output = new PDFTextStripper().getText(document);
                count = StringUtils.countMatches(output, text);
                System.out.println("Number of Occurances of '" + text + "' is " + count);
            } finally {
                if (document != null) {
                    document.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int getNumberOfZeroBalances(String doc, String text) {
        String output = "";
        int count = 0;
        try {
            File file = new File(doc);
            PDDocument document = null;
            try {
                document = PDDocument.load(file);
                output = new PDFTextStripper().getText(document);

                String lines[] = output.split("\\r?\\n");
                for (int i=0;i<lines.length;i++) {
                    System.out.println(i + "#" + lines[i]);
                    if (lines[i].toLowerCase().contains(text.toLowerCase())) {
                        String[] textSplit = lines[i].split("NET BALANCE");
                        String amount = textSplit[0].trim();
                        System.out.println("##"+amount);
                        double amountVal = Double.parseDouble(amount.replace("(", "")
                                .replace(")","").replace(",", ""));
                        System.out.println("###"+amountVal);
                        if (amountVal > 0) {
                            count++;;
                            System.out.println("#####amount greater than zero");
                        }
                    }
                }
            } finally {
                if (document != null) {
                    document.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int getNumberOfOccurancesInXLSXDoc(String doc, String text) throws IOException {
        int count = 0;
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(doc));
            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet s = book.getSheetAt(0);

            Iterator itr = s.iterator();
            while (itr.hasNext()) {
                Row rowitr = (Row) itr.next();
                Iterator cellitr = rowitr.cellIterator();
                while (cellitr.hasNext()) {
                    Cell celldata = (Cell) cellitr.next();
                    switch (celldata.getCellType()) {
                        case STRING:
//                            System.out.println("celldata.getStringCellValue()#" + celldata.getStringCellValue());
                            if (celldata.getStringCellValue().toLowerCase().contains(text.toLowerCase())) {
                                System.out.println("celldata.getStringCellValue()#" + celldata.getStringCellValue());
                                count++;
                            }
                    }
                }
            }
            System.out.println("Number of Occurances of '" + text + "' is " + count);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } finally {
            if (file != null) {
                file.close();
            }
        }
        return count;
    }

    public static int compareDates(String first, String second) throws InterruptedException, ParseException {
        logger.info("comparing Dates#" + first + "#" + second);
        Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(first);
        Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(second);
        return start.compareTo(end);
    }

    public static int compareDate(String first, String second) throws InterruptedException, ParseException {
        logger.info("comparing Dates#" + first + "#" + second);
        Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", Locale.ENGLISH).parse(first);
        Date end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", Locale.ENGLISH).parse(second);
        return start.compareTo(end);
    }

    public static int compareAuditDates(String first, String second) throws InterruptedException, ParseException {
        logger.info("comparing Dates#" + first + "#" + second);
        Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(first);
        Date end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", Locale.ENGLISH).parse(second);
        return start.compareTo(end);
    }

    public static int compareAuditDatesQueues(String first, String second) throws InterruptedException, ParseException {
        logger.info("comparing Dates#" + first + "#" + second);
        Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(first);
        Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH).parse(second);
        return start.compareTo(end);
    }

    public static String dateCovertddMMyyyy(String dateToconvert) throws ParseException {
        Date dt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateToconvert);
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String strDate = formatter.format(dt);
        return strDate;
    }

    public static String removeSpecialCharacters(String str) throws ParseException {
        return str.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static double getDecimalRoundOff2Digits(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }

    @Test
    public void removeSpecialCharacters123() throws ParseException {
        String dateToconvert = "2019-02-12";
        Date dt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateToconvert);
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String strDate = formatter.format(dt);
        System.out.println(strDate);
    }

    public static String getXMLContent(File DataInFile) throws ParserConfigurationException, IOException, SAXException {
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDocument = db.parse(DataInFile);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer;
            try {
                transformer = tf.newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
                String xmlString = writer.getBuffer().toString();
                return xmlString;
            }
            catch (TransformerException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String get6DigitMFACode(String qrCode) throws IOException, NotFoundException {
        File file = new File(System.getProperty("user.dir")+"\\QR_Codes\\"+qrCode+".png");
        String decodedText = null;
        BufferedImage bufferedImage = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        decodedText =  result.getText();
        System.out.println("decodedText#"+decodedText);
        String secret = org.apache.commons.lang.StringUtils.substringBetween(decodedText, "secret=", "&");
        System.out.println("Secret#"+secret);
        Totp totp = new Totp(secret);
        String totpCode=totp.now();
        System.out.println("TOTP Code#"+totpCode);
        return totpCode;
    }
}