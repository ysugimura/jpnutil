package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;

public class NormalizerTest {
  
  @Before
  public void before() {

  }
  
  @Test
  public void normalizeのテスト() {
    String s = Normalizer.normalize(" 東京都 　 とっきょ ｷｮｶｷｮｸ123aBC ");
    //ystem.out.println(s);
    assertEquals("東京都　とっきょ　きょかきょく１２３ＡＢＣ", s);
  }

  @Test
  public void normalizeのテスト2() {
    String o = "{}*+-=)(&'%$#!_?/><;:@";
    String s = Normalizer.normalize(o);
    //ystem.out.println(s);
    String r = "｛｝＊＋－＝）（＆\uff07％＄＃！＿？／＞＜；：＠";
    
    /*
    for (int i = 0; i < s.length(); i++) {
      System.out.println("" + s.charAt(i) + "," + Integer.toHexString(o.charAt(i)) + "," + Integer.toHexString(s.charAt(i))
        +"," + Integer.toHexString(r.charAt(i)) + "," + (s.charAt(i) == r.charAt(i)));
    }
    */
    assertEquals(r, s);
  }
  
  @Test
  public void normalizeのテスト3() {
    String s = Normalizer.normalize(" 1\u0020\u30002 ");
    assertEquals("１\u3000２", s);
  }

}
