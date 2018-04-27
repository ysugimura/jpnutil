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
    StringBuffer buf = new StringBuffer();
    new Converter(new CharConverter.Terminal(c->buf.append(c))).convertFlush(s);
    return buf.toString();
  }
  
  /** 全角ひらがな-->全角カタカナコンバータ */
  public static class Converter extends CharConverter { 
    public Converter(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      // 文字が全角ひらがなと仮定し、スタートからの位置を求める
      int index = (int)c - ZENHIRA_START;
      
      // この位置が全角カタカナの範囲であれば、全角カタカナに変換
      if (0 <= index && index < ZENKATA_COUNT)
        c = (char)(ZENKATA_START + index);
      
      super.convert(c);
    }
  }
}
