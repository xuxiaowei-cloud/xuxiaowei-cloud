# exe

## 阿里云盘分享

1. [MySQL-Windows-Installer-5.5](https://www.aliyundrive.com/s/Aar2JF9bEtm)
2. [MySQL-Windows-Installer-5.6](https://www.aliyundrive.com/s/SfS3o467KaA)
3. [MySQL-Windows-Installer-5.7](https://www.aliyundrive.com/s/FVnS4ar5c6d)
4. [MySQL-Windows-Installer-8.0](https://www.aliyundrive.com/s/FoL6dYLL3wp)

## [cports](cports.exe)

- Windows 端口管理工具
- 使用管理员身份运行（否则可能无法强制停止端口）

## [tail](tail.exe)

- 在 Windows 中实时查看日志

```shell
tail.exe -f D:\logs\passport\passport.log
```

```shell
# 内容来自 ChatGPT

# 在 PowerShell 中，可以使用 Get-Content 命令来实时输出文件的内容。具体步骤如下：

# 打开 PowerShell 命令行窗口。

# 输入以下命令：

Get-Content -Path <文件路径> -Tail <行数> -Wait

# 其中 <文件路径> 是要输出内容的文件路径，<行数> 是指输出文件内容的末尾几行，-Wait 表示持续监视文件并等待新行的添加，以实现实时输出。

# 例如，如果要实时输出文件 C:\log\test.txt 的最后 10 行内容，可以输入以下命令：

Get-Content -Path C:\log\test.txt -Tail 10 -Wait

# 这样，当 test.txt 文件的内容发生变化时，PowerShell 窗口就会实时输出文件的最后 10 行内容。
```

## [tcping](tcping.exe)

- 查看端口是否开放，同：`telnet`

```shell
tcping.exe -t www.baidu.com 80
```
