// @flow

import { NativeModules, requireNativeComponent, NativeEventEmitter } from 'react-native';

const { RNShareMessage } = NativeModules;

type configType = {
  msg: string,
  link: string,
  title: string,
  source: string,
  linkThumb: string,
}

const shareMessage = (config: configType) => {
  try {
    RNShareMessage.shareMessage(config);
  } catch (error) {
    console.log(error);
  }
}

const shareFeed = (config: configType) => {
  try {
    RNShareMessage.shareFeed(config);
  } catch (error) {
    console.log(error);
  }
}

export default {
  shareMessage,
  shareFeed,
};