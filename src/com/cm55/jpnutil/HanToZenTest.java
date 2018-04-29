package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;


public class HanToZenTest {

  @Test
  public void test() {
    assertEquals("ポカホンタス１２３", HanToZen.convert("ﾎﾟｶﾎﾝﾀｽ123"));
    assertEquals("東京　トッキョきょかきょくａｂｃ１２３ＤＥＦＡＢＣパパバ　゛", 
        HanToZen.convert("東京 ﾄｯｷｮきょかきょくabc123DEFABCﾊﾟﾊﾟﾊﾞ ﾞ"));    
  }

}
