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
        <el-form-item prop="category">
          <el-select v-model="testForm.category" placeholder="类别">
            <el-option label="今日" value="0"/>
            <el-option label="全员" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="测试群聊群号">
          <el-input v-model="testForm.testGroupId" placeholder="测试群聊（可选）"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">测试</el-button>
          <el-button type="primary" @click="onReset">重置</el-button>
        </el-form-item>
      </el-form>

      <textarea class="el-textarea__inner" style="width: 100%; height: 400px; resize: none;" disabled :value="logStr">

      </textarea>

    </div>

  </div>

</template>

<script>
export default {
  name: "test",
  data() {
    return {
      testRules: [],
      testForm: {
        clazz: "",
        category: "",
        testGroupId: ""
      },
      logStr: "",
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
  }
}
</script>

<style scoped>

</style>