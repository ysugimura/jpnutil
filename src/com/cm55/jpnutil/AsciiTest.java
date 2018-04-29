package com.cm55.jpnutil;

import static com.cm55.jpnutil.Ascii.*;
import static org.junit.Assert.*;

import java.util.stream.*;

import org.junit.*;

public class AsciiTest {

  
  @Test
  public void testAnkCount() {
    assertEquals(HANANK_COUNT, ZENANK_COUNT);
  }

  @Test
  public void test() {
    char[]original = new char[0x7f - 0x20];
    IntStream.range(0x20, 0x7f).forEach(c-> { 
      original[c - 0x20] = (char)c; 
   });

    char[]zenkaku;
    {
      StringBuffer zenkakuBuf = new StringBuffer();
      Ascii.HanToZen hanToZen = new Ascii.HanToZen();    
      hanToZen.to(c->zenkakuBuf.append(c));
      for (char c: original) {
        boolean r = hanToZen.process(c);
        assertTrue(r);
      }
      zenkaku = zenkakuBuf.toString().toCharArray();
    }
    
    char[]hankaku;
    {
      StringBuffer hankakuBuf = new StringBuffer();
      Ascii.ZenToHan zenToHan = new Ascii.ZenToHan();
      zenToHan.to(c->hankakuBuf.append(c));
      for (char c: zenkaku) {
        boolean r = zenToHan.process(c);        
        assertTrue(r);
      }
      hankaku = hankakuBuf.toString().toCharArray();
    }

    assertArrayEquals(original, hankaku);
  }
}
