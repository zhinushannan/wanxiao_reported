<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 数据导入
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="form-box">
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
          <el-form-item label="班级名称" prop="clazz_name">
            <el-input v-model="form.clazz_name"></el-input>
          </el-form-item>
          <el-form-item label="导员姓名" prop="teacher_name">
            <el-input v-model="form.teacher_name"></el-input>
          </el-form-item>
          <el-form-item label="班级编号" prop="dept_id">
            <el-input v-model="form.dept_id"></el-input>
          </el-form-item>
          <el-form-item label="班级群号" prop="group_id">
            <el-input v-model="form.group_id"></el-input>
          </el-form-item>
          <el-form-item label="机器人" prop="bot_id">
            <el-select v-model="form.bot_id" placeholder="请选择机器人编号">
              <el-option key="bbk" label="步步高" value="bbk"></el-option>
              <el-option key="xtc" label="小天才" value="xtc"></el-option>
              <el-option key="imoo" label="imoo" value="imoo"></el-option>
            </el-select>
          </el-form-item>
          <div style="margin-left: 80px">
            <el-upload class="upload-demo" drag action="http://jsonplaceholder.typicode.com/api/posts/" multiple
                       accept="csv">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或
                <em>点击上传</em>
              </div>
              <template #tip>
                <div class="el-upload__tip">只能上传 csv 文件，且不超过 500kb</div>
              </template>
            </el-upload>
          </div>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">表单提交</el-button>
            <el-button @click="onReset">重置表单</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "insert",
  data() {
    return {
      form: {
        name: "",
        region: ""
      },
      rules: {}
    }
  },
  methods: {
    onSubmit() {

    },
    onReset() {
      this.$refs.form.resetFields();
    },
  }
}
</script>

<style scoped>

</style>