package com.cm55.jpnutil;

import java.util.*;

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
    
    Ascii.ZenToHan asciiZenToHan = new Ascii.ZenToHan() {
      public void output(char c) {
        Converter.super.convert(c);
      }
    };
    
    ZenToHankata.FromHira fromHira = new ZenToHankata.FromHira() {
      public void output(char c) {
        Converter.super.convert(c);
      }
    };
    
    ZenToHankata.FromKata fromKata = new ZenToHankata.FromKata() {
      public void output(char c) {
        Converter.super.convert(c);
      }
    };
    
    public Converter(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      
      // 全角ひらがなより半角カタカナ
      if (fromHira.input(c)) return;
      
      // 全角カタカナより半角カタカナ
      if (fromKata.input(c)) return;
      
      // 全角ANKより半角ANK
      if (asciiZenToHan.input(c)) return;
      
      // その他
      Object mapObj = MISC_MAP.get(new Character(c));
      if (mapObj != null) {
        super.convert(((Character)mapObj).charValue());
        return;
      }
      
      // 変換不能
      super.convert(c);
      return;
    }
  }

  static final HashMap<Character,Character> MISC_MAP;
  static {
    char[]misc = new char[] {
        '×', '*', // ×,*:d7,2a
        '’', '\'', // ’,':2019,27
        '”', '"', // ”,":201d,22
        '″', '~', // ″,~:2033,7e
        '－',  '-', // －,-:ff0d,2d
        '　', ' ', // 全角空白, :3000,20
        '、', '､', // 、,､:3001,ff64
        '。', '｡', // ゜,ﾟ:309c,ff9f
        '「', '｢', // 「,｢:300c,ff62
        '」', '｣', // 」,｣:300d,ff63
        '゛', 'ﾞ', // ゛,ﾞ:309b,ff9e
        '゜', 'ﾟ', // 。,｡:3002,ff61
        '・', '･', // ・,･:30fb,ff65
        'ー', 'ｰ', // ー,ｰ:30fc,ff70
        '￥', '\\', // ￥,\:ffe5,5c
    };
    MISC_MAP = new HashMap<Character,Character>();
    for (int i = 0; i < misc.length / 2; i++) {
      MISC_MAP.put(
        new Character(misc[i * 2 + 0]),
        new Character(misc[i * 2 + 1])
      );
    }
  };
}
