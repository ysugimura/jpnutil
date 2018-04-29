package com.cm55.jpnutil;

import java.util.*;

public class MiscChars {

  public static class ZenToHan extends SubConverter {

    public boolean input(char c) {
      Character converted = MISC_MAP.get(c);
      if (converted != null) {
        output(converted);
        return true;
      }
      return false;
    }
  }
  
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
        misc[i * 2 + 0],
        misc[i * 2 + 1]
      );
    }
  };

}
