package com.cm55.jpnutil;

import java.util.*;
import java.util.stream.*;

/**
 * 文字列の正規化
 * <p>
 * 検索インデックス作成前に文字種の違いによって検索が失敗しないよう正規化する。これには以下がある。
 * </p>
 * <ul>
 * <li>すべての文字を全角文字にする。
 * <li>すべてのアルファベットを大文字にする。
 * <li>すべてのカタカナをひらがなにする。ただし、対応するひらがなが無い場合はそのまま
 * <li>前後のスペースを除去し、途中の連続した複数のスペースは一つにする。
 * </ul>
 * @author ysugimura
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
    if (input == null) return null;
    String converted = KataToHira.convert(HanToZen.convert(input)).toUpperCase();
    List<String>splited = splitBySpace(converted);
    return splited.stream().collect(Collectors.joining("\u3000"));
  }

  public static List<String>splitBySpace(String input) {
    return Arrays.stream(input.split("[\u0020|\u3000]+"))
      .filter(s->s.length() > 0).collect(Collectors.toList());
  }
}
