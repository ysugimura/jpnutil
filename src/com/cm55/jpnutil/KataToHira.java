package com.cm55.jpnutil;

import com.cm55.jpnutil.KanaConverter.*;
import com.cm55.jpnutil.CharConverter.*;

/**
 * 全角カタカナを全角ひらがなに変換する。
 * <p>
 * 対象とするのは「全角カタカナ」のみ。これを全角ひらがなに変換する。
 * それ以外の文字種は素通しにする。
 * </p>
 */
public class KataToHira {

  private static final Cascading cascading = new Cascading(
    new ZenKataToZenHira(),  // 全角カタカナを全角ひらがなに変換する。
    new PassThrough() // 上記以外の文字を素通しにする
  );
  
  /** 全角カタカナを全角ひらがなに変換 */
  public static synchronized String convert(String s) {
    StringBuffer buf = new StringBuffer();
    cascading.to(c->buf.append(c)).convert(s);
    return buf.toString();
  }
}
