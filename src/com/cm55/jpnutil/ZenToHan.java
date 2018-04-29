package com.cm55.jpnutil;

/**
 * 全角文字を半角に変換する。
 */
public class ZenToHan {

  /** 文字列を半角へ変換 */
  public static String convert(String s) {
    StringBuffer buf = new StringBuffer();
    new Converter().setConsumer(c->buf.append(c)).convert(s);
    return buf.toString();
  }

  /** コンバータ */
  public static class Converter extends SubConverter {    
    public Converter() {
      super(new Ascii.ZenToHan(), new KanaConverter.ZenHiraToHanKata(), new KanaConverter.ZenKataToHanKata(), 
          new MiscChars.ZenToHan(), new PassThrough());
    }

  }
}
