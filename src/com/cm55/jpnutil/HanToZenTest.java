package com.cm55.jpnutil;

import org.junit.*;
import static org.junit.Assert.*;


public class HanToZenTest {

  @Test
  public void test() {
    String s = HanToZen.convert("東京 ﾄｯｷｮきょかきょくabc123DEFABCﾊﾟﾊﾟﾊﾞ ﾞ");
    assertEquals("東京　トッキョきょかきょくａｂｃ１２３ＤＥＦＡＢＣパパバ　゛", s);    
  }

  @Test
  public void tableTest() {
    assertEquals(Constants.HANKATA_COUNT, HanToZen.HANKATA_TO_ZENKATA.length / 3);
  }
}
