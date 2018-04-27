package com.cm55.jpnutil;

import java.util.*;

/**
 * 全角文字を半角に変換する。
 */
public class ZenToHan implements Constants {

  /** 文字列を半角へ変換 */
  public static String convert(String s) {
    final StringBuffer buf = new StringBuffer();
    new Converter(new CharConverter(null) {
      public void convert(char c) {
        buf.append(c);
      }
    }).convert(s);
    return buf.toString();
  }

  /** コンバータ */
  public static class Converter extends CharConverter {
    public Converter(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      int code = (int)c & 0xffff;
      
      // 全角ひらがな
      if (ZENHIRA_START <= code && code <= ZENHIRA_END) {
        zenhiraToHankata(code);
        return;
      }
      
      // 全角カタカナ
      if (ZENKATA_START <= code && code <= ZENKATA_END) {
        zenkataToHankata(code);
        return;
      }
      
      // 全角ANK
      if (ZENANK_START <= code && code <= ZENANK_END) {
        zenankToHanank(code);
        return;
      }
      
      // その他
      Object mapObj = MISC_MAP.get(new Character(c));
      if (mapObj != null) {
        super.convert(((Character)mapObj).charValue());
        return;
      }
      
      // 変換不能
      super.convert(c);
      return;
    }
    
    /** 全角ひらがな->半角カタカナ */
    void zenhiraToHankata(int code) {
      int index = code - ZENHIRA_START;
      char c1 = TO_HANKATA[index * 2 + 0];
      char c2 = TO_HANKATA[index * 2 + 1];
      if (c1 == 0) {
        super.convert((char)code);
        return;
      }
      if (c2 == 0) {
        super.convert(c1);
        return;
      }
      super.convert(c1);
      super.convert(c2);
    }

    /** 全角カタカナ->半角カタカナ */
    void zenkataToHankata(int code) {
      int index = code - ZENKATA_START;
      char c1 = TO_HANKATA[index * 2 + 0];
      char c2 = TO_HANKATA[index * 2 + 1];
      if (c1 == 0) {
        super.convert((char)code);
        return;
      }
      if (c2 == 0) {
        super.convert(c1);
        return;
      }
      super.convert(c1);
      super.convert(c2);
    }

    /** 全角ANK->半角ANK */
    void zenankToHanank(int code) {
      int index = code - ZENANK_START;
      if (0 <= index && index <= HANANK_COUNT) {
        super.convert((char)(index + HANANK_START));
        return;
      }
      super.convert((char)code);
    }
  }

  /////////////////////////////////////////////////////////////////////////////
  // 全角/半角変換テーブル
  /////////////////////////////////////////////////////////////////////////////

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

  static final HashMap<Character,Character> MISC_MAP;
  static {
    char[]misc = new char[] {
        '×', '*', // ×,*:d7,2a
        '’', '\'', // ’,':2019,27
        '”', '"', // ”,":201d,22
        '″', '~', // ″,~:2033,7e
        '－',  '-', // －,-:ff0d,2d
        '　', ' ', // 全角空白, :3000,20
        '、', '､', // 、,､:3001,ff64
        '。', '｡', // ゜,ﾟ:309c,ff9f
        '「', '｢', // 「,｢:300c,ff62
        '」', '｣', // 」,｣:300d,ff63
        '゛', 'ﾞ', // ゛,ﾞ:309b,ff9e
        '゜', 'ﾟ', // 。,｡:3002,ff61
        '・', '･', // ・,･:30fb,ff65
        'ー', 'ｰ', // ー,ｰ:30fc,ff70
        '￥', '\\', // ￥,\:ffe5,5c
    };
    MISC_MAP = new HashMap<Character,Character>();
    for (int i = 0; i < misc.length / 2; i++) {
      MISC_MAP.put(
        new Character(misc[i * 2 + 0]),
        new Character(misc[i * 2 + 1])
      );
    }
  };
}
