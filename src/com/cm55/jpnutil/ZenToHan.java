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

  public static class Converter extends CharConverter {
    public Converter(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      //System.out.println("ZenToHan.Converter " + c);
      int code = (int)c & 0xffff;
      if (ZENHIRA_START <= code && code <= ZENHIRA_END) {
        int index = code - ZENHIRA_START;
        char c1 = zenhira2hankata[index * 2 + 0];
        char c2 = zenhira2hankata[index * 2 + 1];
        if (c1 == 0) {
          //return new char[] { c };
          super.convert(c);
          return;
        }
        if (c2 == 0) {
          //return new char[] { c1 };
          super.convert(c1);
          return;
        }
        //return new char[] { c1, c2 };
        super.convert(c1);
        super.convert(c2);
        return;
      }
      if (ZENKATA_START <= code && code <= ZENKATA_END) {
        int index = code - ZENKATA_START;
        char c1 = zenkata2hankata[index * 2 + 0];
        char c2 = zenkata2hankata[index * 2 + 1];
        if (c1 == 0) {
          //return new char[] { c };
          super.convert(c);
          return;
        }
        if (c2 == 0) {
          //return new char[] { c1 };
          super.convert(c1);
          return;
        }
        //return new char[] { c1, c2 };
        super.convert(c1);
        super.convert(c2);
        return;
      }
      if (ZENANK_START <= code && code <= ZENANK_END) {
        int index = code - ZENANK_START;
        char c1 = zenank2hanank[index];
        if (c1 != 0) {
          //return new char[] { c1 };
          super.convert(c1);
          return;
        }
        //return new char[] { c };
        super.convert(c);
        return;
      }
      Object mapObj = miscMap.get(new Character(c));
      if (mapObj != null) {
        //return new char[] { ((Character)mapObj).charValue() };
        super.convert(((Character)mapObj).charValue());
        return;
      }
      //return new char[] { c };
      super.convert(c);
      return;
    }
  }

  /////////////////////////////////////////////////////////////////////////////
  // 全角/半角変換テーブル
  /////////////////////////////////////////////////////////////////////////////


  static final char[]zenhira2hankata = new char[] {
        /* 'ぁ' */ 'ｧ', 0,  // 0x3041
        /* 'あ' */ 'ｱ', 0,
        /* 'ぃ' */ 'ｨ', 0,
        /* 'い' */ 'ｲ', 0,
        /* 'ぅ' */ 'ｩ', 0,
        /* 'う' */ 'ｳ', 0,
        /* 'ぇ' */ 'ｪ', 0,
        /* 'え' */ 'ｴ', 0,
        /* 'ぉ' */ 'ｫ', 0,
        /* 'お' */ 'ｵ', 0,
        /* 'か' */ 'ｶ', 0,
        /* 'が' */ 'ｶ', 'ﾞ',
        /* 'き' */ 'ｷ', 0,
        /* 'ぎ' */ 'ｷ', 'ﾞ',
        /* 'く' */ 'ｸ', 0,
        /* 'ぐ' */ 'ｸ', 'ﾞ',
        /* 'け' */ 'ｹ', 0,
        /* 'げ' */ 'ｹ', 'ﾞ',
        /* 'こ' */ 'ｺ', 0,
        /* 'ご' */ 'ｺ', 'ﾞ',
        /* 'さ' */ 'ｻ', 0,
        /* 'ざ' */ 'ｻ', 'ﾞ',
        /* 'し' */ 'ｼ', 0,
        /* 'じ' */ 'ｼ', 'ﾞ',
        /* 'す' */ 'ｽ', 0,
        /* 'ず' */ 'ｽ', 'ﾞ',
        /* 'せ' */ 'ｾ', 0,
        /* 'ぜ' */ 'ｾ', 'ﾞ',
        /* 'そ' */ 'ｿ', 0,
        /* 'ぞ' */ 'ｿ', 'ﾞ',
        /* 'た' */ 'ﾀ', 0,
        /* 'だ' */ 'ﾀ', 'ﾞ',
        /* 'ち' */ 'ﾁ', 0,
        /* 'ぢ' */ 'ﾁ', 'ﾞ',
        /* 'っ' */ 'ｯ', 0,
        /* 'つ' */ 'ﾂ', 0,
        /* 'づ' */ 'ﾂ', 'ﾞ',
        /* 'て' */ 'ﾃ', 0,
        /* 'で' */ 'ﾃ', 'ﾞ',
        /* 'と' */ 'ﾄ', 0,
        /* 'ど' */ 'ﾄ', 'ﾞ',
        /* 'な' */ 'ﾅ', 0,
        /* 'に' */ 'ﾆ', 0,
        /* 'ぬ' */ 'ﾇ', 0,
        /* 'ね' */ 'ﾈ', 0,
        /* 'の' */ 'ﾉ', 0,
        /* 'は' */ 'ﾊ', 0,
        /* 'ば' */ 'ﾊ', 'ﾞ',
        /* 'ぱ' */ 'ﾊ', 'ﾟ',
        /* 'ひ' */ 'ﾋ', 0,
        /* 'び' */ 'ﾋ', 'ﾞ',
        /* 'ぴ' */ 'ﾋ', 'ﾟ',
        /* 'ふ' */ 'ﾌ', 0,
        /* 'ぶ' */ 'ﾌ', 'ﾞ',
        /* 'ぷ' */ 'ﾌ', 'ﾟ',
        /* 'へ' */ 'ﾍ', 0,
        /* 'べ' */ 'ﾍ', 'ﾞ',
        /* 'ぺ' */ 'ﾍ', 'ﾟ',
        /* 'ほ' */ 'ﾎ', 0,
        /* 'ぼ' */ 'ﾎ', 'ﾞ',
        /* 'ぽ' */ 'ﾎ', 'ﾟ',
        /* 'ま' */ 'ﾏ', 0,
        /* 'み' */ 'ﾐ', 0,
        /* 'む' */ 'ﾑ', 0,
        /* 'め' */ 'ﾒ', 0,
        /* 'も' */ 'ﾓ', 0,
        /* 'ゃ' */ 'ｬ', 0,
        /* 'や' */ 'ﾔ', 0,
        /* 'ゅ' */ 'ｭ', 0,
        /* 'ゆ' */ 'ﾕ', 0,
        /* 'ょ' */ 'ｮ', 0,
        /* 'よ' */ 'ﾖ', 0,
        /* 'ら' */ 'ﾗ', 0,
        /* 'り' */ 'ﾘ', 0,
        /* 'る' */ 'ﾙ', 0,
        /* 'れ' */ 'ﾚ', 0,
        /* 'ろ' */ 'ﾛ', 0,
        /* 'ゎ' */ 'ﾜ', 0,
        /* 'わ' */ 'ﾜ', 0,
        /* 'ゐ' */ 'ｲ', 0,
        /* 'ゑ' */ 'ｴ', 0,
        /* 'を' */ 'ｦ', 0,
        /* 'ん' */ 'ﾝ', 0,
  };

  /** 全角カタカナ */

  static final char zenkata2hankata[] = new char[] {
              /* 'ァ' */ 'ｧ', 0,  // 0x30a1
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
        /* 'ヵ' */ 'ｶ', 0,
        /* 'ヶ' */ 'ｹ', 0,
  };

  private static final char[]zenank2hanank = new char[] {
        /* '！' */ '!',
        0,
        /* '＃' */ '#',
        /* '＄' */ '$',
        /* '％' */ '%',
        /* '＆' */ '&',
        0,
        //0,
        /* '（' */ '(',
        /* '）' */ ')',
        0,
        //0,
        /* '＋' */ '+',
        /* '，' */ ',',
        /* '\uFF0D' */ '-',
        /* '．' */ '.',
        /* '／' */ '/',

        /* '０' */ '0', // 0xff10
        /* '１' */ '1',
        /* '２' */ '2',
        /* '３' */ '3',
        /* '４' */ '4',
        /* '５' */ '5',
        /* '６' */ '6',
        /* '７' */ '7',
        /* '８' */ '8',
        /* '９' */ '9',
        /* '：' */ ':',
        /* '；' */ ';',
        /* '＜' */ '<',
        /* '＝' */ '=',
        /* '＞' */ '>',
        /* '？' */ '?',
        /* '＠' */ '@',
        /* 'Ａ' */ 'A',
        /* 'Ｂ' */ 'B',
        /* 'Ｃ' */ 'C',
        /* 'Ｄ' */ 'D',
        /* 'Ｅ' */ 'E',
        /* 'Ｆ' */ 'F',
        /* 'Ｇ' */ 'G',
        /* 'Ｈ' */ 'H',
        /* 'Ｉ' */ 'I',
        /* 'Ｊ' */ 'J',
        /* 'Ｋ' */ 'K',
        /* 'Ｌ' */ 'L',
        /* 'Ｍ' */ 'M',
        /* 'Ｎ' */ 'N',
        /* 'Ｏ' */ 'O',
        /* 'Ｐ' */ 'P',
        /* 'Ｑ' */ 'Q',
        /* 'Ｒ' */ 'R',
        /* 'Ｓ' */ 'S',
        /* 'Ｔ' */ 'T',
        /* 'Ｕ' */ 'U',
        /* 'Ｖ' */ 'V',
        /* 'Ｗ' */ 'W',
        /* 'Ｘ' */ 'X',
        /* 'Ｙ' */ 'Y',
        /* 'Ｚ' */ 'Z',
        /* '［' */ '[',
        0,
        /* '］' */ ']',
        /* '＾' */ '^',
        /* '＿' */ '_',
        /* '｀' */ '`',

        /* 'ａ' */ 'a',
        /* 'ｂ' */ 'b',
        /* 'ｃ' */ 'c',
        /* 'ｄ' */ 'd',
        /* 'ｅ' */ 'e',
        /* 'ｆ' */ 'f',
        /* 'ｇ' */ 'g',
        /* 'ｈ' */ 'h',
        /* 'ｉ' */ 'i',
        /* 'ｊ' */ 'j',
        /* 'ｋ' */ 'k',
        /* 'ｌ' */ 'l',
        /* 'ｍ' */ 'm',
        /* 'ｎ' */ 'n',
        /* 'ｏ' */ 'o',
        /* 'ｐ' */ 'p',
        /* 'ｑ' */ 'q',
        /* 'ｒ' */ 'r',
        /* 'ｓ' */ 's',
        /* 'ｔ' */ 't',
        /* 'ｕ' */ 'u',
        /* 'ｖ' */ 'v',
        /* 'ｗ' */ 'w',
        /* 'ｘ' */ 'x',
        /* 'ｙ' */ 'y',
        /* 'ｚ' */ 'z',
        /* '｛' */ '{',
        /* '｜' */ '|',
        /* '｝' */ '}', // 0xff5d

  };

  private static final HashMap<Character,Character> miscMap;
  static {
    char[]misc = new char[] {
        '×', '*',
        '’', '\'',
        '”', '"',
        '″', '~',
        '－',  '-',
        '　', ' ',
        '、', '､',
        '。', '｡',
        '「', '｢',
        '」', '｣',
        '゛', 'ﾞ',
        '゜', 'ﾟ',
        '・', '･',
        'ー', 'ｰ',
        '￥', '\\',
    };
    miscMap = new HashMap<Character,Character>();
    for (int i = 0; i < misc.length / 2; i++) {
      miscMap.put(
        new Character(misc[i * 2 + 0]),
        new Character(misc[i * 2 + 1])
      );
    }
  };

  public static void main(String[]args) {

    int code;
    String text = "（）";

    System.out.println("" + convert(text));
    for (int i = 0; i < text.length(); i++) {
      System.out.println("" + Integer.toHexString(text.charAt(i)));
    }
    System.out.println("" + (char)0xff07);
    System.out.println("" + (char)0xff0a);

    /*
    System.out.println("全角ひらがな/半角カタカナ変換テーブル");
    code = ZENHIRA_START;
    for (int i = 0; i < zenhira2hankata.length / 2; i++, code++) {
      char c1 = zenhira2hankata[i * 2];
      char c2 = zenhira2hankata[i * 2 + 1];
      System.out.print(
        "zen: " + (char)code + "(" + Integer.toHexString(code) + ") ");
      if (c2 != 0)
        System.out.println("han: " + c1 + c2);
      else
        System.out.println("han: " + c1);
    }

    System.out.println("全角カタカナ/半角カタカナ変換テーブル");
    code = ZENKATA_START;
    for (int i = 0; i < zenkata2hankata.length / 2; i++, code++) {
      char c1 = zenkata2hankata[i * 2];
      char c2 = zenkata2hankata[i * 2 + 1];
      System.out.print(
        "zen: " + (char)code + "(" + Integer.toHexString(code) + ") ");
      if (c2 != 0)
        System.out.println("han: " + c1 + c2);
      else
        System.out.println("han: " + c1);
    }


    System.out.println("全角ANK/半角ANK変換テーブル");
    code = ZENANK_START;
    for (int i = 0; i < zenank2hanank.length; i++, code++) {
      char c = zenank2hanank[i];
      if (c == 0) continue;
      System.out.print(
        "zen: " + (char)code + "(" + Integer.toHexString(code) + ") ");
      System.out.println(
        "han: " + c + "(" + Integer.toHexString(c) + ")");
    }
    */
    /*
    System.out.println("その他半角変換テーブル");
    for (Iterator it = miscMap.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry e = (Map.Entry)it.next();
      Character key = (Character)e.getKey();
      Character value = (Character)e.getValue();
      System.out.println(
        "zen: " + key.charValue() + " han: " + value.charValue() + " (" + Integer.toHexString(value.charValue()) + ")"
      );
    }
    */

    /*
    Slot[]slots = new Slot[zenank2hanank.length];
    code = ZENANK_START;
    for (int i = 0; i < zenank2hanank.length; i++, code++) {
      slots[i] = new Slot(zenank2hanank[i], (char)code);
    }
    Arrays.sort(slots, new Comparator() {
      public int compare(Object o1, Object o2) {
        Slot s1 = (Slot)o1;
        Slot s2 = (Slot)o2;
        return (int)s1.han - (int)s2.han;
      }
    });
    for (int i = 0; i < slots.length; i++) {
      Slot slot = slots[i];
      if (slot.han == 0) continue;
      System.out.println("    /* " + Integer.toHexString(slot.han) + " '" + slot.han + "' *" + "/ "
        + "'" + slot.zen + "',");
    }
    */

    /*
    for (int i = 0; i < 0x10000; i++) {
      if (Character.isWhitespace((char)i))
        System.out.println("" + Integer.toHexString(i) + " " + (char)i);
    }
    */
  }

  private static class Slot {
    char han;
    char zen;
    private Slot(char han, char zen) {
      this.han = han;
      this.zen = zen;
    }
  }
}
