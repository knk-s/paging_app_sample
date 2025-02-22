﻿## 概要
楽天レシピAPIを用いてご飯もののレシピを取得し、一覧で表示する。  
[API詳細 : 楽天レシピカテゴリ別ランキングAPI](https://webservice.rakuten.co.jp/documentation/recipe-category-ranking)

![screen_recordings](https://github.com/user-attachments/assets/48004d7a-b40a-4e1c-8a60-5579a32c014b)


## 技術的に試したかったこと
- ページングの実装
- [Android推奨アーキテクチャ](https://developer.android.com/topic/architecture?hl=ja)の実装
- Jetpack Composeを用いたUI作成


## 使用ライブラリなど
- Jetpack Compose
- Retrofit2
- Coil
- Paging3

## 環境構築
開発環境で動かすには楽天APIのアプリID取得が必要である。

### 手順
1. [こちら](https://webservice.rakuten.co.jp/app/create)から新規アプリ登録を行い、アプリID/デベロッパーIDを発行する。(アプリURLはGitHubのアドレスなど)

2. ディレクトリ直下のlocal.propertiesに以下を追加する。

```
apiKey="[applicationId]"
```

## 今後やりたいこと
- レシピのテスト用データを作成し、ViewModelのテストができるようにする。
- 受信したレシピデータをローカルに保存する。PagingのRemoteMediaterを使う。
- カラースキームを設定する。

