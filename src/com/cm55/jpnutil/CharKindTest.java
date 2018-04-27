package com.cm55.jpnutil;

import static com.cm55.jpnutil.CharKind.*;
import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.*;

import org.junit.*;

public class CharKindTest {

  @Test
  public void test() {
    
    char[]chars = "漢aアｱあ１".toCharArray();
    
    assertArrayEquals(
      new EnumSet[] {
        EnumSet.of(KANJI),
        EnumSet.of(HANKAKU, ANK),
        EnumSet.of(ZENKAKU, KANA, KATAKANA),
        EnumSet.of(HANKAKU, KANA, KATAKANA),
        EnumSet.of(ZENKAKU, KANA, HIRAGANA),
        EnumSet.of(ZENKAKU, ANK)
      },
      IntStream.range(0, chars.length).mapToObj(i->CharKind.get(chars[i])).collect(Collectors.toList())
      .toArray(new EnumSet[0])
    );

    /*
    for (char c: s.toCharArray()) {
      System.out.println(c + ":" + CharKind.get(c));
    }
    */
  }
}
