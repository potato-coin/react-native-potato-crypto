
import { NativeModules } from 'react-native';

const { RNRnBip39 } = NativeModules;

export const getLanguages = () => RNRnBip39.getLanguages();

export const getWordsFromLanguage = (lang) => RNRnBip39.getWordsFromLanguage(lang);

export const generate = (lang, entropy) => RNRnBip39.generate(lang, entropy);

export const encodeBytes = (input, lang, entropy) =>  RNRnBip39.encodeBytes(input, lang, entropy);

export const validateMnemonic = (mnemonic, lang) => RNRnBip39.validateMnemonic(mnemonic, lang);

export const mnemonicToBip39Seed = (mnemonic) => RNRnBip39.mnemonicToBip39Seed(mnemonic);

export const mnemonicToBip39SeedHex = (mnemonic) => RNRnBip39.mnemonicToBip39SeedHex(mnemonic);

export const entropy = {
    BIP39_ENTROPY_LEN_128 : 16,
    BIP39_ENTROPY_LEN_160 : 20,
    BIP39_ENTROPY_LEN_192 : 24,
    BIP39_ENTROPY_LEN_224 : 28,
    BIP39_ENTROPY_LEN_256 : 32,
    BIP39_ENTROPY_LEN_288 : 36,
    BIP39_ENTROPY_LEN_320 : 40,
}

export default {
    getLanguages,
    getWordsFromLanguage,
    encodeBytes,
    generate,
    validateMnemonic,
    mnemonicToBip39Seed,
    mnemonicToBip39SeedHex,
    entropy
}