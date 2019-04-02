
package com.potato.bip39;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nonnull;

public class RNRnBip39Module extends ReactContextBaseJavaModule {

  static {
    System.loadLibrary("native-lib");
  }

  private static native Object bip39Generate(String language, int entropy);
  private static native String[] bip39GetLanguages();
  private static native String[] bip39GetWordsFromLanguage(String language);
  private static native Object bip39EncodeBytes(ByteBuffer input, String language, int entropy);
  private static native boolean bip39ValidateMnemonic(String mnemonic, String language);
  private static native byte[] bip39WordsToSeed(String mnemonic);

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
  public static String[] getLanguages() {
    return bip39GetLanguages();
  }

  @ReactMethod
  public static String[] getWordsFromLanguage(@Nonnull String lang) {
    checkNotNull(lang, "Language required");
    return bip39GetWordsFromLanguage(lang);
  }

  @ReactMethod
  public static MnemonicResult encodeBytes(@Nonnull byte[] input) {
    return encodeBytes(input, LANG_DEFAULT, ENTROPY_LEN_128);
  }

  /**
   * Generates random mnemonic with PCGRandom for english language and entropy: 16 bytes
   * @return
   */
  @ReactMethod
  public static MnemonicResult generate() {
    return generate(LANG_DEFAULT, ENTROPY_LEN_128);
  }

  @ReactMethod
  public static MnemonicResult generate(String language, int entropy) {
    return (MnemonicResult) bip39Generate(language, entropy);
  }

  @ReactMethod
  public static MnemonicResult encodeBytes(@Nonnull byte[] input, String language, int entropy) {
    checkNotNull(input, "Input data can't be null");

    ByteBuffer buff = nativeBuffer.get();
    if (buff == null || buff.capacity() < input.length) {
      buff = ByteBuffer.allocateDirect(input.length);
      buff.order(ByteOrder.BIG_ENDIAN);
      nativeBuffer.set(buff);
    }

    buff.rewind();
    buff.put(input);

    return ((MnemonicResult) bip39EncodeBytes(buff, firstNonNull(language, LANG_DEFAULT), entropy));
  }

  @ReactMethod
  public static boolean validateMnemonic(String mnemonic, String language) {
    if (mnemonic == null) {
      return false;
    }

    return bip39ValidateMnemonic(mnemonic, firstNonNull(language, LANG_DEFAULT));
  }

  @ReactMethod
  public static byte[] mnemonicToBip39Seed(String mnemonic) {
    return bip39WordsToSeed(mnemonic);
  }
}