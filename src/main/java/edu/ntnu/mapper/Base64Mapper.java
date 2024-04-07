package edu.ntnu.mapper;

import java.util.Base64;
import java.util.logging.Logger;

public class Base64Mapper {
  static Logger logger = Logger.getLogger(Base64Mapper.class.getName());

  public static String convertImageToBase64(byte[] image) {
    if (image != null) {
      return Base64.getEncoder().encodeToString(image);
    }
    return null;
  }

  public static byte[] convertBase64ToImage(String base64String) {
    if (base64String != null) {

      if (base64String.startsWith("data:image/jpeg;base64,")) {
        // Fjern headeren fra base64-strengen
        base64String = base64String.substring("data:image/jpeg;base64,".length());
      }



      logger.info("Converting base64 string to image");
      logger.info("Base64 string: " + base64String);
      return Base64.getDecoder().decode(base64String);
    }
    return null;
  }

}
