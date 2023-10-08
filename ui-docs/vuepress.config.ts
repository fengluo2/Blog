import { defaultTheme } from '@vuepress/theme-default';
import { defineUserConfig } from 'vuepress';

export default defineUserConfig({
  lang: 'zh-CN',
  title: '你好， VuePress ！',
  description: '这是我的第一个 VuePress 站点',
  theme: defaultTheme({
    navbar: [
      { text: '首页', link: '/' },
      {
        text: '测试知识库',
        link: '/test/first',
      },
      {
        text: '中文文件夹测试',
        link: '/中文测试/第一个',
      },
      {
        text: '相关链接',
        children: [
          {
            text: '测试百度的链接',
            link: 'https://www.baidu.com',
          },
        ],
      },
    ],
    sidebar: 'auto',
  }),
});