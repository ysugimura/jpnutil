package com.cm55.jpnutil;

import org.junit.*;
import static org.junit.Assert.*;


public class HanToZenTest {

  @Test
  public void test() {
    assertEquals("東京トッキョきょかきょくａｂｃ１２３ＤＥＦＡＢＣパパバ　ﾞ",
        HanToZen.convert("東京ﾄｯｷｮきょかきょくabc123DEFABCﾊﾟﾊﾟﾊﾞ ﾞ"));    
  }

}
