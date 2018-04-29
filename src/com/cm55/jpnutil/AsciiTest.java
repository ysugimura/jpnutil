package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;
import static com.cm55.jpnutil.Ascii.*;

public class AsciiTest {

  
  @Test
  public void testAnkCount() {
    assertEquals(HANANK_COUNT, ZENANK_COUNT);
  }

  @Test
  public void test() {
    char c = '\uA000';
    
    int index = c - 0x6000;
    System.out.println("" + Integer.toHexString(index));
  }
}
