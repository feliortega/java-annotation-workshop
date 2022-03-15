package com.github.feliortega.type;

import java.util.function.Function;

public class Tuple<A, B> {
  public final A a;
  public final B b;

  public Tuple(A a, B b) {
    this.a = a;
    this.b = b;
  }

  public <C> Tuple<A, C> map(Function<B, C> function) {
    return new Tuple(a, function.apply(b));
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", a, b);
  }
}
