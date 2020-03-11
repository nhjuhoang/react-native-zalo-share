// @flow

import { NativeModules, requireNativeComponent, NativeEventEmitter } from 'react-native';

const { ZaloShare } = NativeModules;

type configType = {
  msg: string,
  link: string,
  title: string,
  source: string,
  linkThumb: string,
}

const shareMessage = (config: configType) => {
  try {
    ZaloShare.shareMessage(config);
  } catch (error) {
    console.log(error);
  }
}

const shareFeed = (config: configType) => {
  try {
    ZaloShare.shareFeed(config);
  } catch (error) {
    console.log(error);
  }
}

export default {
  shareMessage,
  shareFeed,
};