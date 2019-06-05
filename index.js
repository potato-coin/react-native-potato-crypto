
import { NativeModules } from 'react-native';

const { PotatoCrypto } = NativeModules;

export const getLanguages = () => PotatoCrypto.getLanguages();

export const getWordsFromLanguage = (lang) => PotatoCrypto.getWordsFromLanguage(lang);

export const generate = (lang, entropy) => PotatoCrypto.generate(lang, entropy);

export const validateMnemonic = (mnemonic, lang) => PotatoCrypto.validateMnemonic(mnemonic, lang);

export const mnemonicToBip39SeedHex = (mnemonic) => PotatoCrypto.mnemonicToBip39SeedHex(mnemonic);

export const entropy = {
    BIP39_ENTROPY_LEN_128 : 16,
    BIP39_ENTROPY_LEN_160 : 20,
    BIP39_ENTROPY_LEN_192 : 24,
    BIP39_ENTROPY_LEN_224 : 28,
    BIP39_ENTROPY_LEN_256 : 32,
    BIP39_ENTROPY_LEN_288 : 36,
    BIP39_ENTROPY_LEN_320 : 40,
}

export const lang = {
    en:"en",
    es:"es",
    fr:"fr",
    it:"it",
    jp:"jp",
    zhs:"zhs",
    zht:"zht"
}

export default {
    getLanguages,
    getWordsFromLanguage,
    generate,
    validateMnemonic,
    mnemonicToBip39SeedHex,
    entropy,
    lang
}