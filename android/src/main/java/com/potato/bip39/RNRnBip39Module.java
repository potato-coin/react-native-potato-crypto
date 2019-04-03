
package com.potato.bip39;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nonnull;

public class RNRnBip39Module extends ReactContextBaseJavaModule {

  static {
    System.loadLibrary("native-lib");
  }

  public static native Object bip39Generate(String language, int entropy);
  public static native String[] bip39GetLanguages();
  public static native String[] bip39GetWordsFromLanguage(String language);
  public static native Object bip39EncodeBytes(ByteBuffer input, String language, int entropy);
  public static native boolean bip39ValidateMnemonic(String mnemonic, String language);
  public static native byte[] bip39WordsToSeed(String mnemonic);
  public static native String bip39WordsToSeedHex(String mnemonic);

  public static final int ENTROPY_LEN_128 = 16;
  public static final int ENTROPY_LEN_160 = 20;
  public static final int ENTROPY_LEN_192 = 24;
  public static final int ENTROPY_LEN_224 = 28;
  public static final int ENTROPY_LEN_256 = 32;
  public static final int ENTROPY_LEN_288 = 36;
  public static final int ENTROPY_LEN_320 = 40;

  public static final String LANG_DEFAULT = "en";
  public static final int MR_OK = 0;
  public static final int MR_UNSUPPORTED_ENTROPY = 1;
  public static final int MR_UNKNOWN_ERROR = 2;
  private static ThreadLocal<ByteBuffer> nativeBuffer = new ThreadLocal<>();

  private final ReactApplicationContext reactContext;

  public RNRnBip39Module(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNRnBip39";
  }

  @Nonnull
  public static <T> T checkNotNull(T reference, String message) {
    if (reference == null) {
      throw new NullPointerException(message);
    }
    return reference;
  }

  private static String firstNonNull(String language, String langDefault) {
    if (language != null)
      return language;
    return langDefault;
  }

  @ReactMethod
  public void getLanguages(final Promise promise) {
    try {
      String[] returnArray = bip39GetLanguages();
      WritableArray promiseArray = Arguments.createArray();
      for(int i=0; i < returnArray.length; i++){
        promiseArray.pushString(returnArray[i]);
      }

      promise.resolve(promiseArray);
    }
    catch(Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void getWordsFromLanguage(@Nonnull String lang, final Promise promise) {
    try {
      checkNotNull(lang, "Language required");
      String[] returnArray = bip39GetWordsFromLanguage(lang);
      WritableArray promiseArray = Arguments.createArray();
      for(int i = 0; i < returnArray.length; i++){
        promiseArray.pushString(returnArray[i]);
      }

      promise.resolve(promiseArray);
    }
    catch(Exception e) {
      promise.reject(e);
    }
  }

//  @ReactMethod
//  public MnemonicResult encodeBytes(@Nonnull byte[] input) {
//    return encodeBytes(input, LANG_DEFAULT, ENTROPY_LEN_128);
//  }
//
//  /**
//   * Generates random mnemonic with PCGRandom for english language and entropy: 16 bytes
//   * @return
//   */
//  @ReactMethod
//  public MnemonicResult generate() {
//    return generate(LANG_DEFAULT, ENTROPY_LEN_128);
//  }

  @ReactMethod
  public void generate(String language, int entropy, final Promise promise) {
    try {
      MnemonicResult result = (MnemonicResult) bip39Generate(language, entropy);
      WritableMap out = Arguments.createMap();
      out.putString("status", result.getStatus());
      out.putString("mnemonic", result.getMnemonic());
      out.putString("seedhex", result.toSeedHex());
      promise.resolve(out);
    }
    catch(Exception e) {
      promise.reject(e);
    }
  }

  // @ReactMethod
  // public void encodeBytes(@Nonnull byte[] input, String language, int entropy, final Promise promise) {
  //   try {
  //     checkNotNull(input, "Input data can't be null");
  //
  //     ByteBuffer buff = nativeBuffer.get();
  //     if (buff == null || buff.capacity() < input.length) {
  //       buff = ByteBuffer.allocateDirect(input.length);
  //       buff.order(ByteOrder.BIG_ENDIAN);
  //       nativeBuffer.set(buff);
  //     }
  //
  //     buff.rewind();
  //     buff.put(input);
  //
  //     MnemonicResult result = (MnemonicResult)bip39EncodeBytes(buff, firstNonNull(language, LANG_DEFAULT), entropy);
  //     WritableMap out = Arguments.createMap();
  //     out.putString("status", result.getStatus());
  //     out.putString("mnemonic", result.getMnemonic());
  //     out.putString("seedhex", result.toSeedHex());
  //     promise.resolve(out);
  //   }
  //   catch(Exception e) {
  //     promise.reject(e);
  //   }
  // }

  @ReactMethod
  public void validateMnemonic(String mnemonic, String language, final Promise promise) {
    try {
      boolean result = false;
      if (mnemonic != null) {
        result = bip39ValidateMnemonic(mnemonic, firstNonNull(language, LANG_DEFAULT));
      }
      promise.resolve(result);
    }
    catch(Exception e) {
      promise.reject(e);
    }
  }

  // @ReactMethod
  // public void mnemonicToBip39Seed(String mnemonic, final Promise promise) {
  //   try {
  //     byte[] result = bip39WordsToSeed(mnemonic);
  //     WritableArray promiseArray = Arguments.createArray();
  //     for(int i = 0; i < result.length; i++){
  //       promiseArray.pushInt(result[i]);
  //     }
  //
  //     promise.resolve(result);
  //   }
  //   catch(Exception e) {
  //     promise.reject(e);
  //   }
  // }

  @ReactMethod
  public void mnemonicToBip39SeedHex(String mnemonic, final Promise promise) {
    try {
      String result = bip39WordsToSeedHex(mnemonic);
      promise.resolve(result);
    }
    catch(Exception e) {
      promise.reject(e);
    }
  }
}