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
    String origin = new String(original);

    Ascii.HanToZen hanToZen = new Ascii.HanToZen();    
    String zenkaku = hanToZen.convert(origin);
    
    Ascii.ZenToHan zenToHan = new Ascii.ZenToHan();
    String hankaku = zenToHan.convert(zenkaku);

    assertEquals(origin, hankaku);
  }
}
