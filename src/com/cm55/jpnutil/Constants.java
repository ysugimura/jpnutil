package com.cm55.jpnutil;

/**
 *
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
  
  /*
   * 全角ANKの範囲。半角ANKの範囲と全く同じで、対応する文字が一致する
   * ※ここでは空白が抜けていることに注意
   */
  
  /** 全角ＡＮＫ開始位置 */
  public static final int ZENANK_START = 0xff01;

  /** 全角ＡＮＫ終了位置 */
  public static final int ZENANK_END = 0xff5e;

  /** 全角ANK数 */
  public static final int ZENANK_COUNT = ZENANK_END - ZENANK_START + 1;
  
  /*
   * 半角ANKの範囲。全角ANKの範囲と全く同じで、対応する文字が一致する
   * ※ここでは空白は抜けていることに注意
   */
  
  /** 半角ＡＮＫ開始位置 */
  public static final int HANANK_START = 0x21;

  /** 半角ＡＮＫ終了位置 */
  public static final int HANANK_END = 0x7e;

  /** 半角ANK数 */
  public static final int HANANK_COUNT = HANANK_END - HANANK_START + 1;
  
  
  
  public static final int HANKATA_START     = 0xff61;
  public static final int HANKATA_END       = 0xff9d;
  public static final int HANKATA_DAKUON    = 0xff9e; // 半角カタカナ濁音
  public static final int HANKATA_HANDAKUON = 0xff9f; // 半角カタカナ半濁音
    // DAKUON/HANDAKUONがHANKATA_END内に含まれない理由不明

}