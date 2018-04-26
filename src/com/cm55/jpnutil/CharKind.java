package com.cm55.jpnutil;

/**
 * 日本語文字種
 */
public class CharKind implements Constants {

  public static final int HANKAKU  = 0x0001; // 半角文字
  public static final int ZENKAKU  = 0x0002; // 全角文字
  public static final int KANA     = 0x0004; // かな・カナ
  public static final int KATAKANA = 0x0008; // カタカナ
  public static final int HIRAGANA = 0x0010; // ひらがな
  public static final int KANJI    = 0x0020; // 漢字
  public static final int ANK = 0x0040; // ANK

  /** 日本語文字種を取得する */
  public static int get(char c) {
    int code = c;

    // 全角ひらがな
    if (ZENHIRA_START <= code && code <= ZENHIRA_END)
      return ZENKAKU | HIRAGANA;

    // 全角カタカナ
    if (ZENKATA_START <= code && code <= ZENKATA_END)
      return ZENKAKU | KATAKANA;

    // 全角ANK
    if (ZENANK_START <= code && code <= ZENANK_END) {
      return ZENKAKU | ANK;
    }

    // 半角カタカナ
    if (HANKATA_START <= code && code <= HANKATA_HANDAKUON) {
      return KATAKANA | HANKAKU;
    }

    // 半角ANK
    if (code < 0x100) {
      return ANK | HANKAKU;
    }


    // 不明
    return 0;
  }

  public static void main(String[]args) {
    /*
     * [[[シットリローション１２０ＭＬ]]]
[[[サッパリローション１２０ＭＬ]]]
[[[ベビーソープ]]]
[[[ＵＶローション]]]
[[[エメロンシャンプー]]]
[[[つめかえ用３８０ＭＬ]]]
[[[エメロンコンディショナー]]]
     */
    /*
    String s = "サッパリ・ローション１２０ＭＬ";
    s = ZenToHan.convert(s);
    System.out.println("" + (char)0xff64);

    for (char c: s.toCharArray()) {
      System.out.println("" + c + ":" + Integer.toHexString(get(c)) + " " + Integer.toHexString(c));
    }
    */
    /*
    [･]
    [･]ｰー
    */
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
    for (int i = 0; i < misc.length; ) {
      System.out.println("" + Integer.toHexString(misc[i++]));
      System.out.println("  " + Integer.toHexString(misc[i++]));
    }

    for (int i = 0xff61;  i <= 0xff65; i++) {
      System.out.println("/* '" + (char)i + "' */");
    }
    char c = '?';

    System.out.println("" + Integer.toHexString(c));

    System.out.println("" + (char)0xff61 + "]");
    /*
    System.out.println("" + (char)0xff63 + "]");
    System.out.println("" + (char)0xff64 + "]");
    System.out.println("" + (char)0x30fb + "]");
    System.out.println("" + (char)0xff65 + "]");
    System.out.println("" + Integer.toHexString('ｰ'));
    System.out.println("[" + HanToZen.convert("ｰ") + "]");
    */
  }
}
