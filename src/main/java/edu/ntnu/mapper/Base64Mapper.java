package edu.ntnu.mapper;

import java.util.Base64;

public class Base64Mapper {

  public static String convertImageToBase64(byte[] image) {
    if (image != null) {
      return Base64.getEncoder().encodeToString(image);
    }
    return null;
  }

  public static byte[] convertBase64ToImage(String base64String) {
    if (base64String != null) {
      return Base64.getDecoder().decode(base64String);
    }
    return null;
  }

}
