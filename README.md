# react-native-zalo-share

## Installation

  ```sh
  yarn add react-native-zalo-share
  ```
- **React Native 0.60 and higher**

 ```sh
  cd ios
  pod install
  cd ..
  ```
 - **React Native 0.59 and lower**
 
  ```sh
  react-native link react-native-zalo-share
  ```
  ### ANDROID
  - **add implementation "com.zing.zalo.zalosdk:auth:+" => android/app/build.gradle**
  ```sh
      dependencies {
          ...
          implementation "com.zing.zalo.zalosdk:auth:+"
          ...
      }
  ```
  - **add appZaloID => res/values/strings/xml**
  ```sh
      <resources>
        <string name="app_name">example</string>
        <string name="appZaloID">{"appID"}</string>
      </resources>
   ```
  - **edit file AndroidManifest.xml**
  ```sh
      <application
        ...
        <activity android:name="com.zing.zalo.zalosdk.oauth.OpenAPIActivity" />
        <meta-data
            android:name="com.zing.zalo.zalosdk.appID"
            android:value="@string/appZaloID" />
          ...
      </application>
  ```
  - **edit file MainApplication.java**
  ```sh
      import com.zing.zalo.zalosdk.oauth.ZaloSDKApplication;
      ...
      @Override
      public void onCreate() {
          ...
          ZaloSDKApplication.wrap(this);
          ...
      }
  ```
  
  ### IOS
  - **edit file AppDelegate.m**
   ```sh
      #import <ZaloSDK/ZaloSDK.h>
      ...
      - (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
      {
        ...

        [[ZaloSDK sharedInstance] initializeWithAppId:@"yourappID"];

        return YES;
      }
      
      // add override func
      - (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey,id>        *)options {
          return [
            [ZDKApplicationDelegate sharedInstance]
            application:app
            openURL:url
            sourceApplication:nil
            annotation:nil
          ];
}
   ```
   
 - **add URL Type**
    Main target setting -> info -> URL types -> click +
    identifier = “zalo”, URL Schemes = “zalo-yourappid”
    
 - **add Schemes LSApplicationQueriesSchemes Info.plist**
    Info.plist -> + LSApplicationQueriesSchemes -> item -> "zalo"


## Usage
  ```javascript
  import ZaloShare from 'react-native-zalo-share';

  const config = {
    msg: 'message',
    link: '',
    linkTitle: '',
    linkSource: '',
    linkThumb: '',
     appName: '', // appname back to app when cancel
  };

  const App: () => React$Node = () => {
    const onShareMessage = () => {
      ZaloShare.shareMessage(config)
        .then(console.log(' 👉🏼 send data to zalo success'))
        .catch(error => console.log(' 👉🏼 error message', error.message));
    };

    const onShareFeed = () => {
      ZaloShare.shareFeed(config)
        .then(console.log(' 👉🏼 send data to zalo success'))
        .catch(error => console.log(' 👉🏼 error message', error.message));
    };

    return (
      <SafeAreaView style={styles.container}>
        <Button title="share Message" onPress={onShareMessage} />
        <Button title="share Feed" onPress={onShareFeed} />
      </SafeAreaView>
    );
  };

  const styles = StyleSheet.create({
    container: {
      alignItems: 'center',
      justifyContent: 'center',
      flex: 1,
    },
  });

  export default App;
```
