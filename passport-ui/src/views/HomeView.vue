<template>
  <el-container>

    <!-- 高度自动，原 el-header、el-footer 均为固定高度 -->
    <!-- 上下左右外边距 -->
    <el-header class="cloud-header m-20px">
      <h1>Login</h1>
      <div>登录服务-徐晓伟微服务</div>
      <br v-if="!cross">
      <div v-if="!cross" class="color-red">
        由于跨域、Session共享、登录成功的授权URL配置，请使用{{ crossDomain }}的子域，如：{{ passportDomain }}，否则将无法登录
      </div>
    </el-header>

    <el-main class="cloud-main">

      <el-form class="cloud-form" ref="cloudFormRef" :model="cloudForm">

        <el-form-item label="">
          <el-select class="cloud-select" clearable filterable v-model="cloudForm.tenantId"
                     placeholder="请选择租户" @change="handleTenantChange">
            <template #prefix>
              <el-icon><Histogram/></el-icon>
            </template>
            <el-option v-for="item in tenantOptions" :key="item.tenantId" :value="item.tenantId"
                       :label="item.tenantName + ` - ` + item.clientName"/>
          </el-select>
        </el-form-item>

        <el-form-item label="" prop="username" :rules="[{ required: true, message: '用户名必填' }]">
          <el-input v-model.trim="cloudForm.username" :prefix-icon="User" placeholder="用户名"/>
        </el-form-item>

        <el-form-item label="" prop="password" :rules="[{ required: true, message: '密码必填' }]">
          <el-input v-model.trim="cloudForm.password" :prefix-icon="Key" placeholder="密码" :type="passwordType">
            <template #suffix>
              <el-icon class="el-input__icon">
                <Unlock v-if="passwordType==='password'" @click="passwordTypeClick"/>
                <Lock v-else @click="passwordTypeClick"/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item label="" prop="rememberMe" class="remember-me">
          <el-checkbox-group v-model="cloudForm.rememberMe">
            <el-checkbox name="rememberMe">记住我</el-checkbox>
          </el-checkbox-group>
          <el-link type="primary" target="_blank" class="forget" href="#/forget">忘记密码</el-link>
        </el-form-item>

        <el-form-item>
          <el-button class="submit-cloud-form" @click="submitCloudForm()">登录</el-button>
        </el-form-item>

        <el-form-item>

          <el-tooltip class="box-item" effect="dark" content="微信扫码登录" placement="top">
            <el-link :href="weChatOplatformWebsiteUrl" class="mr-5px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/wechat-fill.png" alt="微信扫码登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="码云Gitee登录" placement="top">
            <el-link :href="giteeUrl" class="ml-4px mr-4px">
              <!-- AnimaUI：https://www.iconfont.cn/collections/detail?cid=21749 -->
              <img src="../assets/gitee-fill-round.png" alt="码云Gitee登录" width="27">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="QQ扫码登录" placement="top">
            <el-link :href="qqWebsiteUrl"
              class="ml-4px mr-4px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/QQ-circle-fill.png" alt="QQ扫码登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="钉钉扫码登录" placement="top">
            <el-link :href="dingtalkUrl" class="ml-4px mr-4px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/dingtalk-circle-fill.png" alt="钉钉扫码登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="支付宝扫码登录" placement="top">
            <el-link :href="alipayOplatformWebsiteUrl" class="ml-4px mr-4px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/alipay-circle-fill.png" alt="支付宝扫码登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="微博扫码登录" placement="top">
            <el-link :href="weiBoWebsiteUrl" class="ml-4px mr-4px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/weibo-circle-fill.png" alt="微博扫码登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="GitLab登录" placement="top">
            <el-link :href="gitlabUrl" class="ml-4px mr-4px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/Gitlab-fill.png" alt="GitLab登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="企业微信登录" placement="top">
            <el-link :href="weChatWorkWebsiteUrl" class="ml-4px mr-4px">
              <!-- 吉利工业互联网WEB-Fill：https://www.iconfont.cn/collections/detail?cid=27812 -->
              <img src="../assets/external_enteerprise-wechat.png" alt="企业微信登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="GitHub登录" placement="top">
            <el-link :href="githubUrl" class="ml-4px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/github-fill.png" alt="GitHub登录" width="30">
            </el-link>
          </el-tooltip>

          <el-tooltip class="box-item" effect="dark" content="飞书登录" placement="top">
            <el-link :href="feiShuWebPageUrl" class="ml-4px">
              <!-- Ant Design 官方图标库：https://www.iconfont.cn/collections/detail?cid=9402 -->
              <img src="../assets/feishu.png" alt="飞书登录" width="30" style="filter: grayscale(100%);">
            </el-link>
          </el-tooltip>

        </el-form-item>

      </el-form>

    </el-main>

    <!-- 高度自动，原 el-header、el-footer 均为固定高度 -->
    <!-- 上下左右外边距 -->
    <el-footer class="cloud-footer h-auto m-20px">
      <span>&copy;&nbsp;2022</span>&nbsp;
      <a target="_blank" href="http://xuxiaowei.com.cn">徐晓伟工作室</a>&nbsp;
      <a target="_blank" href="http://beian.miit.gov.cn">鲁ICP备19009036号</a>
    </el-footer>

  </el-container>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { Histogram, Key, Lock, Unlock, User } from '@element-plus/icons-vue'
