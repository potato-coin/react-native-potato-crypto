package com.potato.bip39;

public final class MnemonicResult {
    private int status = RNRnBip39Module.MR_OK;
    private String words;
    private int len;

    public MnemonicResult(final String mnemonicPhrase) {
        if (mnemonicPhrase == null || mnemonicPhrase.isEmpty())
            throw new IllegalArgumentException("Mnemonic phrase can't be null");
        words = mnemonicPhrase;
        len = mnemonicPhrase.split("\\s+").length;
    }

    public String getMnemonic() {
        return words;
    }

    public int size() {
        return len;
    }

    public boolean isOk() {
        return status == RNRnBip39Module.MR_OK;
    }

    public String toSeedHex() {
        return RNRnBip39Module.bip39WordsToSeedHex(words);
    }

    public String getStatus() {
        switch (status) {
            case RNRnBip39Module.MR_OK:
                return "OK";
            case RNRnBip39Module.MR_UNSUPPORTED_ENTROPY:
                return "Unsupported entropy";
            case RNRnBip39Module.MR_UNKNOWN_ERROR:
            default:
                return "Unknown error";
        }
    }
}
