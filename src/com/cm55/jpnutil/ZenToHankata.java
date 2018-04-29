package com.cm55.jpnutil;

import static com.cm55.jpnutil.Constants.*;

import java.util.function.*;

/**
 * 全角ひらがな・カタカナから半角カタカナへの変換
 * @author ysugimura
 */
public class ZenToHankata {

  public static class FromHira extends SubConverter {
    FromHira(Consumer<Character>c) {
      super(c);
    }
    
    public boolean input(char c) {
      int index = c - ZENHIRA_START;
      if (index < 0 || ZENHIRA_COUNT <= index) return false;
      char c1 = TO_HANKATA[index * 2 + 0];
      char c2 = TO_HANKATA[index * 2 + 1];
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

  public static class FromKata extends SubConverter {
    FromKata(Consumer<Character>c) {
      super(c);
    }
    
    public boolean input(char c) {
      int index = c - ZENKATA_START;
      if (index < 0 || ZENKATA_COUNT <= index) return false;
      char c1 = TO_HANKATA[index * 2 + 0];
      char c2 = TO_HANKATA[index * 2 + 1];
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
  static final char TO_HANKATA[] = new char[] {
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
