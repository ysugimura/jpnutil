package com.cm55.jpnutil;

/**
 * 半角文字を全角文字に変換する。
 */
public class HanToZen implements Constants {

  /** 文字列を全角へ変換 */
  public static String convert(String s) {
    final StringBuffer buf = new StringBuffer();
    Converter converter = new Converter(new CharConverter(null) {
      public void convert(char c) {
        buf.append(c);
      }
    });
    converter.convert(s);
    converter.flush();
    return buf.toString();
  }

  /** 半角-->全角コンバータ */
  public static class Converter extends CharConverter {

    // ペンド中の半角カタカナ
    private char pendingHankata;

    public Converter(CharConverter n) {
      super(n);
    }

    public void convert(char c) {

      int code = (int)c & 0xffff;

      // ペンド中の半角カタカナのある場合、濁音あるいは半濁音の可能性をチェック
      if (pendingHankata != 0) {
        int index = ((int)pendingHankata - HANKATA_START) * 3;
        char plain = HANKATA_TO_ZENKATA[index + 0];
        char dakuon = HANKATA_TO_ZENKATA[index + 1];
        char handakuon = HANKATA_TO_ZENKATA[index + 2];
        pendingHankata = 0;
        if (code == HANKATA_DAKUON) {
          if (dakuon != 0)
            super.convert(dakuon);
          else {
            super.convert(plain);
            super.convert(ZEN_DAKUON);
          }
          return; /* RETURN! */
        } else if (code == HANKATA_HANDAKUON) {
          if (handakuon != 0)
            super.convert(handakuon);
          else {
            super.convert(plain);
            super.convert(ZEN_HANDAKUON);
          }
          return; /* RETURN! */
        } else {
          super.convert(plain);
          /* FALL THROUGH! */
        }
      }

      // 半角カタカナ
      if (HANKATA_START <= code && code <= HANKATA_END) {
        int index = (code - HANKATA_START) * 3;

        // 濁音あるいは半濁音の可能性のある場合ペンドする。
        if (HANKATA_TO_ZENKATA[index + 1] != 0 || HANKATA_TO_ZENKATA[index + 2] != 0) {
          pendingHankata = c;
          return;
        }

        // 濁音あるいは半濁音の可能性がない。変換して終了。
        super.convert(HANKATA_TO_ZENKATA[index]);
        return;
      }

      // ANK
      if (HANANK_START <= code && code <= HANANK_END) {
        int index = code - HANANK_START;
        super.convert(HANANK_TO_ZENANK[index]);
        return;
      }

      super.convert(c);
    }

    /** ペンド文字をフラッシュする */
    public void flush() {
      //System.out.println("flushing hanToZen");
      if (pendingHankata != 0) {
        int index = ((int)pendingHankata - HANKATA_START) * 3;
        pendingHankata = 0;
        super.convert(HANKATA_TO_ZENKATA[index]);
      }
      super.flush();
    }
  }


