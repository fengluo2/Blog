<script setup lang="ts">
import { useTitle } from '@/hooks/useTitle';
import { forward } from '@/utils/router';

const { title, changeTitle } = useTitle();

function goTest() {
  forward('test', {
    a: 1,
  });
}

function getTest() {
  let temp = '';
  for (const key in import.meta.env) {
    if (Object.prototype.hasOwnProperty.call(import.meta.env, key)) {
      const element = `${key}:${import.meta.env[key]}\n`;
      temp += element;
    }
  }
  return temp;
}
</script>

<template>
  <view class="content">
    <image class="logo" src="/static/logo.png" />
    <view class="text-area">
      <text class="title">{{ title }}</text>
    </view>
    <view @click="changeTitle">changeTitle</view>
    <view @click="goTest">测试页</view>
    <text>{{ getTest() }}</text>
  </view>
</template>

<style scoped lang="scss">
.content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.logo {
  margin: 200rpx auto 50rpx;
  width: 200rpx;
  height: 200rpx;
}

.text-area {
  display: flex;
  justify-content: center;
}

.title {
  font-size: 36rpx;
  color: #8f8f94;
}
</style>
