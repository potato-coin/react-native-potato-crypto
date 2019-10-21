package com.reactlibrary;

public final class MnemonicResult {
    private int status = PotatoCryptoModule.MR_OK;
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
        return status == PotatoCryptoModule.MR_OK;
    }

    public String toSeedHex() {
        return PotatoCryptoModule.bip39WordsToSeedHex(words);
    }

    public String getStatus() {
        switch (status) {
            case PotatoCryptoModule.MR_OK:
                return "OK";
            case PotatoCryptoModule.MR_UNSUPPORTED_ENTROPY:
                return "Unsupported entropy";
            case PotatoCryptoModule.MR_UNKNOWN_ERROR:
            default:
                return "Unknown error";
        }
    }
}