  static final char[]HANKATA_TO_ZENKATA = new char[] {
    /* ff61 '｡' */ '。', 0, 0,
    /* ff62 '｢' */ '「', 0, 0,
    /* ff63 '｣' */ '」', 0, 0,
    /* ff64 '､' */ '、', 0, 0,
    /* ff65 '･' */ '・', 0, 0,
    /* ff66 'ｦ' */ 'ヲ', 0, 0,
    /* ff67 'ｧ' */ 'ァ', 0, 0,
    /* ff68 'ｨ' */ 'ィ', 0, 0,
    /* ff69 'ｩ' */ 'ゥ', 0, 0,
    /* ff6a 'ｪ' */ 'ェ', 0, 0,
    /* ff6b 'ｫ' */ 'ォ', 0, 0,
    /* ff6c 'ｬ' */ 'ャ', 0, 0,
    /* ff6d 'ｭ' */ 'ュ', 0, 0,
    /* ff6e 'ｮ' */ 'ョ', 0, 0,
    /* ff6f 'ｯ' */ 'ッ', 0, 0,
    /* ff70 'ｰ' */ 'ー', 0, 0,
    /* ff71 'ｱ' */ 'ア', 0, 0,
    /* ff72 'ｲ' */ 'イ', 0, 0,
    /* ff73 'ｳ' */ 'ウ', 0, 0,
    /* ff74 'ｴ' */ 'エ', 0, 0,
    /* ff75 'ｵ' */ 'オ', 0, 0,
    /* ff76 'ｶ' */ 'カ', 'ガ', 0,
    /* ff77 'ｷ' */ 'キ', 'ギ', 0,
    /* ff78 'ｸ' */ 'ク', 'グ', 0,
    /* ff79 'ｹ' */ 'ケ', 'ゲ', 0,
    /* ff7a 'ｺ' */ 'コ', 'ゴ', 0,
    /* ff7b 'ｻ' */ 'サ', 'ザ', 0,
    /* ff7c 'ｼ' */ 'シ', 'ジ', 0,
    /* ff7d 'ｽ' */ 'ス', 'ズ', 0,
    /* ff7e 'ｾ' */ 'セ', 'ゼ', 0,
    /* ff7f 'ｿ' */ 'ソ', 'ゾ', 0,
    /* ff80 'ﾀ' */ 'タ', 'ダ', 0,
    /* ff81 'ﾁ' */ 'チ', 'ヂ', 0,
    /* ff82 'ﾂ' */ 'ツ', 'ヅ', 0,
    /* ff83 'ﾃ' */ 'テ', 'デ', 0,
    /* ff84 'ﾄ' */ 'ト', 'ド', 0,
    /* ff85 'ﾅ' */ 'ナ', 0, 0,
    /* ff86 'ﾆ' */ 'ニ', 0, 0,
    /* ff87 'ﾇ' */ 'ヌ', 0, 0,
    /* ff88 'ﾈ' */ 'ネ', 0, 0,
    /* ff89 'ﾉ' */ 'ノ', 0, 0,
    /* ff8a 'ﾊ' */ 'ハ', 'バ', 'パ',
    /* ff8b 'ﾋ' */ 'ヒ', 'ビ', 'ピ',
    /* ff8c 'ﾌ' */ 'フ', 'ブ', 'プ',
    /* ff8d 'ﾍ' */ 'ヘ', 'ベ', 'ペ',
    /* ff8e 'ﾎ' */ 'ホ', 'ボ', 'ポ',
    /* ff8f 'ﾏ' */ 'マ', 0, 0,
    /* ff90 'ﾐ' */ 'ミ', 0, 0,
    /* ff91 'ﾑ' */ 'ム', 0, 0,
    /* ff92 'ﾒ' */ 'メ', 0, 0,
    /* ff93 'ﾓ' */ 'モ', 0, 0,
    /* ff94 'ﾔ' */ 'ヤ', 0, 0,
    /* ff95 'ﾕ' */ 'ユ', 0, 0,
    /* ff96 'ﾖ' */ 'ヨ', 0, 0,
    /* ff97 'ﾗ' */ 'ラ', 0, 0,
    /* ff98 'ﾘ' */ 'リ', 0, 0,
    /* ff99 'ﾙ' */ 'ル', 0, 0,
    /* ff9a 'ﾚ' */ 'レ', 0, 0,
    /* ff9b 'ﾛ' */ 'ロ', 0, 0,
    /* ff9c 'ﾜ' */ 'ワ', 0, 0,
    /* ff9d 'ﾝ' */ 'ン', 0, 0,
  };

  static final char ZEN_DAKUON = '゛'; // 0x309b, 全角単一濁音
  static final char ZEN_HANDAKUON = '゜'; // 0x309c, 全角単一半濁音

