package com.cm55.jpnutil;

/**
 * 全角ひらがなを全角カタカナに変換する。
 * <p>
 * 変換対象となるのは、「全角ひらがな」のみ。これを全角カタカナに変換する。
 * それ以外の文字種は素通しにする。
 * </p>
 */
public class HiraToKata implements Constants {

  /** 全角ひらがなを全角カタカナに変換 */
  public static String convert(String s) {
    final StringBuffer buf = new StringBuffer();
    Converter converter = new Converter(new CharConverter(null) {
      public void convert(char c) {
        buf.append(c);
      }
    });
    converter.convert(s);
    converter.flush();
    return buf.toString();
  }
  
  /** 全角ひらがな-->全角カタカナコンバータ */
  public static class Converter extends CharConverter { 
    public Converter(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      int code = (int)c;
      if (ZENHIRA_START <= code && code <= ZENHIRA_END)
        c = (char)(ZENKATA_START + (code - ZENHIRA_START));
      super.convert(c);
    }
  }
}
