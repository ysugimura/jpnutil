package com.cm55.jpnutil;

import static org.junit.Assert.*;

import org.junit.*;

public class HankataToZenProcessorTest {

  @Test
  public void tableTest() {
    assertEquals(Constants.HANKATA_COUNT, HankataToZenProcessor.HANKATA_TO_ZENKATA.length / 3);
  }

}
