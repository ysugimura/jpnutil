package com.cm55.jpnutil;

/**
 * プッシュ型文字列コンバータ
 * <p>
 * 文字の変換機であるが、カスケードに接続することができる。例えば、「空白除去変換」
 * を行った後に「大文字から小文字への変換」を行いたい場合、
 * <pre>
 * CharConverter converter = new CharConverter.IgnoreWhitespaces(
 *   new CharConverter.LowerCase(null)
 * );
 * </pre>
 * などという風に、「前の変換オブジェクト」のコンストラクタ引数として、「後の
 * 変換オブジェクトのオブジェクト」を指定する。
 * </p>
 * <p>
 * 変換を行うには、先のコンバータをconverter.convert(c)という風に呼び出すのでは
 * あるが、ただし変換結果は最後に指定された変換機で受け取る必要がある。先の例では
 * nullを指定しているが、ここに結果受け取り専用のオブジェクトを指定しなくては
 * ならない。
 * </p>
 * <p>
 * 一つの変換カスケード接続をいくつもの用途に使用するためには、その度に先の
 * カスケード接続を作成して、最後に受け取り用のオブジェクトを指定する必要がある。
 * これを簡単に行うために、ファクトリオブジェクトを用意してある。
 * </p>
 * <p>
 * 要するにchain of responsibilityパターンか？
 * </p>
 */
public class CharConverter {
  
  /** 次のコンバータ */
  protected CharConverter next;

  /** 次のコンバータを指定して作成 */
  public CharConverter(CharConverter next) {
    this.next = next;
  }

  /** コンバータチェインの最後尾に指定コンバータを入れる */
  public void add(CharConverter n) { 
    if (next != null) next.add(n);
    else              next = n;
  }
      
  /** 文字列を変換する */
  public void convert(String s) {
    char[]chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++)
      convert(chars[i]);
  }
    
  /** 文字を変換する。デフォルトでは次のコンバータに変換を依頼する */
  public void convert(char c) {
    if (next != null) next.convert(c);
  }
  
  /** ペンド文字列がある場合にそれをフラッシュする */
  public void flush() {
    if (next != null) next.flush();
  }
  
  /////////////////////////////////////////////////////////////////////////////
  // ファクトリ
  
  /**
   * 変換カスケード接続を作成するためのファクトリオブジェクト
   */
  public static abstract class Factory {
  
    /** 最終コンバータを指定してカスケード接続を作成する */
    public abstract CharConverter create(CharConverter last);
    
    /** 文字列を変換するためのコンバータ */
    private CharConverter stringConverter;
    
    /** 文字列変換結果 */
    private StringBuffer stringBuffer;
    
    /** 文字列を変換する */
    public String convertString(String s) {
      if (stringConverter == null) {
        stringConverter = create(
          new CharConverter(null) {
            public void convert(char c) {
              stringBuffer.append(c);
            }
          }
        );
      }
      stringBuffer = new StringBuffer();
      stringConverter.convert(s);
      stringConverter.flush();
      String result = stringBuffer.toString();
      stringBuffer = null;
      return result;
    }
  }
  
  /////////////////////////////////////////////////////////////////////////////
  // ビルトインコンバータ
  
  /** 
   * 空白類無視コンバータ
   */
  public static class IgnoreWhitespaces extends CharConverter {
    public IgnoreWhitespaces(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      if (!Character.isWhitespace(c)) super.convert(c);
    }
  }
  
  /**
   * 空白類折り畳みコンバータ
   */
  public static class FoldWhitespaces extends CharConverter {
    boolean spaceFlag;
    public FoldWhitespaces(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      if (!Character.isWhitespace(c)) {
        super.convert(c);
        spaceFlag = false;
        return;
      }
      if (spaceFlag) return;
      super.convert(' ');
      spaceFlag = true;
    }
  }
  
  /** 
   * 大文字・小文字コンバータ
   */
  public static class LowerCase extends CharConverter {
    public LowerCase(CharConverter n) {
      super(n);
    }
    public void convert(char c) {
      super.convert(Character.toLowerCase(c));
    }
  }
}
