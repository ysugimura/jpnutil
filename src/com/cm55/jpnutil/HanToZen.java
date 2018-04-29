package com.cm55.jpnutil;

import com.cm55.jpnutil.CharConverter.*;

/**
 * 半角文字を全角文字に変換する。
 */
public class HanToZen {

  private static final Cascading cascading = new Cascading(
    new HankataToZen(),  // 半角カタカナを全角カタカナに変換
    new Ascii.HanToZen(), // 半角ANKを全角ANKに変換
    new PassThrough() // 上記以外の文字種を素通しする
  );
  
  /** 文字列を全角へ変換 */
  public static synchronized String convert(String s) {
    StringBuffer buf = new StringBuffer();
    cascading.to(c->buf.append(c)).convert(s);
    return buf.toString();
  }
}
