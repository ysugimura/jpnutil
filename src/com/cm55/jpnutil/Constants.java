package com.cm55.jpnutil;

/**
 *　ここで定義されている値は、あくまでも文字列変換のための便宜的な値であり、一般的なユニコード文字の値範囲を示すものではない。
 * 例えば、{@link #HANANK_START}には空白が含まれていない。
 */
public interface Constants {

  /* 
   * 全角ひらがなの範囲 84個。
   * この範囲はUNICODEの定義よりも狭いが、これ以外のコードポイントには文字が無いか、あるいは使い物にならない。
   * 
   * 全角カタカナの範囲の方が90個と広いが、最初の84個については、ひらがな・カタカナは
   * 対応する文字がマッピングされている。
   */
  
  /** 全角ひらがな開始位置(ぁ) */
  public static final int ZENHIRA_START = 0x3041;

  /** 全角ひらがな終了位置(ゔ) */
  public static final int ZENHIRA_END   = 0x3094;

  /** 全角ひらがな数 */
  public static final int ZENHIRA_COUNT = ZENHIRA_END - ZENHIRA_START + 1;
  
  /* 
   * 全角カタカナの範囲 90個
   * この範囲はUNICODEの定義よりも狭いが、これ以外のコードポイントには文字が無いか、あるいは使いものにならない。
   */
  
  /** 全角カタカナ開始位置(ァ) */
  public static final int ZENKATA_START = 0x30a1;

  /** 全角カタカナ終了位置(ヺ) */
  public static final int ZENKATA_END   = 0x30fa;

  /** 全角カタカナ数 */
  public static final int ZENKATA_COUNT = ZENKATA_END - ZENKATA_START + 1;

  
    
  public static final int HANKATA_START     = 0xff61;
  
  public static final int HANKATA_DAKUON    = 0xff9e; // 半角カタカナ濁音
  public static final int HANKATA_HANDAKUON = 0xff9f; // 半角カタカナ半濁音
  
  public static final int HANKATA_END       = 0xff9f;

  public static final int HANKATA_COUNT = HANKATA_END - HANKATA_START + 1;
  
  public static final char ZENKAKU_DAKUON = '゛'; // 0x309b, 全角単一濁音
  public static final char ZENKAKU_HANDAKUON = '゜'; // 0x309c, 全角単一半濁音
  

}