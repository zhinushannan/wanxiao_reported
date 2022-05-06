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
          <el-form-item label="班级名称" prop="clazzName">
            <el-input v-model="form.clazzName"></el-input>
          </el-form-item>
          <el-form-item label="导员姓名" prop="teacherName">
            <el-select v-model="form.teacherName" placeholder="请选择辅导员姓名">
              <el-option
                  v-for="item in accounts"
                  :key="item.teacherName"
                  :label="item.teacherName"
                  :value="item.teacherName"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="班级编号" prop="deptId">
            <el-input v-model="form.deptId"></el-input>
          </el-form-item>
          <el-form-item label="班级群号" prop="groupId">
            <el-input v-model="form.groupId"></el-input>
          </el-form-item>
          <el-form-item label="机器人" prop="botId">
            <el-select v-model="form.botId" placeholder="请选择机器人编号">
              <el-option
                  v-for="item in bots"
                  :key="item.port"
                  :label="item.port"
                  :value="item.port"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="撤回功能">
            <el-switch v-model="form.delete" active-value=true inactive-value=false />
          </el-form-item>
          <div style="margin-left: 80px">
            <el-upload class="upload-demo" drag action="http://localhost:8080/upload/" multiple
                       :limit="1"
                       :on-exceed="exceed"
                       :on-success="success"
                       :on-remove="remove"
                       accept="csv">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或
                <em>点击上传</em>
              </div>
              <template #tip>
                <div class="el-upload__tip">只能上传 csv 文件。</div>
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
import {ElLoading, ElMessage} from "element-plus";

export default {
  name: "insert",
  data() {
    return {
      bots: [],
      accounts: [],

      form: {
        clazzName: "",
        teacherName: "",
        deptId: "",
        groupId: "",
        botId: "",
        delete: "",
        isUpload: false
      },

      rules: {
        clazzName: [
          {required: true, message: "请输入班级名称", trigger: "blur"},
        ],
        teacherName: [
          {required: true, message: "请选择辅导员", trigger: "blur"},
        ],
        deptId: [
          {required: true, message: "请输入班级编号", trigger: "blur"},
        ],
        groupId: [
          {required: true, message: "请输入班级群号", trigger: "blur"},
        ],
        botId: [
          {required: true, message: "请选择机器人", trigger: "blur"},
        ],
      }
    }
  },
  methods: {
    onSubmit() {
      let _this = this
      let loading = ElLoading.service({fullscreen: true})

      // 表单校验
      _this.$refs.form.validate((valid) => {
        if (valid) {
          if (!_this.form["isUpload"]) {
            ElMessage.error("请上传表格！")
            loading.close()
            return false
          }
          _this.$axios.post("/data/insert", _this.form).then((resp) => {
            console.log(resp.data.flag)
            console.log(resp.data.message)
            if (resp.data.flag) {
              ElMessage.success(resp.data.message)
            } else {
              ElMessage.warning(resp.data.message)
            }
            loading.close()
          })
        } else {
          loading.close()
          return false;
        }
      });
    },
    onReset() {
      this.$refs.form.resetFields();
    },
    exceed() {
      ElMessage.info("上传文件个数限制1个，请删除文件列表后重新上传！")
    },
    success(response) {
      this.form.isUpload = true
      ElMessage.success(response["message"])
    },
    remove() {
      this.form.isUpload = false
      ElMessage.info("移除成功！")
    }
  },
  created() {
    let _this = this
    _this.$axios.get("/bot/list").then((resp) => {
      _this.bots = resp["data"]["data"]
    })

    _this.$axios.get("/account/list").then((resp) => {
      _this.accounts = resp["data"]["data"]
    })

  }
}
</script>

<style scoped>

</style>