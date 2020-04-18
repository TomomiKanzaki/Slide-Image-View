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
