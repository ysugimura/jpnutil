package com.cm55.jpnutil;

import com.cm55.jpnutil.Converter.*;
import com.cm55.jpnutil.KanaConverter.*;

/**
 * 全角ひらがなを全角カタカナに変換する。
 * <p>
 * 変換対象となるのは、「全角ひらがな」のみ。これを全角カタカナに変換する。
 * それ以外の文字種は素通しにする。
 * </p>
 */
public class HiraToKata  {

  private static final Cascading cascading = new Cascading(
    new ZenHiraToZenKata()  // 全角ひらがなを全角カタカナに変換
  );
    
  /** 全角ひらがなを全角カタカナに変換 */
  public static String convert(String s) {
    return cascading.convert(s);
  }

}
