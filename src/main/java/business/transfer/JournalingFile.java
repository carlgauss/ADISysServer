package business.transfer;

import org.joda.time.LocalDate;
import utility.DateConverter;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JournalingFile implements Comparable<JournalingFile> {

    private final static transient String FILE_HEADER = "Journaling ";
    private final static transient String FILE_HEADER_REGEX = "pianificazione[ ]";

    private final static transient String XML_EXTENSION = "xml";
    private final static transient String XML_EXTENSION_REGEX = "[.]" + XML_EXTENSION;

    private final static transient String JOURNALING_PATH = "importazione/";
    private static final transient String DATE_REGEX_STRING = "(([0-9])+[-]){2}([0-9])+";

    private static final transient String FOOTER_REGEX = DATE_REGEX_STRING + XML_EXTENSION_REGEX;

    private static final transient Pattern DATE_REGEX = Pattern.compile(DATE_REGEX_STRING);
    private static final transient int FIRST_SUBGROUP = 0;

    private String fileName;
    private LocalDate date;
    private File file;
    private String infermiereName;

    public JournalingFile(String fileName) {
        this.fileName = fileName;

        String dateString = null;
        Matcher matcher = DATE_REGEX.matcher(fileName);
        while (matcher.find()) {
            dateString = matcher.group(FIRST_SUBGROUP);
        }

        infermiereName = fileName.replaceFirst(FILE_HEADER_REGEX, "");
        infermiereName = infermiereName.replaceFirst(FOOTER_REGEX, "");

        date = LocalDate.parse(dateString);
        file = new File(JOURNALING_PATH + fileName);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFileName() {
        return fileName;
    }

    public String getInfermiereName() {
        return infermiereName;
    }

    public File getFile() {
        return file;
    }

    @Override
    public int compareTo(JournalingFile another) {
        LocalDate anotherDateTime = another.getDate();
        return date.compareTo(anotherDateTime);

    }

    public String toString() {
        return infermiereName + " " + date.toString(DateConverter.EUROPEAN_DATE_FORMAT);
    }
}
