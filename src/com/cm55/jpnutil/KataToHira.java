package com.cm55.jpnutil;

import com.cm55.jpnutil.Converter.*;
import com.cm55.jpnutil.KanaConverter.*;

/**
 * 全角カタカナを全角ひらがなに変換する。
 * <p>
 * 対象とするのは「全角カタカナ」のみ。これを全角ひらがなに変換する。
 * それ以外の文字種は素通しにする。
 * </p>
 */
public class KataToHira {

  private static final Cascading cascading = new Cascading(
    new ZenKataToZenHira()  // 全角カタカナを全角ひらがなに変換する。
  );
  
  /** 全角カタカナを全角ひらがなに変換 */
  public static String convert(String s) {
    return cascading.convert(s);
  }
}
