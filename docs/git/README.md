# Git 命令

## Git仓库大文件删除

```shell
# 列出 10 个大文件
git rev-list --all | xargs -rL1 git ls-tree -r --long | sort -uk3 | sort -rnk4 | head -10

# 根据路径，查看历史提交记录
# 其中 -n 5 代表显示最近的 5 条记录
# 其中 --all 代表查看所有分支
# 其中 -- 代表从路径开始
# 其中 --follow 代表跟踪文件的重命名和移动操作
# git log -n 5 --all -- 路径

# 删除文件
git filter-branch -f --tree-filter "rm -f 要删除的文件路径" -- --all
# 推送
git push -f --all
# 重新检出克隆，查看文件是否已被删除

# 清理服务器文件、提交记录
# 以下命令在服务器 git 仓库储存目录下执行
# gitlab 默认所有仓库储存总文件夹 /var/opt/gitlab/git-data/repositories/@hashed，执行 gitlab-rake gitlab:backup:create 备份数据后，备份日志可以分析出具体项目储存的文件夹

# 标记所有引用中过期的记录，并将它们设为立即过期。引用包括分支（branch）、标签（tag）等。
git reflog expire --expire=now --all
# 通过垃圾回收（garbage collection）清理掉已经过期的引用以及与之关联的无效对象。--prune=now 表示立即清理过期的引用，--aggressive 则表示使用更激进的方式进行垃圾回收。
git gc --prune=now --aggressive
```

## 克隆

```shell
git clone --progress -v "https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud.git"
```

## 分支切换

```shell
git checkout -b xuxiaowei remotes/origin/xuxiaowei
git checkout xuxiaowei
```

## 推送

```shell
git push --all --progress "gitcode"
git push --all --progress "gitee"
git push --all --progress "github"
git push --all --progress "gitlab"
```

## 拉取

```shell
git pull --progress -v --no-rebase "origin"
```

## 批量添加远端仓库地址

<details>
<summary>点击展开</summary>
git remote add gitee https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud.git

git remote add gitlab https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud.git

git remote add jihulab https://jihulab.com/xuxiaowei-cloud/xuxiaowei-cloud.git

git remote add github https://github.com/xuxiaowei-cloud/xuxiaowei-cloud.git

git remote add gitcode https://gitcode.net/xuxiaowei-cloud/xuxiaowei-cloud.git

git remote add gitlink https://gitlink.org.cn/xuxiaowei-cloud/xuxiaowei-cloud.git
</details>

## 其他命令

```shell
git branch
git branch -a

git status
```
