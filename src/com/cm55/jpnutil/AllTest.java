package com.cm55.jpnutil;

import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class) 
@SuiteClasses( { 
  HanToZenTest.class,
  HiraToKataTest.class,
  KataToHiraTest.class,
  SJISUtilTest.class,
  ZenToHanTest.class,
})
public class AllTest {
  public static void main(String[] args) {
    JUnitCore.main(AllTest.class.getName());
  }
}