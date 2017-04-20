package converter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by gadzik on 18.04.17.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String dateString) throws Exception {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.toString();
    }
}
