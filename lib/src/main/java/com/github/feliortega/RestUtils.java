package com.github.feliortega;

public class RestUtils {
  @ApiCall("{ml}/user/{id}")
  public String getUserName(long userId) {
    return String.format("user-%s", userId);
  }

  @ApiCall("{ml}/item/{id}")
  public String getItemName(long itemId) {
    return String.format("item-%s", itemId);
  }
}
