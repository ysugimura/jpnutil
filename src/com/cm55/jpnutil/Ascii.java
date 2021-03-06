package com.cm55.jpnutil;

/**
 * ASCIIコードの0x20-0x7eの範囲の文字について、全角・半角相互変換を行う。
 * @author ysugimura
 */
public class Ascii {
  
  /*
   * 全角ANKの範囲。半角ANKの範囲と全く同じで、対応する文字が一致する
   * ※ここでは空白が抜けていることに注意
   */
  
  /** 全角ＡＮＫ開始位置 */
  static final int ZENANK_START = 0xff01;

  /** 全角ＡＮＫ終了位置 */
  static final int ZENANK_END = 0xff5e;

  /** 全角ANK数。半角ANK数と同一であること */
  static final int ZENANK_COUNT = ZENANK_END - ZENANK_START + 1;
  
  /*
   * 半角ANKの範囲。全角ANKの範囲と全く同じで、対応する文字が一致する
   * ※ここでは空白は抜けていることに注意
   */
  
  /** 半角ＡＮＫ開始位置 */
  static final int HANANK_START = 0x21;

  /** 半角ＡＮＫ終了位置 */
  static final int HANANK_END = 0x7e;

  /** 半角ANK数。全角ANK数と同一であること */
  static final int HANANK_COUNT = HANANK_END - HANANK_START + 1;

  /**
   * 空白を除くANK数
   */
  static final int ANK_COUNT = ZENANK_COUNT; // == HANANK_COUNT  
  
  /*
   * 空白については以下で定義
   */
  
  /** 半角スペース */
  static final char HANKAKU_SPACE = ' ';
  
  /** 全角スペース */
  static final char ZENKAKU_SPACE = '\u3000';

  /**
   * 半角ASCII->全角ASCII変換
   */
  public static class HanToZen extends Converter.PerChar {
    @Override
    public void process(CharOutput output, char c) {
      // 空白の変換
      if (c == HANKAKU_SPACE) {
        output.accept(ZENKAKU_SPACE);
        return;
      }
      
      // 半角ANK->全角ANK数
      int index = c - HANANK_START;
      if (0 <= index && index < ANK_COUNT) {
        output.accept((char)(ZENANK_START + index));
        return;
      }
      
      output.accept(c);
    }
  }

  /**
   * 全角ASCII->半角ASCII変換
   */
  public static class ZenToHan extends Converter.PerChar {
    @Override
    public void process(CharOutput output, char c) {
      if (c == ZENKAKU_SPACE) {
        output.accept(HANKAKU_SPACE);
        return;
      }
      int index = c - ZENANK_START;
      if (0 <= index && index < ANK_COUNT) {
        output.accept((char)(index + HANANK_START));
        return; 
      }
      output.accept(c);
    }
  }
}
