package com.cm55.jpnutil;

import org.junit.*;
import static org.junit.Assert.*;

public class HiraToKataTest {

  @Test
  public void test() {
    assertEquals("ジュゲム寿限無カキクケコｶｷｸｹｺ", 
        HiraToKata.convert("じゅげむ寿限無カキクケコｶｷｸｹｺ"));
  }

}
