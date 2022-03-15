package com.github.feliortega.type;

public class Tuple<A, B> {
  public final A a;
  public final B b;

  public Tuple(A a, B b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", a, b);
  }
}
