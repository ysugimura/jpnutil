package com.cm55.jpnutil;

import com.cm55.jpnutil.KanaConverter.*;
import com.cm55.jpnutil.CharConverter.*;

/**
 * 全角ひらがなを全角カタカナに変換する。
 * <p>
 * 変換対象となるのは、「全角ひらがな」のみ。これを全角カタカナに変換する。
 * それ以外の文字種は素通しにする。
 * </p>
 */
public class HiraToKata  {

  private static final Cascading cascading = new Cascading(
    new ZenHiraToZenKata(),  // 全角ひらがなを全角カタカナに変換
    new PassThrough() // 上記以外の文字を素通しする
  );
    
  /** 全角ひらがなを全角カタカナに変換 */
  public static synchronized String convert(String s) {
    StringBuffer buf = new StringBuffer();    
    cascading.to(c->buf.append(c)).convert(s);
    return buf.toString();
  }

}
