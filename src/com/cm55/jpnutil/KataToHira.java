package com.cm55.jpnutil;

/**
 * 全角カタカナを全角ひらがなに変換する。
 * <p>
 * 対象とするのは「全角カタカナ」のみ。これを全角ひらがなに変換する。
 * それ以外の文字種は素通しにする。
 * </p>
 */
public class KataToHira implements Constants {

  /** 全角カタカナを全角ひらがなに変換 */
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
  
  /** 全角カタカナ-->全角ひらがなコンバータ */
  public static class Converter extends CharConverter { 
    public Converter(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      int code = (int)c;
      if (ZENKATA_START <= code && code <= ZENKATA_END)
        c = (char)(ZENHIRA_START + (code - ZENKATA_START));
      super.convert(c);
    }
  }
}
