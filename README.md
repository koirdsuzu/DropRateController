# DropRateController
アイテムのドロップ率を変えるプラグイン 日本語は下
# DropRateController Plugin
# Support 1.13~1.21.4
DropRateController is a Minecraft plugin that allows server administrators to control the drop rates of items from both blocks and mobs with a configurable probability. This plugin is compatible with all modern Minecraft versions and provides an easy-to-use command system.

## Features

- Configure the drop rate for items dropped by mobs and blocks.
- Permission-based usage for commands and functionality.
- Configurable messages and settings stored in `config.yml`.
- Supports tab completion for easier command usage.

## Installation

1. Download the plugin's `.jar` file.
2. Place the `.jar` file in your server's `plugins` directory.
3. Start or reload your server.
4. Edit the `config.yml` file generated in the `plugins/DropRateController` folder to customize drop rates and messages.

## Commands

### `/dropchance`
- **Description**: Manage the drop rate for items.
- **Usage**:
  - `/dropchance get`: Displays the current drop rate.
  - `/dropchance set <value>`: Sets the drop rate to a value between 0 and 100.
- **Permission**: `dropratecontroller.use`

## Permissions

| Permission                | Description                                  |
|---------------------------|----------------------------------------------|
| `dropratecontroller.use`  | Allows the player to use `/dropchance`.      |

## Configuration

The plugin generates a `config.yml` file where you can configure the following:

```yaml
# Default drop rate percentage (0 to 100)
dropRate: 1.0

messages:
  usage: "Usage: /dropchance <get|set <value>>"
  get: "Current drop rate: %.2f%%"
  set: "Drop rate updated to %.2f%%"
  invalid: "Invalid value. Please provide a number between 0 and 100."
  range_error: "Please enter a number between 0 and 100."
  no_permission: "You do not have permission to use this command."
```

## How It Works

### Block Drops

### Mob Drops

# DropRateController プラグイン

DropRateController は、サーバー管理者がブロックやモブからドロップするアイテムの確率を設定できる Minecraft プラグインです。このプラグインは、最新の Minecraft バージョンと互換性があり、簡単に使用できるコマンドシステムを提供します。

## 特徴

- ブロックやモブからドロップするアイテムの確率を設定可能。
- コマンドや機能に対する権限管理。
- `config.yml` に保存された設定やメッセージのカスタマイズ。
- タブ補完による簡単なコマンド入力サポート。

## インストール

1. プラグインの `.jar` ファイルをダウンロードします。
2. サーバーの `plugins` フォルダに `.jar` ファイルを配置します。
3. サーバーを起動またはリロードします。
4. `plugins/DropRateController` フォルダ内に生成される `config.yml` を編集して、ドロップ率やメッセージをカスタマイズします。

## コマンド

### `/dropchance`
- **説明**: ドロップ率を管理します。
- **使い方**:
  - `/dropchance get`: 現在のドロップ率を表示します。
  - `/dropchance set <value>`: ドロップ率を 0 から 100 の値で設定します。
- **権限**: `dropratecontroller.use`

## 権限

| 権限                       | 説明                                      |
|----------------------------|-------------------------------------------|
| `dropratecontroller.use`   | プレイヤーが `/dropchance` を使用可能。   |

## 設定ファイル

プラグインは `config.yml` ファイルを生成します。このファイルで以下を設定できます:

```yaml
# デフォルトのドロップ率 (0 ～ 100)
dropRate: 1.0

messages:
  usage: "使い方: /dropchance <get|set <value>>"
  get: "現在のドロップ率: %.2f%%"
  set: "ドロップ率を %.2f%% に更新しました"
  invalid: "無効な値です。0 から 100 の数値を入力してください。"
  range_error: "0 から 100 の範囲内で数値を入力してください。"
  no_permission: "このコマンドを使用する権限がありません。"
```

## 動作概要

### ブロックのドロップ

### モブのドロップ
