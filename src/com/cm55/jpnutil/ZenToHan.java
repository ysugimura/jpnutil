package com.cm55.jpnutil;

/**
 * 全角文字を半角に変換する。
 */
public class ZenToHan implements Constants {

  /** 文字列を半角へ変換 */
  public static String convert(String s) {
    StringBuffer buf = new StringBuffer();
    new Converter(new CharConverter.Terminal(c->buf.append(c))).convertFlush(s);
    return buf.toString();
  }

  /** コンバータ */
  public static class Converter extends CharConverter {
    
    Ascii.ZenToHan asciiZenToHan = new Ascii.ZenToHan(this::output) ;
    
    ZenToHankata.FromHira fromHira = new ZenToHankata.FromHira(this::output);
    
    ZenToHankata.FromKata fromKata = new ZenToHankata.FromKata(this::output);
    
    MiscChars.ZenToHan miscChars = new MiscChars.ZenToHan(this::output);
    
    public Converter(CharConverter n) {
      super(n);
    }
    
    void output(char c) {
      super.convert(c);
    }
    
    public void convert(char c) {
      
      // 全角ひらがなより半角カタカナ
      if (fromHira.input(c)) return;
      
      // 全角カタカナより半角カタカナ
      if (fromKata.input(c)) return;
      
      // 全角ANKより半角ANK
      if (asciiZenToHan.input(c)) return;
      
      // その他
      if (miscChars.input(c)) return;
      
      // 変換不能
      super.convert(c);
      return;
    }
  }
}
