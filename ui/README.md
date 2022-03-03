# ui

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
npm create vite@latest ui -- --template vue
```

1. [eslint](https://eslint.vuejs.org/user-guide/#installation)
    ```shell
    # 选择 Standard
    # 勾选 Lint on save、Lint and fix on commit
    vue add @vue/cli-plugin-eslint
    # vue-cli-service lint 无法运行
    npm i -D @vue/cli-service
    ```

1. [vue-router](https://router.vuejs.org/installation.html)
    ```shell
    npm install vue-router@4
    ```

## 说明

- 使用 Maven 进行打包

```shell
cd ui
mvn clean package
```

# Vue 3 + Vite

[setup 文档](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup)