import { JSEncrypt } from 'jsencrypt'
import { ElMessage } from 'element-plus'
import { useRoute } from 'vue-router'
import { configuration, login } from '../api/passport'
import { pageLogin } from '../api/tenant'
import settings from '../settings'
import Resp from '../api/common'

// 跨域
const crossDomain = ref<string>('example.xuxiaowei.cloud')
// 登录域名
const passportDomain = ref<string>('passport.example.xuxiaowei.cloud')
// 跨域配置
const cross = ref<boolean>(location.host.includes(crossDomain.value))

const route = useRoute()

const weChatOplatformWebsiteUrl = ref()
const giteeUrl = ref()
const qqWebsiteUrl = ref()
const weiBoWebsiteUrl = ref()
const gitlabUrl = ref()
const weChatWorkWebsiteUrl = ref()
const githubUrl = ref()
const dingtalkUrl = ref()
const alipayOplatformWebsiteUrl = ref()
const feiShuWebPageUrl = ref()
// 默认客户主键
const defaultId = ref<string>('1')
// 默认租户ID
const defaultTenantId = ref<string>('1')
// 默认客户ID
const defaultClientId = ref<string>('1')

configuration().then((response : Resp<any>) => {
  console.log(response)
  const msg = response.msg
  if (response.code === settings.okCode) {
    weChatOplatformWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/wechat-oplatform/website/authorize/${response.data.weChatOplatformWebsiteAppid}`
    giteeUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/gitee/authorize/${response.data.giteeAppid}`
    qqWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/qq/website/authorize/${response.data.qqWebsiteAppid}`
    weiBoWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/weibo/authorize/${response.data.weiBoWebsiteAppid}`
    gitlabUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/gitlab/authorize/${response.data.gitlabAppid}`
    weChatWorkWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/wechat-work/website/authorize/${response.data.weChatWorkWebsiteAppid}/${response.data.weChatWorkWebsiteAgentid}`
    githubUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/github/authorize/${response.data.githubAppid}`
    dingtalkUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/dingtalk/authorize/${response.data.dingtalkAppid}`
    alipayOplatformWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/alipay-oplatform/website/authorize/${response.data.alipayOplatformWebsiteAppid}`
    feiShuWebPageUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/feishu-webpage/authorize/${response.data.feiShuWebPageAppid}`
    defaultTenantId.value = response.data.defaultTenantId
    defaultClientId.value = response.data.defaultClientId
    id.value = response.data.defaultId
    tenantId.value = response.data.defaultTenantId
    clientId.value = response.data.defaultClientId
  } else {
    ElMessage.error(msg)
  }
})

// 表单中的值
const cloudForm = reactive({
  id: '',
  tenantId: '',
  clientId: '',
  username: '',
  password: '',
  rememberMe: []
})

const id = ref<string>('')
const tenantId = ref<string>('')
const clientId = ref<string>('')

interface TenantOption {
  id: string;
  tenantId: string;
  tenantName: string;
  clientId: string;
  clientName: string;
}

const tenantOptions = ref<TenantOption[]>([])

pageLogin({ current: 1, size: 10, clientType: 'web' }).then(response => {
  tenantOptions.value = response.data.records
})

const handleTenantChange = function () {
  // 循环匹配客户ID
  tenantId.value = cloudForm.tenantId === '' ? defaultTenantId.value : cloudForm.tenantId
  if (tenantOptions.value !== undefined) {
    for (const tenantOption of tenantOptions.value) {
      if (tenantOption.tenantId === tenantId.value) {
        clientId.value = tenantOption.clientId
        id.value = tenantOption.id
        break
      }
    }
  }

  // 未选择租户时使用默认租户
  if (id.value === '' || id.value == null) {
    id.value = defaultId.value
  }

  // 未选择租户时使用默认租户
  if (tenantId.value === '' || tenantId.value == null) {
    tenantId.value = defaultTenantId.value
  }

  // 未选择租户时使用默认客户
  if (clientId.value === '' || clientId.value == null) {
    clientId.value = defaultClientId.value
  }
}

