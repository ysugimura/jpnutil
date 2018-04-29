package com.cm55.jpnutil;

import com.cm55.jpnutil.CharConverter.*;

/**
 * 全角文字を半角に変換する。
 */
public class ZenToHan {

  private static Cascading cascading = new Cascading(      
      new Ascii.ZenToHan(),  // 全角ANKを半角ANKに変換
      new KanaConverter.ZenHiraToHanKata(), // 全角ひらがなを半角カタカナに変換する
      new KanaConverter.ZenKataToHanKata(),  // 全角カタカナを半角カタカナに変換する
      new MiscChars.ZenToHan(),  // その他の全角文字を半角文字に変換する
      new PassThrough() // 上記以外の文字を素通しする
  );
  
  /** 文字列を半角へ変換 */
  public static synchronized String convert(String s) {
    StringBuffer buf = new StringBuffer();
    cascading.to(c->buf.append(c)).convert(s);
    return buf.toString();
  }
}
