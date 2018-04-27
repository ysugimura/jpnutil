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
    StringBuffer buf = new StringBuffer();
    new Converter(new CharConverter.Terminal(c->buf.append(c))).convertFlush(s);
    return buf.toString();
  }
  
  /** 全角カタカナ-->全角ひらがなコンバータ */
  public static class Converter extends CharConverter { 
    public Converter(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      // 全角カタカナと仮定して、スタート位置からの位置を求める
      int index = (int)c - ZENKATA_START;
      
      // この位置が全角ひらがな範囲にあれば、全角ひらがなに変換
      if (0 <= index && index < ZENHIRA_COUNT) {
        c = (char)(ZENHIRA_START + index);
      }
      
      super.convert(c);
    }
  }
}