// 密码输入框类型
const passwordType = ref('password')

// 密码输入框类型切换
const passwordTypeClick = () => {
  passwordType.value = passwordType.value === 'password' ? 'text' : 'password'
}

// 表单验证
const cloudFormRef = ref()

// 提交表单
const submitCloudForm = () => {
  cloudFormRef.value.validate((valid: boolean) => {
    if (valid) {
      let header = 'header'
      let token = 'token'
      let password = cloudForm.password
      let rememberMeParameter = 'remember-me'
      if (process.env.NODE_ENV === 'production') {
        const csrfHeaderHTMLElement = document.head.querySelector('[name=_csrf_header][content]') as HTMLElement | null
        if (csrfHeaderHTMLElement) {
          header = csrfHeaderHTMLElement.getAttribute('content') || ''
        }

        const csrfHTMLElement = document.head.querySelector('[name=_csrf][content]') as HTMLElement | null
        if (csrfHTMLElement) {
          token = csrfHTMLElement.getAttribute('content') || ''
        }

        const rsaPublicKeyBase64HTMLElement = document.head.querySelector('[name=rsa_public_key_base64][content]') as HTMLElement | null
        if (rsaPublicKeyBase64HTMLElement) {
          const publicKey = rsaPublicKeyBase64HTMLElement.getAttribute('content') || ''

          const jsEncrypt = new JSEncrypt()
          jsEncrypt.setPublicKey(publicKey)
          const encrypt = jsEncrypt.encrypt(password)
          if (encrypt === false) {
            ElMessage.error('密码加密失败')
            return
          }

          password = encrypt
        }

        const rememberMeParameterHTMLElement = document.head.querySelector('[name=remember_me_parameter][content]') as HTMLElement | null
        if (rememberMeParameterHTMLElement) {
          rememberMeParameter = rememberMeParameterHTMLElement.getAttribute('content') || ''
        }
      }
      const redirectUri = route.query.redirectUri as string

      // encodeURIComponent()
      const homePage = route.query.homePage as string

      login(id.value, tenantId.value, clientId.value, cloudForm.username, password, cloudForm.rememberMe[0], header, token, rememberMeParameter, redirectUri, homePage).then((response : Resp<any>) => {
        console.log(response)
        const msg = response.msg

        if (response.code === settings.okCode) {
          ElMessage({
            message: msg,
            // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
            duration: 1500,
            type: 'success',
            onClose: () => {
              location.href = response.data.authorizeUri
            }
          })
        } else {
          ElMessage.error(msg)
        }
      }).catch(error => {
        console.error(error)
      })
    }
  })
}

</script>

<style scoped>

.cloud-header,
.cloud-footer {
  /* 高度自动，原 el-header、el-footer 均为固定高度 */
  height: auto;
}

.cloud-form {
  /* form 居中 */
  margin-left: auto;
  margin-right: auto;
}

.forget {
  position: absolute;
  right: 0;
}

.cloud-footer {
  /* 版权信息：绝对位置 */
  position: absolute;
  bottom: 30px;
  width: calc(100% - 40px);
  padding: 0;
}

/* xs<768px 响应式栅格数或者栅格属性对象 */
@media only screen and (max-width: 768px) {

  /* xs 主内容无内边距 */
  .cloud-main {
    --el-main-padding: 0;
    margin-left: 0;
    margin-right: 0;
  }

  /* 表单宽度 */
  .cloud-form {
    width: 300px;
  }

  .cloud-form .el-input,
  .cloud-form .cloud-select,
  .remember-me,
  .submit-cloud-form {
    /* xs ：输入框、按钮宽度 */
    width: 300px;
  }

}

/* sm≥768px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 768px) {

  .cloud-form .el-input,
  .cloud-form .cloud-select,
  .remember-me,
  .submit-cloud-form {
    /* 非 xs ：输入框、按钮宽度 */
    width: 375px;
  }

  .cloud-form {
    /* 非 xs ：表单宽度 */
    width: 375px;
  }
}

/* md≥992px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 992px) {

}

/* lg≥1200px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 1200px) {

}

/* xl≥1920px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 1920px) {

}

</style>
