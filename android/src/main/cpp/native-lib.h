//
// Created by rise on 19-4-2.
//

#ifndef EXAMPLE_NATIVE_LIB_H
#define EXAMPLE_NATIVE_LIB_H

#include <stdbool.h>
#include <stdint.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     network_minter_core_bip39_NativeBip39
 * Method:    getLanguages
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_com_reactlibrary_PotatoCryptoModule_bip39GetLanguages
    (JNIEnv *, jclass);

JNIEXPORT jobjectArray JNICALL
Java_com_reactlibrary_PotatoCryptoModule_bip39GetWordsFromLanguage(
    JNIEnv *env, jclass type, jstring language_);

JNIEXPORT jobject JNICALL
Java_com_reactlibrary_PotatoCryptoModule_bip39EncodeBytes(
    JNIEnv *env, jclass type, jobject input, jstring language_, jint entropy);

JNIEXPORT jboolean JNICALL
Java_com_reactlibrary_PotatoCryptoModule_bip39ValidateMnemonic(
    JNIEnv *env, jclass, jstring mnemonic_, jstring language_);

JNIEXPORT jbyteArray JNICALL
Java_com_reactlibrary_PotatoCryptoModule_bip39WordsToSeed(
    JNIEnv *env, jclass type, jstring mnemonic_);

JNIEXPORT jstring JNICALL
Java_com_reactlibrary_PotatoCryptoModule_bip39WordsToSeedHex(
        JNIEnv *env, jclass type, jstring mnemonic_);

JNIEXPORT jobject JNICALL
Java_com_reactlibrary_PotatoCryptoModule_bip39Generate(
    JNIEnv *env, jclass type, jstring language_, jint entropy);

#ifdef __cplusplus
}
#endif

#endif //EXAMPLE_NATIVE_LIB_H
