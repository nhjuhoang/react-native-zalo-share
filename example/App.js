/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react';
import { SafeAreaView, StyleSheet, Button } from 'react-native';
import ZaloShare from 'react-native-zalo-share';

const config = {
  msg: 'ahuhuahuahuahua',
  link:
    'https://edoctor.io/suc-khoe/bai-viet/news/20-tinh-thanh-cho-hoc-sinh-nghi-tiep',
  linkTitle: 'tinh thanh cho hoc sinh nghi tiep',
  linkSource:
    'https://edoctor.io/suc-khoe/bai-viet/news/20-tinh-thanh-cho-hoc-sinh-nghi-tiep',
  linkThumb:
    'https://img.v3.news.zdn.vn/w660/Uploaded/xpcwvovb/2015_12_15/cua_kinh_2.jpg',
  appName: 'Test HIHI',
};

const App: () => React$Node = () => {
  const onShareMessage = () => {
    ZaloShare.shareMessage(config)
      .then(console.log(' 👉🏼 share success'))
      .catch(error => console.log(' 👉🏼 error message', error.message));
  };

  const onShareFeed = () => {
    ZaloShare.shareFeed(config)
      .then(console.log(' 👉🏼 share success'))
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
