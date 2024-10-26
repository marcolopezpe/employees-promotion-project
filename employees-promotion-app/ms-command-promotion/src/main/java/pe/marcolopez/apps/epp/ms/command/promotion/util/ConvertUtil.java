package pe.marcolopez.apps.epp.ms.command.promotion.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConvertUtil {

  public static LocalDate convertToLocalDate(Long timestamp) {
    return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalDateTime convertToLocalDateTime(Long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
  }

  public static Long convertToLong(LocalDate localDate) {
    return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }

  public static String proposedLevel(String currentLevel) {
    return switch (currentLevel) {
      case "JUNIOR" -> "MIDDLE";
      case "MIDDLE" -> "SENIOR";
      case "SENIOR" -> "MASTER";
      default -> "";
    };
  }

  public static String previousLevel(String currentLevel) {
    return switch (currentLevel) {
      case "MIDDLE" -> "JUNIOR";
      case "SENIOR" -> "MIDDLE";
      case "MASTER" -> "SENIOR";
      default -> "";
    };
  }
}
