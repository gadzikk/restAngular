package converter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by gadzik on 11.03.17.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return DateTimeFormatter.ISO_DATE.format(localDate);
    }
}

