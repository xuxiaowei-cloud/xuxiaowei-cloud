# Git 命令

## Git仓库大文件删除

```shell
# 列出 10 个大文件
git rev-list --all | xargs -rL1 git ls-tree -r --long | sort -uk3 | sort -rnk4 | head -10
# 删除文件
git filter-branch --tree-filter "rm -f 要删除的文件路径" -- --all
# 推送
git push -f --all
# 重新检出克隆，查看文件是否已被删除
```

## 克隆

```
git clone --progress -v "https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud.git"
```

## 分支切换

```
git checkout -b xuxiaowei remotes/origin/xuxiaowei
git checkout xuxiaowei
```

## 推送

```
git push --all --progress "gitcode"
git push --all --progress "gitee"
git push --all --progress "github"
git push --all --progress "gitlab"
```

## 拉取

```
git pull --progress -v --no-rebase "origin"
```

## 其他命令
```
git branch
git branch -a

git status
```
