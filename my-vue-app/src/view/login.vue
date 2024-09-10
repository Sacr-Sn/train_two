<template>
    <div class="bg">
        <el-tabs type="border-card" stretch class="tag-box">
            <el-tab-pane label="登录" class="box">
                <el-card shadow="always" class="one">
                    <span class="title">login</span>
                    <div class="two">
                        <el-form-item label="账 号">
                            <el-input v-model="loginInfo.username" placeholder="账号" />
                        </el-form-item>
                        <el-form-item label="密 码">
                            <el-input v-model="loginInfo.password" placeholder="密码" type="password" />
                        </el-form-item>
                        <el-form-item label="验证码">
                            <el-input v-model="loginInfo.imgCode" style="width: 7vw;margin-right: 1vw;" placeholder="验证码" />
                            <el-image style="width: 5vw; height: 5vh" :src="codeSrc" fit="contain"
                                @click="codeSrc = codeSrc + '?' + new Date()" />
                        </el-form-item>
                        <el-form-item>
                            <el-button class="btn" round @click="login">登录</el-button>
                        </el-form-item>
                    </div>
                </el-card>
            </el-tab-pane>

            <el-tab-pane label="注册" class="box">
                <el-card shadow="always" class="one">
                    <span class="title" style="margin-left: 5vw;">register</span>
                    <div class="two">
                        <el-form-item>
                            <el-input v-model="registerInfo.account" placeholder="账号" />
                        </el-form-item>
                        <el-form-item>
                            <el-input v-model="registerInfo.email" placeholder="邮箱" />
                        </el-form-item>
                        <el-form-item>
                            <el-input v-model="registerInfo.emailCode" style="width: 7vw;margin-right: 1vw;"
                                placeholder="邮箱验证码" />
                            <el-button type="success" style="width: 5vw;" @click="getEmailCode">点击获取</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-input v-model="registerInfo.password" placeholder="密码" type="password" />
                        </el-form-item>
                        <el-form-item>
                            <el-input v-model="sure" placeholder="确认密码" type="password" @blur="handleBlur" />
                        </el-form-item>
                        <el-form-item>
                            <el-button class="btn" round @click="register">注册</el-button>
                        </el-form-item>
                    </div>
                </el-card>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { ref, reactive } from 'vue'
import { useRouter } from "vue-router";
import axios from 'axios';
import { setLocalToken } from "../common/token.js";
import { setLogStatus, setUserId, setUsername, setSex, setSchoolId } from '../common/user.js'
// import { TS_CONTEXT_PATH } from '../common/constants.js'

const store = useStore()
// 验证码后台接口
// const codeSrc = ref(BBS_CONTEXT_PATH + "/captcha/captchaImage");
// const codeSrc = ref("/api/imgCode");
const codeSrc = ref("/images/imgCode.webp")

const router = useRouter(); // 获取路由器

const errAlert = (message) => {
    ElMessage({
        message: message,
        type: 'warning',
    })
}

const okAlert = (message) => {
    ElMessage({
        message: message,
        type: 'success',
    })
}

// 用户输入的登录数据
const loginInfo = reactive({
    username: '',
    password: '',
    imgCode: ''
})

/**
 * 用户输入的注册数据
 */
const registerInfo = reactive({
    account: '',
    email: '',
    emailCode: '',
    password: ''
})

const sure = ref()




const login = () => {
    if (loginInfo.account == '' || loginInfo.password == '' || loginInfo.imgCode == '') {
        errAlert("请输入完整信息！")
        return
    }
    axios.post('/api/user/login', loginInfo).then(
        res => {
            // 从响应结果中取出token，并将token存于客户端
            if (res.data.code == 200) {
                setLocalToken(res.data.data);
                okAlert(res.data.message)
                // // 将用户信息保存在本地
                // setLogStatus(true)
                // setUserId(res.data.data.userInfoVo.id)
                // setUsername(res.data.data.userInfoVo.username)
                // setSchoolId(res.data.data.userInfoVo.schoolId)
                // setSex(res.data.data.userInfoVo.schoolId)
                // // 修改登录状态
                // store.state.user.logStatus = true
                // // 通过路由导航到管理页面
                router.push({ path: '/home' })
            } else {
                errAlert(res.data.message)
            }

        },
        error => {
            console.log('错误信息为：', error.message)
        }
    )
};

/**
 * 验证邮箱格式是否正确
 */
const validateEmail = (email) => {
    console.log(email)
    // 使用更一般性的邮箱格式验证正则表达式
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    // 测试邮箱格式是否匹配正则表达式
    return emailRegex.test(email);
}

/**
 * 获取邮箱验证码
 */
const getEmailCode = () => {
    if (registerInfo.email == '') {
        errAlert("请先输入邮箱！")
        return
    }
    if (!(validateEmail(registerInfo.email))) {
        errAlert("邮箱格式错误！")
        return
    }
    axios({
        method: 'get',
        url: '/api/mail/sendCode',
        params: { email: registerInfo.email }
    }).then(
        res => {
            if (res.data.code === 200) {
                okAlert(res.data.msg);
            } else {
                errAlert(res.data.msg)
            }
        },
        error => {
            console.log('错误信息为：', error.data.message)
        }
    )
}

/**
 * 失去焦点，判断密码是否一致
 */
const handleBlur = () => {
    if (sure.value != registerInfo.password) {
        errAlert("密码不一致！")
    }
}

/**
 * 注册
 */
const register = () => {
    // 判空
    if (registerInfo.account == '' || registerInfo.email == '' || registerInfo.emailCode == '' || registerInfo.password == '') {
        errAlert("请填写完整信息！")
        return
    }
    if (sure.value != registerInfo.password) {
        errAlert("密码不一致！")
        return
    }
    axios.post('/api/user/register', registerInfo).then(
        res => {
            // 从响应结果中取出token，并将token存于客户端
            if (res.data.code == 200) {
                okAlert(res.data.msg)
            } else {
                errAlert(res.data.msg)
            }
        },
        error => {
            console.log('错误信息为：', error.message)
        }
    )
};


</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
    font-size: 黑体;
    box-sizing: border-box;
}

.bg {
    /* 弹性居中布局 */
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    min-width: 100vw;
    /* 设置底色 */
    /* background: #222; */
    background-image: url(/images/logback.webp);
}


.box {
    width: 40vw;
    height: 80vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.one {
    /* border: 1px solid black; */
    width: 70%;
    height: 95%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.title {
    margin-left: 7vw;
    color: #00CCCC;
    font-family: Comic Sans MS;
    font-weight: 600;
    font-size: 2vw;
}

.two {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.btn {
    background-color: #00CCCC;
    width: 10vw;
    height: 5vh;
    color: whitesmoke;
}

.btn:hover {
    color: yellowgreen;
}

.el-form-item {
    margin-top: 3vh;
}
</style>