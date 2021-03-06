package com.github.feliortega.client;

import com.github.feliortega.annotation.ApiCall;

public class RestUtils {
  @ApiCall("{host}/user/{id}")
  public String getUserName(long userId) {
    return String.format("user-%s", userId);
  }

  @ApiCall("{host}/item/{id}")
  public String getItemName(long itemId) {
    return String.format("item-%s", itemId);
  }
}
