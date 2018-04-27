package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;

public class ZenToHanTest {
  @Test
  public void test() {
    assertEquals("東京ﾄｯｷｮｷｮｶｷｮｸabc123DEFABCﾊﾟﾊﾟﾊﾞ ﾞ",
        ZenToHan.convert("東京トッキョきょかきょくａｂｃ１２３ＤＥＦＡＢＣパパバ　ﾞ"));    
  }

}
