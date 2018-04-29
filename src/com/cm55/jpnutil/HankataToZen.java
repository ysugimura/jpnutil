package com.cm55.jpnutil;

/**
 * 半角カタカナを全角カタカナに変換するプロセッサ
 * <p>
 * 半角カタカナを全角カタカナに変換するが、「ﾊﾟ」といった二文字からなる濁音・半濁音付きの文字列を「パ」といった一文字の全角文字に
 * 変換する。
 * このため、濁音・半濁音の可能性のある文字についてはいったん保留しておき、次に濁音・半濁音が来たときに、複合して一文字とする。
 * </p>
 * @author ysugimura
 */
public class HankataToZen extends SubConverter {
   
  
  public static final int HANKATA_START     = 0xff61;
  
  public static final int HANKATA_DAKUON    = 0xff9e; // 半角カタカナ濁音
  public static final int HANKATA_HANDAKUON = 0xff9f; // 半角カタカナ半濁音
  
  public static final int HANKATA_END       = 0xff9f;

  public static final int HANKATA_COUNT = HANKATA_END - HANKATA_START + 1;
  
  public static final char ZENKAKU_DAKUON = '゛'; // 0x309b, 全角単一濁音
  public static final char ZENKAKU_HANDAKUON = '゜'; // 0x309c, 全角単一半濁音
  
  // ペンド中の半角カタカナ
  private int pendingHankata;
  
  
  /**
   * 一文字を処理する。処理した場合はtrueを返し、対象外で未処理の場合はfalseを返す。
   * @param code 処理する文字
   * @return true：処理済み、false:対象外
   */
  @Override
  public boolean input(char c) {
    int code = (int)c & 0xffff;
    
    // 既にペンド中の半角カタカナがある場合、濁音あるいは半濁音の可能性をチェック
    if (processPending(code)) return true;

    // ここで取り扱う半角カタカナ範囲で無い場合には処理しない
    if (code < HANKATA_START || HANKATA_END < code) return false;
    
    // HANKATA_TO_ZENKATAテーブルのインデックス
    int index = (code - HANKATA_START) * 3;

    // 濁音あるいは半濁音の可能性のある場合ペンドする。
    if (HANKATA_TO_ZENKATA[index + 1] != 0 || HANKATA_TO_ZENKATA[index + 2] != 0) {
      pendingHankata = code;
      return true;
    }

    // 濁音あるいは半濁音の可能性がない。変換して終了。
    output(HANKATA_TO_ZENKATA[index]);
    return true;
  }

  /** ペンド中の半角カタカナがあり、かつそれを処理した場合にはtrueを返す */
  boolean processPending(int code) {

    // ペンド中の半角カタカナは無い
    if (pendingHankata == 0) return false;
    
    // ペンド中の半角カタカナに対応する全角カタカナ、濁音付全角カタカナ、半濁音付全角カタカナを得る
    // もちろん存在しない場合もある
    int index = (pendingHankata - HANKATA_START) * 3;
    char pendZenPlain = HANKATA_TO_ZENKATA[index + 0];
    char pendZenDakuon = HANKATA_TO_ZENKATA[index + 1];
    char pendZenHandakuon = HANKATA_TO_ZENKATA[index + 2];
    pendingHankata = 0;

    // 今回の文字が半角濁音、あるいは全角濁音の場合
    if (code == HANKATA_DAKUON || code == ZENKAKU_DAKUON) {
      // 対応する全角濁音付カタカナあり
      if (pendZenDakuon != 0) {
        output(pendZenDakuon);
        return true;
      }
      // 対応する全角濁音付カタカナ無し
      output(pendZenPlain);
      output(ZENKAKU_DAKUON);      
      return true; 
    }

    // 今回の文字が半角半濁音、あるいは全角半濁音の場合
    if (code == HANKATA_HANDAKUON || code == ZENKAKU_HANDAKUON) {
      // 対応する全角半濁音付カタカナあり
      if (pendZenHandakuon != 0) {       
        output(pendZenHandakuon);
        return true;
      }
      // 対応する全角半濁音付カタカナ無し
      output(pendZenPlain);
      output(ZENKAKU_HANDAKUON);      
      return true; 
    }
    
    // 濁音・半濁音無しの全角カタカナを出力し、今回の文字は未処理とする。
    output(pendZenPlain);
    
    return false;
  }  
  
  /**
   * ペンドされている文字をフラッシュする
   */
  @Override
  public void flush() {
    if (pendingHankata != 0) {
      int index = (pendingHankata - HANKATA_START) * 3;
      pendingHankata = 0;
      output(HANKATA_TO_ZENKATA[index]);
    }
  }
  
  /**
   * 半角カタカナ->全角カタカナ変換テーブル
   * 0:濁音半濁音の無い場合
   * 1:濁音のある場合
   * 2:半濁音のある場合
   * いずれの場合も0の場合は対象文字が存在しない。
   */
  static final char[]HANKATA_TO_ZENKATA = new char[] {
    // ここは正確にHANKATA_STARTの位置
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
    /* ff9e 'ﾞ' */ ZENKAKU_DAKUON, 0, 0,
    /* ff9f 'ﾟ' */ ZENKAKU_HANDAKUON, 0, 0,
    // ここは正確にHANKATA_ENDの位置
  };
}
