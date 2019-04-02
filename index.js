
import { NativeModules } from 'react-native';

const { RNRnBip39 } = NativeModules;

// export default RNRnBip39;

function getLanguages() {
    return RNRnBip39.getLanguages();
}

function getWordsFromLanguage(lang) {
    return RNRnBip39.getWordsFromLanguage(lang);
}

function encodeBytes(input) {
    return RNRnBip39.encodeBytes(input);
}

function generate() {
    return RNRnBip39.generate();
}

function generate(lang, entropy) {
    return RNRnBip39.generate(lang, entropy);
}

function encodeBytes(input, lang, entropy) {
    return RNRnBip39.encodeBytes(input, lang, entropy);
}

function validateMnemonic(mnemonic, lang) {
    return RNRnBip39.validateMnemonic(mnemonic, lang);
}

function mnemonicToBip39Seed(mnemonic) {
    return RNRnBip39.mnemonicToBip39Seed(mnemonic);
}

export default {
    getLanguages,
    getWordsFromLanguage,
    encodeBytes,
    generate,
    validateMnemonic,
    mnemonicToBip39Seed
}