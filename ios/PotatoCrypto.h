
#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#else
#import "Base/RCTBridgeModule.h"
#endif

@interface PotatoCrypto : NSObject <RCTBridgeModule>

@end
  
