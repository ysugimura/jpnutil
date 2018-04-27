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
      void output(char c) {
        Converter.super.convert(c);
      }      
    };

    public Converter(CharConverter n) {
      super(n);
    }
    
    public void convert(char c) {

      int code = (int)c & 0xffff;

      // 半角カタカナの処理。処理された場合はtrueが返される。
      if (hankataProcessor.processHankata(code)) return;

      // 空白
      if (code == 0x20) {
        super.convert('\u3000');
        return;
      }
      
      // ANK
      if (HANANK_START <= code && code <= HANANK_END) {
        int index = code - HANANK_START;
        super.convert((char)(ZENANK_START + index));
        return;
      }

      super.convert(c);
    }
        
    /** ペンド文字をフラッシュする */
    public void flush() {
      hankataProcessor.flush();
      super.flush();
    }
  }
}
