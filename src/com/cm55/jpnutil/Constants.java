package com.cm55.jpnutil;

/**
 *
 */
public interface Constants {

  /** 全角ひらがな開始位置(ぁ) */
  public static final int ZENHIRA_START = 0x3041;

  /** 全角ひらがな終了位置(ん) */
  public static final int ZENHIRA_END   = 0x3093;

  /** 全角カタカナ開始位置(ァ) */
  public static final int ZENKATA_START = 0x30a1;

  /** 全角カタカナ終了位置(ヶ) */
  public static final int ZENKATA_END   = 0x30f6;

  /** 全角ＡＮＫ開始位置 */
  public static final int ZENANK_START = 0xff01;

  /** 全角ＡＮＫ終了位置 */
  public static final int ZENANK_END = 0xff5d;

  public static final int HANKATA_START     = 0xff61;
  public static final int HANKATA_END       = 0xff9d;
  public static final int HANKATA_DAKUON    = 0xff9e; // 半角カタカナ濁音
  public static final int HANKATA_HANDAKUON = 0xff9f; // 半角カタカナ半濁音
    // DAKUON/HANDAKUONがHANKATA_END内に含まれない理由不明


  /* ひらがなは８３個、カタカナは８６個であるが、最初の８３個のマッピングは同じに
     なっている。カタカナの最後の３個は「ヴ」「ヵ」「ヶ」であり、これらに対応する
     ひらがなは存在しない。
   */
}