package com.cm55.jpnutil;
import static com.cm55.jpnutil.Constants.*;

import java.util.*;

/**
 * 日本語文字種
 */
public enum CharKind {
  HANKAKU, // 全角
  ZENKAKU, // 半角
  KANA, // かな・カナ
  KATAKANA, // カタカナ
  HIRAGANA, // ひらがな
  KANJI, // 漢字
  ANK; // ANK
  
  /** 日本語文字種を取得する */
  public static EnumSet<CharKind> get(char c) {
    
    Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
      
    int code = c;

    // 全角ひらがな
    if (ZENHIRA_START <= code && code <= ZENHIRA_END)
      return EnumSet.of(ZENKAKU, HIRAGANA, KANA);

    // 全角カタカナ
    if (ZENKATA_START <= code && code <= ZENKATA_END)
      return EnumSet.of(ZENKAKU, KATAKANA, KANA);

    // 全角ANK
    if (ZENANK_START <= code && code <= ZENANK_END) {
      return EnumSet.of(ZENKAKU, ANK);
    }

    // 半角カタカナ
    if (HANKATA_START <= code && code <= HANKATA_HANDAKUON) {
      return EnumSet.of(KATAKANA, HANKAKU, KANA);
    }

    // 半角ANK
    if (code < 0x100) {
      return EnumSet.of(ANK, HANKAKU);
    }

    // 漢字、4E00–9FCF
    if (block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
      return EnumSet.of(KANJI);
      
    // 不明
    return EnumSet.noneOf(CharKind.class);
  }
}
