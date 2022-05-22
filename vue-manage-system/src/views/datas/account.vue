<template>

  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 帐号管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">

      <el-form ref="accountForm" :rules="accountRules" :model="accountForm" class="demo-form-inline" inline>
        <el-form-item prop="teacherName">
          <el-input v-model="accountForm.teacherName" placeholder="辅导员"/>
        </el-form-item>
        <el-form-item prop="username">
          <el-input v-model="accountForm.username" placeholder="账号"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="accountForm.password" placeholder="密码"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">添加 / 修改账号</el-button>
          <el-button type="primary" @click="onReset">重置表单</el-button>
        </el-form-item>
      </el-form>

      <el-table
          :data="accountData['data']"
          style="width: 100%">

        <el-table-column fixed prop="teacherName" align="center" label="辅导员"/>
        <el-table-column prop="username" align="center" label="账号"/>
        <el-table-column prop="password" align="center" label="密码"/>
        <el-table-column prop="classes" align="center" label="班级">
          <template #default="scope">
            <el-tag v-for="item in scope.row.clazz">{{ item }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button size="mini" type="primary" @click="edit(scope.row)">编辑</el-button>
            <el-popconfirm :title="'删除后不可恢复，确定要删除' + scope.row['teacherName'] + '吗？'" @confirm="del(scope.row)">
              <template #reference>
                <el-button type="danger" plain size="mini">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

    </div>

  </div>

</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "account",
  data() {
    return {
      accountData: {
        page: 1,
        size: 10,
        total: 0,
        data: []
      },


      accountRules: {
        teacherName: [{required: true, message: "请输入导员姓名", trigger: "blur"}],
        username: [{required: true, message: "请输入账号", trigger: "blur"}],
        password: [{required: true, message: "请输入密码", trigger: "blur"}],
      },
      accountForm: {
        teacherName: "",
        username: "",
        password: ""
      }
    }
  },
  methods: {
    list() {
      let _this = this
      _this.$axios.post("/data/account/list", _this.accountData).then((resp) => {
        _this.accountData = resp["data"]["data"]
        console.log(_this.accountData)
      })
    },
    onSubmit() {
      let _this = this
      // 表单校验
      _this.$refs.accountForm.validate((valid) => {
        if (valid) {
          _this.$axios.post("/data/account/save", _this.accountForm).then((resp) => {
            if (resp.data.flag) {
              ElMessage.success(resp.data.message)
              _this.onReset()
            } else {
              ElMessage.error(resp.data.message)
            }
            _this.list()
          })
        } else {
          ElMessage.error("提交失败")
          return false;
        }
      });
    },
    edit(row) {
      this.accountForm = this.$jq.extend({}, row)
    },
    del(row) {
      console.log(row)
      let _this = this
      _this.$axios.get("/data/account/delete?teacherName=" + row["teacherName"]).then((resp) => {
        if (resp.data.flag) {
          ElMessage.success(resp.data.message)
          _this.list()
        } else {
          ElMessage.error(resp.data.message)
        }
      })
    },
    onReset() {
      this.accountForm = {
        teacherName: "",
        username: "",
        password: ""
      }
    }
  },
  created() {
    let _this = this

    _this.list()
  }
}
</script>

<style scoped>

</style>