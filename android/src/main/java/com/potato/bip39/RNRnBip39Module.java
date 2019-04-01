
package com.potato.bip39;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNRnBip39Module extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNRnBip39Module(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNRnBip39";
  }
}