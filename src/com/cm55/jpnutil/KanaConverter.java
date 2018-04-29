package com.cm55.jpnutil;

/**
 * ひらがな・カタカナコンバータ
 * @author ysugimura
 */
public class KanaConverter {
  
  /* 
   * 全角ひらがなの範囲 84個。
   * この範囲はUNICODEの定義よりも狭いが、これ以外のコードポイントには文字が無いか、あるいは使い物にならない。
   * 
   * 全角カタカナの範囲の方が90個と広いが、最初の84個については、ひらがな・カタカナは
   * 対応する文字がマッピングされている。
   */
  
  /** 全角ひらがな開始位置(ぁ) */
  static final int ZENHIRA_START = 0x3041;

  /** 全角ひらがな終了位置(ゔ) */
  static final int ZENHIRA_END   = 0x3094;

  /** 全角ひらがな数 */
  static final int ZENHIRA_COUNT = ZENHIRA_END - ZENHIRA_START + 1;
  
  /* 
   * 全角カタカナの範囲 90個
   * この範囲はUNICODEの定義よりも狭いが、これ以外のコードポイントには文字が無いか、あるいは使いものにならない。
   */
  
  /** 全角カタカナ開始位置(ァ) */
  static final int ZENKATA_START = 0x30a1;

  /** 全角カタカナ終了位置(ヺ) */
  static final int ZENKATA_END   = 0x30fa;

  /** 全角カタカナ数 */
  static final int ZENKATA_COUNT = ZENKATA_END - ZENKATA_START + 1;
  
  /** 全角ひらがなから全角カタカナへ変換 */
  public static class ZenHiraToZenKata extends CharConverter {     
    @Override
    public boolean process(char c) {
      // 文字が全角ひらがなと仮定し、スタートからの位置を求める
      int index = (int)c - ZENHIRA_START;
      
      // この位置が全角カタカナの範囲であれば、全角カタカナに変換
      if (0 <= index && index < ZENKATA_COUNT) {
        output((char)(ZENKATA_START + index));
        return true;
      }
      
      return false;
    }
  }
  
  /** 全角カタカナから全角ひらがなへ変換 */
  public static class ZenKataToZenHira extends CharConverter { 
    @Override
    public boolean process(char c) {
      // 全角カタカナと仮定して、スタート位置からの位置を求める
      int index = (int)c - ZENKATA_START;
      
      // この位置が全角ひらがな範囲にあれば、全角ひらがなに変換
      if (0 <= index && index < ZENHIRA_COUNT) {
        c = (char)(ZENHIRA_START + index);
        output(c);
        return true;
      }
      
      return false;
    }
  }
  
  /** 全角ひらがなから半角カタカナへ変換 */
  public static class ZenHiraToHanKata extends CharConverter {    
    @Override
    public boolean process(char c) {
      int index = c - ZENHIRA_START;
      if (index < 0 || ZENHIRA_COUNT <= index) return false;
      char c1 = TO_HANKATA_TABLE[index * 2 + 0];
      char c2 = TO_HANKATA_TABLE[index * 2 + 1];
      if (c1 == 0) {
        output(c);
        return true;
      }
      if (c2 == 0) {
        output(c1);
        return true;
      }
      output(c1);
      output(c2);
      return true;
    }
  }

  /** 全角カタカナから半角カタカナへ変換 */
  public static class ZenKataToHanKata extends CharConverter {    
    @Override
    public boolean process(char c) {
      int index = c - ZENKATA_START;
      if (index < 0 || ZENKATA_COUNT <= index) return false;
      char c1 = TO_HANKATA_TABLE[index * 2 + 0];
      char c2 = TO_HANKATA_TABLE[index * 2 + 1];
      if (c1 == 0) {
        output(c);
        return true;
      }
      if (c2 == 0) {
        output(c1);
        return true;
      }
      output(c1);
      output(c2);
      return true;
    }
  }
  
