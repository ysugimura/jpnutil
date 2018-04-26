package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;

public class JpnConverterTest {

  JpnConverter converter;
  
  @Before
  public void before() {
    converter = new JpnConverter();
  }
  
  @Test
  public void normalizeのテスト() {
    String s = converter.normalize("東京都 とっきょ ｷｮｶｷｮｸ123aBC");
    //ystem.out.println(s);
    assertEquals("東京都　とっきょ　きょかきょく１２３ＡＢＣ", s);
  }

  @Test
  public void normalizeのテスト2() {
    String s = converter.normalize("{}*+-=)(&'%$#!_?/><;:@");
    //ystem.out.println(s);
    assertEquals("｛｝＊＋－＝）（＆’％＄＃！＿？／＞＜；：＠", s);
  }
  
  @Test
  public void normalizeのテスト3() {
    String s = converter.normalize(" 1\u0020\u30002 ");
    assertEquals("１\u3000２", s);
  }
  
  @Test
  public void toHankakuのテスト() {
    String input = "東京都　とっきょ　きょかきょく１２３ＡＢＣー１－";
    String output = converter.toHankaku(input);
    assertEquals("東京都 ﾄｯｷｮ ｷｮｶｷｮｸ123ABCｰ1-", output);
  }
}
