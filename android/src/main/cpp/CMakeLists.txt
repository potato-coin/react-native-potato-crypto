# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_CXX_STANDARD 14)

set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -Wall -pedantic")
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -Wall -g0 -O2")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wno-c99-extensions -Wno-unused-function -Wno-unused-const-variable -Wshift-count-overflow")


set(BTC_SRC
    ../../../../crypto/address.cpp
    ../../../../crypto/address.h
    ../../../../crypto/base58.cpp
    ../../../../crypto/base58.h
    ../../../../crypto/bignum.cpp
    ../../../../crypto/bignum.h
    ../../../../crypto/blake256.cpp
    ../../../../crypto/blake256.h
    ../../../../crypto/blake2_common.h
    ../../../../crypto/blake2b.cpp
    ../../../../crypto/blake2b.h
    ../../../../crypto/blake2s.cpp
    ../../../../crypto/blake2s.h
    ../../../../crypto/check_mem.h
    ../../../../crypto/curves.cpp
    ../../../../crypto/curves.h
    ../../../../crypto/ecdsa.cpp
    ../../../../crypto/ecdsa.h
    ../../../../crypto/groestl.cpp
    ../../../../crypto/groestl.h
    ../../../../crypto/groestl_internal.h
    ../../../../crypto/hasher.cpp
    ../../../../crypto/hasher.h
    ../../../../crypto/hmac.cpp
    ../../../../crypto/hmac.h
    ../../../../crypto/memzero.cpp
    ../../../../crypto/memzero.h
    ../../../../crypto/options.h
    ../../../../crypto/pbkdf2.cpp
    ../../../../crypto/pbkdf2.hpp
    ../../../../crypto/rfc6979.h
#    ../../../../crypto/rfc6979.cpp
    ../../../../crypto/rand.cpp
    ../../../../crypto/rand.h
    ../../../../crypto/ripemd160.cpp
    ../../../../crypto/ripemd160.h
    ../../../../crypto/script.cpp
    ../../../../crypto/script.h
    ../../../../crypto/secp256k1.cpp
    ../../../../crypto/secp256k1.h
    ../../../../crypto/sha2.cpp
    ../../../../crypto/sha2.hpp
    ../../../../crypto/sha3.cpp
    ../../../../crypto/sha3.h

    ../../../../bip39/crypto/common.h
    ../../../../bip39/crypto/sha256.h
    ../../../../bip39/crypto/sha256.cpp
    ../../../../bip39/crypto/sha256_sse4.cpp
    ../../../../bip39/crypto/hmac_sha256.h
    ../../../../bip39/crypto/hmac_sha256.cpp
    ../../../../bip39/crypto/hmac_sha512.h
    ../../../../bip39/crypto/hmac_sha512.cpp
    ../../../../bip39/crypto/sha512.h
    ../../../../bip39/crypto/sha512.cpp
    ../../../../bip39/crypto/sha256.h
    ../../../../bip39/crypto/sha256.cpp
    ../../../../bip39/bip39.cpp
    ../../../../bip39/bip39.h
    ../../../../bip39/data/wordlists/chinese_simplified.cpp
    ../../../../bip39/data/wordlists/chinese_traditional.cpp
    ../../../../bip39/data/wordlists/english.cpp
    ../../../../bip39/data/wordlists/french.cpp
    ../../../../bip39/data/wordlists/italian.cpp
    ../../../../bip39/data/wordlists/japanese.cpp
    ../../../../bip39/data/wordlists/spanish.cpp
    ../../../../bip39/wordlist.cpp
    ../../../../bip39/wordlist.h
    ../../../../bip39/mnemonic.h
    ../../../../bip39/mnemonic.cpp
    ../../../../bip39/internal.cpp
    ../../../../bip39/internal.h
    ../../../../bip39/bip39_core.h
    ../../../../bip39/config.h

    ../../../../bip39/minter/utils.h
    ../../../../bip39/minter/utils.cpp
    ../../../../bip39/minter/HDKeyEncoder.cpp
    ../../../../bip39/minter/HDKeyEncoder.h
    ../../../../bip39/minter/Bip39Mnemonic.cpp
    ../../../../bip39/minter/Bip39Mnemonic.h
    ../../../../bip39/minter/uint256_t.hpp
    ../../../../bip39/minter/PCGRand.hpp
    )

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../../crypto/)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../../bip39/)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

add_library( # Specifies the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             ${BTC_SRC}

             # Provides a relative path to your source file(s).
             native-lib.cpp )

find_library(
            log-lib
            log )

target_link_libraries(
            native-lib
            ${log-lib} )

target_include_directories(
            native-lib
            PRIVATE
            ../../../../crypto/
            ../../../../bip39/
            )