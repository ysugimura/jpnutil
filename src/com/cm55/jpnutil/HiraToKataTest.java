package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;

public class HiraToKataTest {

  @Test
  public void test() {
    assertEquals("ジュゲム寿限無カキクケコｶｷｸｹｺ", 
        HiraToKata.convert("じゅげむ寿限無カキクケコｶｷｸｹｺ"));
  }

}
