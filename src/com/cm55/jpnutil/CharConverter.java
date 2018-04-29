package com.cm55.jpnutil;

import java.util.*;
import java.util.function.*;

/**
 * 文字コンバータ
 * <p>
 * 入力された文字を変換し、{@link #output(char)}に出力する。変換不能の場合は出力を行わず、falseを返す。
 * 出力の指定はあらかじめ{@link #to(Consumer)}で行っておく。
 * 内部に文字をペンドした場合、最終的にそれを吐き出すために{@link #flush()}が呼び出される。
 * </p>
 * <p>
 * ※内部で文字を保留する場合があるため、スレッドセーフにすることはできない。
 * </p>
 * @author ysugimura
 */
public abstract class CharConverter {
  
  private Consumer<Character>consumer;
 
  /** 
   * 出力先を指定する 
   * @param consumer 出力先
   * @return 本オブジェクト
   */
  public CharConverter to(Consumer<Character>consumer) {
    this.consumer = consumer;
    return this;
  }

  /**
   * 一文字を処理する。処理した場合はtrueが返される。
   * 必ずしも{@link #output(char)}で出力されるとは限らない。内部的に保留されることもある。
   * @param c 処理対象文字
   * @return true:処理を行った。false：処理対象ではない
   */
  public abstract boolean process(char c);

  /** 出力する */
  protected final void output(char c) {
    consumer.accept(c);
  }

  /** 
   * 保留文字のある場合、それをフラッシュする
   */
  public void flush() {
  }

  /**
   * 入力された文字をそのまま出力する。
   */
  public static class PassThrough extends CharConverter {
    public PassThrough() {
      super();
    }
    @Override
    public boolean process(char c) {
      output(c);
      return true;
    }
  }
  
  /**
   * 複数の{@link CharConverter}によって文字列の変換を行う。
   * @author ysugimura
   */
  public static class Cascading extends CharConverter {
    private CharConverter[]delegates;
    
    /** 処理を移譲する{@link CharConverter}を指定する */
    protected Cascading(CharConverter...delegates) {
      Arrays.stream(this.delegates = delegates).forEach(sc->sc.to(this::output)); 
    }

    /** メソッドの返り値型を変更する */
    public Cascading to(Consumer<Character>consumer) {
      super.to(consumer);
      return this;
    }
    
    /** 
     * 一文字を処理する。
     * 移譲オブジェクトのトップから処理をさせてみて、trueが返された場合にはそこで終了しtrueを返す。
     * 処理できなければfalseを返す。
     */
    public boolean process(char c) {
      for (CharConverter sc: delegates) {
        if (sc.process(c)) return true;
      }
      return false;
    }

    /**
     * 文字列を処理する
     * @param s
     */
    public void convert(String s) {
      for (char c: s.toCharArray()) process(c);
      flush();
    }    
    
    /**
     * フラッシュする。すべての移譲オブジェクトにフラッシュ指示を行う。
     */
    public void flush() {    
      for (CharConverter sc: delegates) {
        sc.flush();
      }
    }
  }
}
