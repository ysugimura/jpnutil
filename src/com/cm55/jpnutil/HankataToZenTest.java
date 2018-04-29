package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;

public class HankataToZenTest {

  @Test
  public void tableTest() {
    assertEquals(Constants.HANKATA_COUNT, HankataToZen.HANKATA_TO_ZENKATA.length / 3);
  }

}
