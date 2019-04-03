
#import "RNRnBip39.h"

#include "minter/Bip39Mnemonic.h"
using namespace minter;

@implementation RNRnBip39

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(getLanguages, resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
{
    std::vector<std::string> list = Bip39Mnemonic::getLanguages();
//    if (list.size() > 0) {
        NSMutableArray* languages = [NSMutableArray array];
        for (auto it = list.begin(); it != list.end(); ++it) {
            std::string lang = *it;
            NSString *str=[NSString stringWithUTF8String:(lang.c_str())];

            [languages addObject:str];
        }
        resolve(languages);
//    } else {
//        NSError *error = [NSError errorWithDomain:@"world" code:200 userInfo:@""];
//        reject(@"error", @"", error);
//    }
}

RCT_REMAP_METHOD(getWordsFromLanguage, getWordsFromLanguage:(NSString*)lang
                 resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
{
    const char* langStr = [lang UTF8String];
    std::vector<std::string> list = Bip39Mnemonic::getWordsFromLanguage(langStr);
    NSMutableArray* languages = [NSMutableArray array];
    for (auto it = list.begin(); it != list.end(); ++it) {
        std::string lang = *it;
        NSString *str=[NSString stringWithUTF8String:(lang.c_str())];
        
        [languages addObject:str];
    }
    resolve(languages);
}

const char* getStatus(int status) {
    switch (status) {
        case Bip39Mnemonic::MnemonicStatus::Ok:
            return "OK";
        case Bip39Mnemonic::MnemonicStatus::UnsupportedEntropy:
            return "Unsupported entropy";
        case Bip39Mnemonic::MnemonicStatus::UnknownError:
        default:
            return "Unknown error";
    }
}

RCT_REMAP_METHOD(generate, generate:(NSString*)lang entropy:(NSInteger)entropy  resolver:(RCTPromiseResolveBlock)resolve  rejecter:(RCTPromiseRejectBlock)reject)
{
    const char* langStr = [lang UTF8String];
    
    Bip39Mnemonic::MnemonicResult result = Bip39Mnemonic::generate(langStr, (size_t) entropy);
//    if (result.status == Bip39Mnemonic::MnemonicStatus::Ok) {
        NSString *status = [NSString stringWithUTF8String:getStatus(result.status)];
        const char* mnemonicRaw = result.raw.c_str();
        NSString *mnemonic = [NSString stringWithUTF8String:mnemonicRaw];
        NSString *seedhex = [NSString stringWithUTF8String:Bip39Mnemonic::wordsToSeedHex(mnemonicRaw).c_str()];
        NSDictionary *obj = @{
                              @"status":status,
                              @"mnemonic":mnemonic,
                              @"seedhex":seedhex
                              };
        
        resolve(obj);
//    }
}

//RCT_REMAP_METHOD(encodeBytes, src:(NSString*)src lang:(NSString*)lang entropy:(NSInteger)entropy resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
//{
//    const uint8_t* srcStr = (const uint8_t*)[src UTF8String];
//    const char* langStr = [lang UTF8String];
//
//    Bip39Mnemonic::MnemonicResult result = Bip39Mnemonic::encodeBytes(srcStr, langStr, (size_t) entropy);
//
//    NSString *status = [NSString stringWithUTF8String:getStatus(result.status)];
//    const char* mnemonicRaw = result.raw.c_str();
//    NSString *mnemonic = [NSString stringWithUTF8String:mnemonicRaw];
//    NSString *seedhex = [NSString stringWithUTF8String:Bip39Mnemonic::wordsToSeedHex(mnemonicRaw).c_str()];
//    NSDictionary *obj = @{
//                          @"status":status,
//                          @"mnemonic":mnemonic,
//                          @"seedhex":seedhex
//                          };
//
//    resolve(obj);
//}

RCT_REMAP_METHOD(validateMnemonic, lang:(NSString*)lang words:(NSString*)words resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
{
    const char* langStr = [lang UTF8String];
    const char* wordsStr = [words UTF8String];
    
    BOOL result = Bip39Mnemonic::validateWords(langStr, wordsStr);
    
    resolve(result?@"true":@"false");
}

//RCT_REMAP_METHOD(mnemonicToBip39Seed, resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
//{
//
//}

RCT_REMAP_METHOD(mnemonicToBip39SeedHex, words:(NSString*)words resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
{
    const char* wordsStr = [words UTF8String];
    std::string seedHex = Bip39Mnemonic::wordsToSeedHex(wordsStr);
    
    resolve([NSString stringWithUTF8String:seedHex.c_str()]);
}

@end
  