  static final int HANANK_START = 0x20;
  static final int HANANK_END = 0x7E;
  static char[]HANANK_TO_ZENANK = new char[] {
    /* 20 ' ' */ '　',
    /* 21 '!' */ '！',
    /* 22 '"' */ '”',
    /* 23 '#' */ '＃',
    /* 24 '$' */ '＄',
    /* 25 '%' */ '％',
    /* 26 '&' */ '＆',
    /* 27 ''' */ '’',
    /* 28 '(' */ '（',
    /* 29 ')' */ '）',
    /* 2a '*' */ '＊',
    /* 2b '+' */ '＋',
    /* 2c ',' */ '，',
    /* 2d '-' */ '\uFF0D',
    /* 2e '.' */ '．',
    /* 2f '/' */ '／',
    /* 30 '0' */ '０',
    /* 31 '1' */ '１',
    /* 32 '2' */ '２',
    /* 33 '3' */ '３',
    /* 34 '4' */ '４',
    /* 35 '5' */ '５',
    /* 36 '6' */ '６',
    /* 37 '7' */ '７',
    /* 38 '8' */ '８',
    /* 39 '9' */ '９',
    /* 3a ':' */ '：',
    /* 3b ';' */ '；',
    /* 3c '<' */ '＜',
    /* 3d '=' */ '＝',
    /* 3e '>' */ '＞',
    /* 3f '?' */ '？',
    /* 40 '@' */ '＠',
    /* 41 'A' */ 'Ａ',
    /* 42 'B' */ 'Ｂ',
    /* 43 'C' */ 'Ｃ',
    /* 44 'D' */ 'Ｄ',
    /* 45 'E' */ 'Ｅ',
    /* 46 'F' */ 'Ｆ',
    /* 47 'G' */ 'Ｇ',
    /* 48 'H' */ 'Ｈ',
    /* 49 'I' */ 'Ｉ',
    /* 4a 'J' */ 'Ｊ',
    /* 4b 'K' */ 'Ｋ',
    /* 4c 'L' */ 'Ｌ',
    /* 4d 'M' */ 'Ｍ',
    /* 4e 'N' */ 'Ｎ',
    /* 4f 'O' */ 'Ｏ',
    /* 50 'P' */ 'Ｐ',
    /* 51 'Q' */ 'Ｑ',
    /* 52 'R' */ 'Ｒ',
    /* 53 'S' */ 'Ｓ',
    /* 54 'T' */ 'Ｔ',
    /* 55 'U' */ 'Ｕ',
    /* 56 'V' */ 'Ｖ',
    /* 57 'W' */ 'Ｗ',
    /* 58 'X' */ 'Ｘ',
    /* 59 'Y' */ 'Ｙ',
    /* 5a 'Z' */ 'Ｚ',
    /* 5b '[' */ '［',
    /* 5c '\\' */ '￥',
    /* 5d ']' */ '］',
    /* 5e '^' */ '＾',
    /* 5f '_' */ '＿',
    /* 60 '`' */ '｀',
    /* 61 'a' */ 'ａ',
    /* 62 'b' */ 'ｂ',
    /* 63 'c' */ 'ｃ',
    /* 64 'd' */ 'ｄ',
    /* 65 'e' */ 'ｅ',
    /* 66 'f' */ 'ｆ',
    /* 67 'g' */ 'ｇ',
    /* 68 'h' */ 'ｈ',
    /* 69 'i' */ 'ｉ',
    /* 6a 'j' */ 'ｊ',
    /* 6b 'k' */ 'ｋ',
    /* 6c 'l' */ 'ｌ',
    /* 6d 'm' */ 'ｍ',
    /* 6e 'n' */ 'ｎ',
    /* 6f 'o' */ 'ｏ',
    /* 70 'p' */ 'ｐ',
    /* 71 'q' */ 'ｑ',
    /* 72 'r' */ 'ｒ',
    /* 73 's' */ 'ｓ',
    /* 74 't' */ 'ｔ',
    /* 75 'u' */ 'ｕ',
    /* 76 'v' */ 'ｖ',
    /* 77 'w' */ 'ｗ',
    /* 78 'x' */ 'ｘ',
    /* 79 'y' */ 'ｙ',
    /* 7a 'z' */ 'ｚ',
    /* 7b '{' */ '｛',
    /* 7c '|' */ '｜',
    /* 7d '}' */ '｝',
    /* 7e '~' */ '～',
  };
}
