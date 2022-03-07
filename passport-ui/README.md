# passport-ui

## 项目设置

```
npm install
```

### 编译、热加载

```
npm run dev
```

### 生产编译和压缩

```
npm run build
```

### lints和修复文件

```
npm run lint
```

### 自定义配置

- [配置参考](https://cli.vuejs.org/zh/config/index.html).

## 项目创建

- [nodejs v16.14.0 下载](https://nodejs.org/dist/v16.14.0/)
    - node v16.14.0
    - npm 8.3.1
- [Vite 官方中文文档](https://cn.vitejs.dev/guide/)

1. init

```shell
npm create vite@latest passport-ui -- --template vue
```

1. [eslint](https://eslint.vuejs.org/user-guide/#installation)
    ```shell
    npm install -g @vue/cli
    # 选择 Standard
    # 勾选 Lint on save、Lint and fix on commit
    vue add @vue/cli-plugin-eslint
    # vue-cli-service lint 无法运行
    npm i -D @vue/cli-service
    ```

1. [vue-router](https://router.vuejs.org/installation.html)
    ```shell
    npm install vue-router
    ```

## 依赖说明

1. 创建项目时自动生成
    - 依赖
        - vue
    - 开发依赖
        - @vitejs/plugin-vue
        - vite

1. lint
    - 开发依赖
        - @vue/cli-plugin-eslint
        - @vue/cli-service
        - @vue/eslint-config-standard
        - eslint
        - eslint-plugin-import
        - eslint-plugin-node
        - eslint-plugin-promise
        - eslint-plugin-vue
        - lint-staged

1. 路由
    - 依赖
        - vue-router

1. http 请求
    - 依赖
        - axios

1. element-plus 前端 UI 框架
    - 依赖
        - element-plus
    - 开发依赖
        - unplugin-auto-import
        - unplugin-vue-components

## 其他说明

- 使用 Maven 进行打包

```shell
cd ui
mvn clean package
```

# Vue 3 + Vite

[setup 文档](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup)
