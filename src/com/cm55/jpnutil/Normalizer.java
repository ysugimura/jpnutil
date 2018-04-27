package com.cm55.jpnutil;

import java.util.*;

/**
 * 文字列コンバータ
 * 
 * @author ysugimura
 *
 */
public class Normalizer {

  /**
   * 文字列の正規化。
   * <p>
   * 前後のスペースを削除する 半角は全角にし、カタカナはひらがなにし、英小文字は大文字にする。
   * つまり、全角・半角、カタカナ・ひらがな、大文字小文字の違いがあっても検索を可能にする。
   * 上記の変換の結果、半角空白はすべて全角半角になるが、空白の連続を除去し、 単一の空白にする。
   * </p>
   * <p>
   * 入力がnullの場合はnullを返す。
   * </p>
   * 
   * @param input
   *          半角・全角、カタカナ・ひらがな、その他の文字の混在した文字列
   * @return 全角ひらがな＋全角大文字アルファベット＋その他の全角文字
   */
  public static String normalize(String input) {
    if (input == null)
      return null;
    String converted = KataToHira.convert(HanToZen.convert(input)).toUpperCase();
    String[] splited = splitBySpace(converted);
    StringBuilder result = new StringBuilder();
    for (String s : splited) {
      if (result.length() > 0)
        result.append("\u3000");
      result.append(s);
    }
    return result.toString();
  }

  /**
   * 「全角ひらがな」を「全角カタカナ」に変換する。それ以外は変更なし
   * 
   * @param input
   *          全角ひらがな混じり文字列
   * @return 全角カタカナ混じり文字列
   */
  public static String toKatakana(String input) {
    if (input == null)
      return null;
    return HiraToKata.convert(input);
  }

  /**
   * 半角に変換する
   */
  public static String toHankaku(String input) {
    return ZenToHan.convert(input);
  }

  public static String[] splitBySpace(String input) {

    // null入力の場合はnullを返す。
    if (input == null)
      return null;

    // 結果リスト
    List<String> result = new ArrayList<String>();

    // 半角空白もしくは全角空白の１つ以上の連続で区切る
    for (String splited : input.split("[\u0020|\u3000]+")) {

      // 長さ０のものは捨てる
      if (splited.length() == 0)
        continue;

      // 結果に追加する
      result.add(splited);
    }

    // 結果を配列にして返す。
    return result.toArray(new String[0]);
  }

}
