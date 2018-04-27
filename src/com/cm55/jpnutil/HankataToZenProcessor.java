package com.cm55.jpnutil;

import static com.cm55.jpnutil.Constants.*;

public abstract class HankataToZenProcessor {
  
  abstract void output(char c);
  
  // ペンド中の半角カタカナ
  private int pendingHankata;
  
  boolean processHankata(int code) {
    
    // ペンド中の半角カタカナのある場合、濁音あるいは半濁音の可能性をチェック
    if (processPending(code)) return true;

    // 半角カタカナ
    if (code < HANKATA_START || HANKATA_END < code) return false;
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
    if (pendingHankata == 0) return false;
    int index = (pendingHankata - HANKATA_START) * 3;
    char zenPlain = HANKATA_TO_ZENKATA[index + 0];
    char zenDakuon = HANKATA_TO_ZENKATA[index + 1];
    char zenHandakuon = HANKATA_TO_ZENKATA[index + 2];
    pendingHankata = 0;
    
    if (code == HANKATA_DAKUON) {
      if (zenDakuon != 0)
        output(zenDakuon);
      else {
        output(zenPlain);
        output(ZENKAKU_DAKUON);
      }
      return true; 
    }
    
    if (code == HANKATA_HANDAKUON) {
      if (zenHandakuon != 0)
        output(zenHandakuon);
      else {
        output(zenPlain);
        output(ZENKAKU_HANDAKUON);
      }
      return true; 
    }
    
    output(zenPlain);
    return false;
  }  
  
  void flush() {
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
   */
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
    /* ff9e 'ﾞ' */ ZENKAKU_DAKUON, 0, 0,
    /* ff9f 'ﾟ' */ ZENKAKU_HANDAKUON, 0, 0,
  };
}
