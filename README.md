
# react-native-rn-bip39

## Getting started

`$ npm install react-native-rn-bip39 --save`

### Mostly automatic installation

`$ react-native link react-native-rn-bip39`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-rn-bip39` and add `PotatoCrypto.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libPotatoCrypto.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.potato.crypto.PotatoCryptoPackage;` to the imports at the top of the file
  - Add `new PotatoCryptoPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-rn-bip39'
  	project(':react-native-rn-bip39').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-rn-bip39/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-rn-bip39')
  	```


## Usage
```javascript
import PotatoCrypto from 'react-native-rn-bip39';

const langs = await PotatoCrypto.getLanguages();
const mnemonic = await PotatoCrypto.generate('zhs', entropy.BIP39_ENTROPY_LEN_128);
const isValidate = await PotatoCrypto.validateMnemonic(mnemonic.mnemonic, 'zhs');
const seedHex = await PotatoCrypto.mnemonicToBip39SeedHex(mnemonic.mnemonic);

```
  