package com.cm55.jpnutil;

import com.cm55.jpnutil.Converter.*;

/**
 * 半角文字を全角文字に変換する。
 */
public class HanToZen {

  private static final Cascading cascading = new Cascading(
    new HankataToZen(),  // 半角カタカナを全角カタカナに変換
    new Ascii.HanToZen() // 半角ANKを全角ANKに変換

  );
  
  /** 文字列を全角へ変換 */
  public static String convert(String s) {
    return cascading.convert(s);
  }
}
