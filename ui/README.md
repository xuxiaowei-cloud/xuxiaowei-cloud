# Init

- [nodejs](https://nodejs.org/dist/v16.14.0/)
    - node
        - v16.14.0
    - npm
        - 8.3.1
- [vitejs](https://cn.vitejs.dev/guide/)

```shell
npm create vite@latest ui -- --template vue
```

## Dependencies

1. [eslint](https://eslint.vuejs.org/user-guide/#installation)
    ```
    # 选择 Standard
    # 勾选 Lint on save、Lint and fix on commit
    vue add @vue/cli-plugin-eslint
    npm i -D @vue/cli-service
    ```

## 问题

1. `npm install`报错时，可使用`npm install --force`进行强制安装

- 使用 Maven 进行打包

```shell
cd ui
mvn clean package
```

# Vue 3 + Vite

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs,
check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VSCode](https://code.visualstudio.com/)
- [Volar](https://marketplace.visualstudio.com/items?itemName=johnsoncodehk.volar)
