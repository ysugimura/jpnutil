package com.cm55.jpnutil;

/**
 * 半角文字を全角文字に変換する。
 */
public class HanToZen {

  /** 文字列を全角へ変換 */
  public static String convert(String s) {
    StringBuffer buf = new StringBuffer();
    new Converter().setConsumer(c->buf.append(c)).convert(s);
    return buf.toString();
  }

  /** 半角-->全角コンバータ */
  public static class Converter extends SubConverter {
    public Converter() {
      super(new HankataToZen(), new Ascii.HanToZen(), new PassThrough());
    }
  }
}