  /** 全角ひらがな・全角カタカナから半角カタカナへ */
  static final char TO_HANKATA_TABLE[] = new char[] {
        /* 'ァ' */ 'ｧ', 0,  // ひらがな：0x3041、カタカナ：0x30a1
        /* 'ア' */ 'ｱ', 0,
        /* 'ィ' */ 'ｨ', 0,
        /* 'イ' */ 'ｲ', 0,
        /* 'ゥ' */ 'ｩ', 0,
        /* 'ウ' */ 'ｳ', 0,
        /* 'ェ' */ 'ｪ', 0,
        /* 'エ' */ 'ｴ', 0,
        /* 'ォ' */ 'ｫ', 0,
        /* 'オ' */ 'ｵ', 0,
        /* 'カ' */ 'ｶ', 0,
        /* 'ガ' */ 'ｶ', 'ﾞ',
        /* 'キ' */ 'ｷ', 0,
        /* 'ギ' */ 'ｷ', 'ﾞ',
        /* 'ク' */ 'ｸ', 0,
        /* 'グ' */ 'ｸ', 'ﾞ',
        /* 'ケ' */ 'ｹ', 0,
        /* 'ゲ' */ 'ｹ', 'ﾞ',
        /* 'コ' */ 'ｺ', 0,
        /* 'ゴ' */ 'ｺ', 'ﾞ',
        /* 'サ' */ 'ｻ', 0,
        /* 'ザ' */ 'ｻ', 'ﾞ',
        /* 'シ' */ 'ｼ', 0,
        /* 'ジ' */ 'ｼ', 'ﾞ',
        /* 'ス' */ 'ｽ', 0,
        /* 'ズ' */ 'ｽ', 'ﾞ',
        /* 'セ' */ 'ｾ', 0,
        /* 'ゼ' */ 'ｾ', 'ﾞ',
        /* 'ソ' */ 'ｿ', 0,
        /* 'ゾ' */ 'ｿ', 'ﾞ',
        /* 'タ' */ 'ﾀ', 0,
        /* 'ダ' */ 'ﾀ', 'ﾞ',
        /* 'チ' */ 'ﾁ', 0,
        /* 'ヂ' */ 'ﾁ', 'ﾞ',
        /* 'ッ' */ 'ｯ', 0,
        /* 'ツ' */ 'ﾂ', 0,
        /* 'ヅ' */ 'ﾂ', 'ﾞ',
        /* 'テ' */ 'ﾃ', 0,
        /* 'デ' */ 'ﾃ', 'ﾞ',
        /* 'ト' */ 'ﾄ', 0,
        /* 'ド' */ 'ﾄ', 'ﾞ',
        /* 'ナ' */ 'ﾅ', 0,
        /* 'ニ' */ 'ﾆ', 0,
        /* 'ヌ' */ 'ﾇ', 0,
        /* 'ネ' */ 'ﾈ', 0,
        /* 'ノ' */ 'ﾉ', 0,
        /* 'ハ' */ 'ﾊ', 0,
        /* 'バ' */ 'ﾊ', 'ﾞ',
        /* 'パ' */ 'ﾊ', 'ﾟ',
        /* 'ヒ' */ 'ﾋ', 0,
        /* 'ビ' */ 'ﾋ', 'ﾞ',
        /* 'ピ' */ 'ﾋ', 'ﾟ',
        /* 'フ' */ 'ﾌ', 0,
        /* 'ブ' */ 'ﾌ', 'ﾞ',
        /* 'プ' */ 'ﾌ', 'ﾟ',
        /* 'ヘ' */ 'ﾍ', 0,
        /* 'ベ' */ 'ﾍ', 'ﾞ',
        /* 'ペ' */ 'ﾍ', 'ﾟ',
        /* 'ホ' */ 'ﾎ', 0,
        /* 'ボ' */ 'ﾎ', 'ﾞ',
        /* 'ポ' */ 'ﾎ', 'ﾟ',
        /* 'マ' */ 'ﾏ', 0,
        /* 'ミ' */ 'ﾐ', 0,
        /* 'ム' */ 'ﾑ', 0,
        /* 'メ' */ 'ﾒ', 0,
        /* 'モ' */ 'ﾓ', 0,
        /* 'ャ' */ 'ｬ', 0,
        /* 'ヤ' */ 'ﾔ', 0,
        /* 'ュ' */ 'ｭ', 0,
        /* 'ユ' */ 'ﾕ', 0,
        /* 'ョ' */ 'ｮ', 0,
        /* 'ヨ' */ 'ﾖ', 0,
        /* 'ラ' */ 'ﾗ', 0,
        /* 'リ' */ 'ﾘ', 0,
        /* 'ル' */ 'ﾙ', 0,
        /* 'レ' */ 'ﾚ', 0,
        /* 'ロ' */ 'ﾛ', 0,
        /* 'ヮ' */ 'ﾜ', 0,
        /* 'ワ' */ 'ﾜ', 0,
        /* 'ヰ' */ 'ｲ', 0,
        /* 'ヱ' */ 'ｴ', 0,
        /* 'ヲ' */ 'ｦ', 0,
        /* 'ン' */ 'ﾝ', 0,
        /* 'ヴ' */ 'ｳ', 'ﾞ',        
        // 以降カタカナのみ
      /* 'ヵ' */ 'ｶ', 0,
      /* 'ヶ' */ 'ｹ', 0,
  };
}
