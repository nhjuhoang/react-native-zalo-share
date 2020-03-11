// @flow

import { NativeModules, requireNativeComponent, NativeEventEmitter } from 'react-native';

const { ZaloShare } = NativeModules;

type configType = {
  msg: string,
  link: string,
  linkTitle: string,
  linkSource: string,
  linkThumb: string,
  appName?: string, // app name back ios app when cancel share
}

const shareMessage = async (config: configType) => {
  await ZaloShare.shareMessage(config)
}

const shareFeed = async (config: configType) => {
  await ZaloShare.shareFeed(config)
}

export default {
  shareMessage,
  shareFeed,
};