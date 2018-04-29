package com.cm55.jpnutil;

import java.util.*;
import java.util.function.*;

public class SubConverter {
  
  SubConverter[]delegates;
  Consumer<Character>consumer;
  
  protected SubConverter(SubConverter...delegates) {
    Arrays.stream(this.delegates = delegates).forEach(sc->sc.setConsumer(this::output));    
  }

  public SubConverter setConsumer(Consumer<Character>consumer) {
    this.consumer = consumer;
    return this;
  }
  
  public boolean input(char c) {
    for (SubConverter sc: delegates) {
      if (sc.input(c)) return true;
    }
    return false;
  }
  
  protected final void output(char c) {
    consumer.accept(c);
  }
  
  public void flush() {    
    for (SubConverter sc: delegates) {
      sc.flush();
    }
  }
  
  public void convert(String s) {
    for (char c: s.toCharArray()) input(c);
    flush();
  }

  public static class PassThrough extends SubConverter {
    public PassThrough() {
      super();
    }
    public boolean input(char c) {
      output(c);
      return true;
    }
  }

}
