package com.cm55.jpnutil;

import com.cm55.jpnutil.KanaConverter.*;
import com.cm55.jpnutil.SubConverter.*;

/**
 * 全角カタカナを全角ひらがなに変換する。
 * <p>
 * 対象とするのは「全角カタカナ」のみ。これを全角ひらがなに変換する。
 * それ以外の文字種は素通しにする。
 * </p>
 */
public class KataToHira {

  /** 全角カタカナを全角ひらがなに変換 */
  public static String convert(String s) {
    StringBuffer buf = new StringBuffer();
    new SubConverter(new ZenKataToZenHira(), new PassThrough()).setConsumer(c->buf.append(c)).convert(s);
    return buf.toString();
  }
  

}
