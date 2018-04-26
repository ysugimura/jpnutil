package com.cm55.jpnutil;

import java.io.*;
import java.util.*;

/**
 * シフトJISユーティリティ
 */
public class SJISUtil {
  
  public static final String SJIS_ENCODING = "Windows-31J";

  /** SJISバイト列を文字列に変換 */
  public static String getString(byte[]bytes) {
    return getString(bytes, 0, bytes.length);
  }
  
  /** SJISバイト列を文字列に変換 */
  public static String getString(byte[]bytes, int pos, int size) {
    try {
      return new String(bytes, pos, size, SJIS_ENCODING);
    } catch (UnsupportedEncodingException ex) {
      throw new InternalError();
    }
  }
  
  /** 文字列をSJISバイト列に変換 */
  public static byte[]getBytes(String s) {
    try {
      return s.getBytes(SJIS_ENCODING);    
    } catch (UnsupportedEncodingException ex) {
      throw new InternalError();
    }
  }
  
  /** 文字列をSJISに変換した場合のバイト数を取得 */
  public static int byteCount(String s) {
    return s.getBytes().length;
  }
  
  /**
   * 漢字の前半であるか。
   * <p>
   * ちなみにShift-JISでは漢字の後半であることを判断する方法はない。
   * 通常は、あるバイトが「ANK(半角カナ含む）」か「漢字前半」かを判断し、
   * もし「漢字前半」であれば、その次の文字は「漢字後半」であるとしなければならない。
   * </p>
   */
  public static boolean isKanji(byte b) {
    int c = ((int)b) & 0xff;
    return 0x81 <= c && c <= 0x9f || 0xe0 <= c && c <= 0xef;
  }
  
  /** 指定バイト列のidx番目のバイトの種類を得る
   * 0:ANK、 1:漢字前半、2:漢字後半
   */
  public static int sjisKind(byte[]sjis, int idx) {
    if (idx >= sjis.length)
      throw new InternalError();
    int i = 0;
    while (true) {
      if (isKanji(sjis[i])) {
        if (i == idx) return 1;
        if (i == idx - 1) return 2;
        i += 2;
      } else {
        if (i == idx) return 0;
        i++;
      }
    }
  }
  
  /**
   * シフトJISバイト列を最大長さwidthのバイト列の配列にする。
   * ただし、漢字の途中では分割しない。
   * 
   * @param sjis シフトJISバイト列
   * @param width　最大幅
   * @return バイト列の配列
   */
  public static byte[][]sjisDivide(byte[]sjis, int width) {
    
    if (width < 2) throw new IllegalArgumentException();
    
    java.util.List<byte[]>list = new ArrayList<byte[]>();
    
    int index = 0;
    int restSize = sjis.length;
    while (true) {
      if (restSize <= width) {
        byte[]lineBytes = new byte[restSize];
        System.arraycopy(sjis, index,  lineBytes, 0, restSize);
        list.add(lineBytes);
        break;
      }
      int lineSize = width;
      if (sjisKind(sjis, index + width) == 2) {
        lineSize--;
      }
      byte[]lineBytes = new byte[lineSize];
      System.arraycopy(sjis, index, lineBytes, 0, lineSize);
      list.add(lineBytes);
      index += lineSize;
      restSize -= lineSize;
    }
      
    return list.toArray(new byte[0][]);
  }
  
  public static String[]sjisDivide(String s, int width) {
    try {
      byte[]sjis = s.getBytes(SJIS_ENCODING);
      byte[][]divided = sjisDivide(sjis, width);
      String[]result = new String[divided.length];
      for (int i = 0; i < result.length; i++) {
        result[i] = new String(divided[i], SJIS_ENCODING);
      }
      return result;
    } catch (UnsupportedEncodingException ex) {
      throw new InternalError();
    }
  }
}
