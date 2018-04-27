package com.cm55.jpnutil;

import org.junit.*;

public class CharKindTest {

  @Test
  public void test() {
    String s = "漢aアｱあ１";
    for (char c: s.toCharArray()) {
      System.out.println(c + ":" + CharKind.get(c));
    }
  }
}
