package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;

public class KataToHiraTest {

  @Test
  public void test() {
    assertEquals("じゅげむ寿限無かきくけこｶｷｸｹｺ",
        KataToHira.convert("ジュゲム寿限無カキクケコｶｷｸｹｺ"));
  }
}
