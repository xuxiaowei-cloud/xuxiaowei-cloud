# Vue 3 + Vite

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
npm create vite@latest authorization-server-ui -- --template vue
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

1. [element-plus](https://element-plus.gitee.io/zh-CN/guide/quickstart.html#%E6%8C%89%E9%9C%80%E5%AF%BC%E5%85%A5)
    ```shell
    npm i element-plus
    ```

1. [axios](https://www.npmjs.com/package/axios)
