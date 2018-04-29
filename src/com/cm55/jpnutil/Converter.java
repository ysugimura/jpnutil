package com.cm55.jpnutil;

/**
 * 文字コンバータ
 * @author ysugimura
 */
public abstract class Converter {
  
  public abstract String convert(String s);
  
  public interface CharOutput {
    public void accept(char c);
  }
  
  public static abstract class PerChar extends Converter {

    @Override
    public String convert(String s) {
      StringBuilder buf = new StringBuilder();
      for (char ch: s.toCharArray()) {
        process(c->buf.append(c), ch);
      }
      return buf.toString();
    }  
    
    /**
     * 一文字を処理する。処理した場合はtrueが返される。
     * 必ずしも{@link #output(char)}で出力されるとは限らない。内部的に保留されることもある。
     * @param output 出力先
     * @param c 処理対象文字
     * @return true:処理を行った。false：処理対象ではない
     */
    public abstract void process(CharOutput output, char c);  
  }
  
  /**
   * 複数の{@link Converter}によって文字列の変換を行う。
   * @author ysugimura
   */
  public static class Cascading extends Converter {
    private Converter[]delegates;
    
    /** 処理を移譲する{@link Converter}を指定する */
    protected Cascading(Converter...delegates) {
      this.delegates = delegates;      
    }
    
    /** 
     * 一文字を処理する。
     * 移譲オブジェクトのトップから処理をさせてみて、trueが返された場合にはそこで終了しtrueを返す。
     * 処理できなければfalseを返す。
     */
    @Override
    public String convert(String s) {
      for (Converter d: delegates) {
        s = d.convert(s);
      }
      return s;
    }

  }
}
