
# react-native-rn-bip39

## Getting started

`$ npm install react-native-rn-bip39 --save`

### Mostly automatic installation

`$ react-native link react-native-rn-bip39`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-rn-bip39` and add `RNRnBip39.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNRnBip39.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.potato.bip39.RNRnBip39Package;` to the imports at the top of the file
  - Add `new RNRnBip39Package()` to the list returned by the `getPackages()` method
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
import RNRnBip39 from 'react-native-rn-bip39';

// TODO: What to do with the module?
RNRnBip39;
```
  