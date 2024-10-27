package pe.marcolopez.apps.epp.ms.consumer.promotion.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

  public static LocalDate convertStringToLocalDate(String dateString, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    try {
      return LocalDate.parse(dateString, formatter);
    } catch (Exception ignored) {
      return null;
    }
  }
}
