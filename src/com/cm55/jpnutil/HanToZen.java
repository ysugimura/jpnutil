package com.cm55.jpnutil;

import static com.cm55.jpnutil.Constants.*;

/**
 * 半角文字を全角文字に変換する。
 */
public class HanToZen {

  /** 文字列を全角へ変換 */
  public static String convert(String s) {
    StringBuffer buf = new StringBuffer();
    new Converter(new CharConverter.Terminal(c->buf.append(c))).convertFlush(s);
    return buf.toString();
  }

  /** 半角-->全角コンバータ */
  public static class Converter extends CharConverter {

    HankataToZenProcessor hankataProcessor = new HankataToZenProcessor() {
      public void output(char c) {
        Converter.super.convert(c);
      }      
    };
    Ascii.HanToZen asciiHanToZen = new Ascii.HanToZen() {
      public void output(char c) {
        Converter.super.convert(c);
      }
    };

    public Converter(CharConverter n) {
      super(n);
    }
    
    public void convert(char c) {

      // 半角カタカナの処理。処理された場合はtrueが返される。
      if (hankataProcessor.input(c)) return;

      // 半角Asciiの処理。処理された場合はtrueを返す。
      if (asciiHanToZen.input(c)) return;

      // 上記の処理がされなかった
      super.convert(c);
    }
        
    /** ペンド文字をフラッシュする */
    public void flush() {
      hankataProcessor.flush();
      super.flush();
    }
  }
}
