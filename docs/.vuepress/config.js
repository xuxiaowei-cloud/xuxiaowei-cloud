module.exports = {
    // 站点配置
    lang: 'zh-CN',
    locales: {
        // 键名是该语言所属的子路径
        // 作为特例，默认语言可以使用 '/' 作为其路径。
        '/': {
            lang: 'zh-CN',
            selectLanguageName: '简体中文'
        }
    },
    themeConfig: {
        repoLabel: 'gitlab',
        repo: 'https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud',

        docsRepo: 'https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud',
        docsBranch: 'pages',
        docsDir: 'docs',
        editLinkPattern: ':repo/-/edit/pages/:path',

        lastUpdatedText: '最后修改',
        contributorsText: '贡献者'
    },
    head: [
        ['link', {rel: 'icon', href: '/favicon.ico'}]
    ],
}
