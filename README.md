# Slide-Image-View
### サーバからpngファイル取得
- web_appにpngファイルを７つ配置(test1.png~test7.png)
- pngファイルがアプリのキャッシュディレクトリになければサーバから取得して保存
- キャッシュディレクトリにpngファイルがあればサーバへの通信を行わない
### ViewPagerで表示
- キャッシュディレクトリから全てのpngファイル取得したら表示

### 主要ライブラリ
- Retrofit
- Rx
- ViewPager
- 通信処理の直列化



![slide-image](https://user-images.githubusercontent.com/35129131/79640085-9d42d080-81ca-11ea-8dd9-e5a68c40b1f5.gif)
