<template>

  <div>

    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 通知测试
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>


    <div class="container">

      <el-form ref="testForm" :rules="testRules" :model="testForm" class="demo-form-inline" inline>
        <el-form-item prop="clazz">
          <el-select v-model="testForm.clazz" placeholder="测试班级">
            <el-option
                v-for="item in clazz"
                :key="item"
                :label="item"
                :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item prop="type">
          <el-select v-model="testForm.type" placeholder="类别">
            <el-option label="今日" value="0"/>
            <el-option label="全员" value="1"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="botId">
          <el-select v-model="testForm.botPort" placeholder="机器人（可选）">
            <el-option
                v-for="item in bots"
                :key="item.port"
                :label="item.port"
                :value="item.port"
            />
          </el-select>
        </el-form-item>
        <el-form-item prop="groupId">
          <el-input v-model="testForm.groupId" placeholder="测试群聊（可选）"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">测试</el-button>
          <el-button type="primary" @click="onReset">重置</el-button>
        </el-form-item>
      </el-form>

      <div style="font-size: 12px; padding-bottom: 5px;">打卡提醒预览结果：</div>
      <div class="el-textarea__inner" style="width: 100%; height: 200px; resize: none;overflow: scroll" disabled :innerHTML="msgShow" >
      </div>

      <div style="width: 1px; height: 10px;"></div>
      <div style="font-size: 12px; padding-bottom: 5px;">打卡提醒结果源：</div>
      <div class="el-textarea__inner" style="width: 100%; height: 200px; max-height: 200px; resize: none;;overflow: scroll" disabled :innerHTML="msgRaw">
      </div>

    </div>

  </div>

</template>

<script>
export default {
  name: "test",
  data() {
    return {
      testRules: [],
      bots: [],
      testForm: {
        clazz: "",
        type: "",
        botId: "",
        groupId: ""
      },
      msgShow: "",
      msgRaw: "",
      clazz: []
    }
  },
  methods: {
    clazzList() {
      let _this = this
      _this.$axios.get("/test/clazzList").then((resp) => {
        _this.clazz = resp["data"]["data"]
      })
    },
    onSubmit() {
      let _this = this

      _this.$axios.post("/test/do", _this.testForm).then((resp) => {
        _this.msgShow = resp["data"]["data"]["show"]
        _this.msgRaw = resp["data"]["data"]["raw"]
      })

    },
    onReset() {
      this.testForm = {
        clazz: "",
        category: "",
        testGroupId: ""
      }
    },
  },
  created() {
    let _this = this
    _this.clazzList()

    _this.$axios.get("/bot/list").then((resp) => {
      _this.bots = resp["data"]["data"]
    })

  }
}
</script>

<style scoped>

</style>