<template>

  <div>

    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 增加机器人
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <el-form ref="accountForm" :rules="botRules" :model="bot" class="demo-form-inline" inline>
        <el-form-item prop="teacherName">
          <el-input v-model="bot['accountUin']" placeholder="QQ账号" :disabled="isSubmit"/>
        </el-form-item>
        <el-form-item prop="username">
          <el-input v-model="bot['accountPassword']" placeholder="QQ密码" :disabled="isSubmit"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="bot['serversHttpPort']" placeholder="端口号" disabled/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">添加账号</el-button>
          <el-button type="primary" @click="onReset">重置表单</el-button>
        </el-form-item>
      </el-form>

      <textarea class="el-textarea__inner" style="width: 100%; height: 400px; resize: none;" disabled :value="logStr">

      </textarea>

    </div>


    <el-dialog
        v-model="qrcodeVisible"
        title="登录二维码"
        width="30%"
    >
      <el-image :src="'data:image/jpeg;base64,' + qrcodeBase64"/>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="qrcodeVisible = false">关闭</el-button>
      </span>
      </template>
    </el-dialog>

  </div>

</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "add",
  data() {
    return {
      isSubmit: false,
      bot: {
        accountUin: "",
        accountPassword: "",
        serversHttpPort: "",
      },
      botRules: {},

      qrcodeVisible: false,
      qrcodeBase64: "",

      logStr: "",
      logSessionId: "",
    }
  },

  methods: {
    onSubmit() {
      let _this = this
      _this.$axios.post("/bot/add", _this.bot).then((resp) => {
        if (resp.data.flag) {
          ElMessage.success(resp.data.message)

          _this.logs()
          // let timer = window.setInterval(function () {
          //   _this.logs()
          //   // if (_this.qrcodeVisible) {
          //   //   window.clearInterval(timer)
          //   // }
          // }, 500);
        }
      })
    },
    async logs() {
      let _this = this

      function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms))
      }

      await sleep(1000)

      function getLog() {
        _this.$axios.get("/bot/start/log?port=" + _this.bot.serversHttpPort + "&sessionId=" + _this.logSessionId).then((resp) => {
          _this.logSessionId = resp["data"]["message"]
          let logs = resp["data"]["data"]
          if (logs.length !== 0) {
            console.log(logs)
            for (let i = 0; i < logs.length; i++) {
              _this.logStr += logs[i] + "\n"
              if (logs[i].indexOf("qrcode.png") !== -1) {
                _this.qrcode()
              }
            }
          }
        })
      }

      window.setInterval(function () {
        getLog()
      }, 500);
    },
    qrcode() {
      let _this = this
      _this.$axios.get("/bot/add/qrcode?port=" + _this.bot["serversHttpPort"]).then((resp) => {
        console.log(resp.data)
        if (resp.data.message === "true") {
          _this.qrcodeBase64 = resp.data.data
          _this.qrcodeVisible = true
        }
      })
    },
    onReset() {
      this.bot = {
        accountUin: "",
        accountPassword: "",
        serversHttpPort: this.bot["serversHttpPort"],
      }
    }
  },
  created() {
    let _this = this
    _this.$axios.get("/bot/add/port?port=" + _this.bot["serversHttpPort"]).then((resp) => {
      _this.bot["serversHttpPort"] = resp["data"]["data"]
    })
  }
}
</script>

<style scoped>

</style>