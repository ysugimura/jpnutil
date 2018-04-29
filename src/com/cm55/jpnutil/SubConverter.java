package com.cm55.jpnutil;

import java.util.function.*;

public abstract class SubConverter {
  Consumer<Character>consumer;
  protected SubConverter(Consumer<Character>consumer) {
    this.consumer = consumer;
  }
  
  abstract public boolean input(char c);
  
  protected final void output(char c) {
    consumer.accept(c);
  }
}
