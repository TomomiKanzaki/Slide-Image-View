# Slide-Image-View
### サーバからpngファイル取得
- pngファイルがアプリのキャッシュディレクトリになければサーバから取得して保存
- キャッシュディレクトリにpngファイルがあればサーバへの通信を行わない
### ViewPagerで表示
- キャッシュディレクトリから全てのpngファイル取得したら表示

### 使ってる技術
- Retrofit
- Rx
- ViewPager
- 通信処理の直列化(最後の画像取得完了を検知するため)
