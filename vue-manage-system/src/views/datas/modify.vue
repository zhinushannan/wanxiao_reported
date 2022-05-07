<template>

  <div>

    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 数据更改
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <el-row :gutter="10">
        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
          <h4>班级信息</h4>
          <el-table
              :data="clazzData['data']"
              style="width: 100%">
            <el-table-column fixed prop="clazzName" align="center" label="班级" />
            <el-table-column prop="teacherName" align="center" label="导员姓名" />
            <el-table-column prop="deptId" align="center" label="班级ID" />
            <el-table-column prop="groupId" align="center" label="班级群号" />
            <el-table-column prop="botId" align="center" label="机器人" />
            <el-table-column prop="delete" align="center" label="撤回服务" :formatter="deleteFormat" >
              <template #default="scope">
                <el-tag v-if="scope.row.delete===1">已开通</el-tag>
                <el-tag v-if="scope.row.delete===0" type="info">未开通</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="160">
              <template #default="scope" style="height: 400px">
                <el-button size="mini" type="primary" @click="student(scope.row)">学生</el-button>
                <el-button size="mini" type="info" @click="editClazz(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>

        <el-col :xs="6" :sm="8" :md="8" :lg="8" :xl="8">
          <h4>{{ currentClazz }}学生信息</h4>
          <el-table
              :data="stuData"
              height="500"
              style="width: 100%">
            <el-table-column
                fixed
                prop="studentName"
                align="center"
                label="姓名">
              <template #default="scope">
                <span v-if="scope.row.remove === 0"> {{ scope.row.studentName }} </span>
                <span v-if="scope.row.remove === 1"><el-tag size="small" type="warning"> {{ scope.row.studentName }} </el-tag></span>
              </template>
            </el-table-column>
            <el-table-column
                prop="studentQq"
                align="center"
                label="QQ号">
            </el-table-column>
            <el-table-column label="操作" align="center" width="160">
              <template #default="scope">
                <el-button size="mini" type="primary" @click="editStu(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>

      </el-row>
    </div>








    <!-- 班级编辑弹出框 -->
    <el-dialog :title="'编辑' + clazzForm['clazzName']" v-model="clazzVisible" width="30%">
      <div>
        <el-form ref="clazzForm" :rules="clazzFormRules" :model="clazzForm" label-width="80px">
          <el-form-item label="班级名称" prop="clazzName">
            <el-input v-model="clazzForm.clazzName" disabled></el-input>
          </el-form-item>
          <el-form-item label="导员姓名" prop="teacherName">
            <el-select v-model="clazzForm.teacherName" placeholder="请选择辅导员姓名">
              <el-option
                  v-for="item in accounts"
                  :key="item.teacherName"
                  :label="item.teacherName"
                  :value="item.teacherName"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="班级编号" prop="deptId">
            <el-input v-model="clazzForm.deptId"></el-input>
          </el-form-item>
          <el-form-item label="班级群号" prop="groupId">
            <el-input v-model="clazzForm.groupId"></el-input>
          </el-form-item>
          <el-form-item label="机器人" prop="botId">
            <el-select v-model="clazzForm.botId" placeholder="请选择机器人编号">
              <el-option
                  v-for="item in bots"
                  :key="item.port"
                  :label="item.port"
                  :value="item.port"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="撤回功能">
            <el-switch v-model="clazzForm.delete" :active-value=1 :inactive-value=0 />
          </el-form-item>
        </el-form>
      </div>
      <template #footer>
      <span class="dialog-footer">
          <el-button type="primary" @click="clazzEditCommit">确 定</el-button>

          <el-button @click="clazzVisible = false">取 消</el-button>
          <el-popconfirm title="删除后不可恢复，请再次确认！" @confirm="deleteClazz">
              <template #reference>
                <el-button type="danger">删除班级</el-button>
              </template>
          </el-popconfirm>
      </span>
      </template>
    </el-dialog>

    <!-- 学生编辑弹出框 -->
    <el-dialog :title="'编辑' + currentClazz + stuForm['studentName']" v-model="stuVisible" width="30%">
      <div>
        <el-form ref="stuForm" :rules="stuFormRules" :model="stuForm" label-width="80px">
          <el-form-item label="姓名" prop="studentName">
            <el-input v-model="stuForm.studentName" disabled></el-input>
          </el-form-item>
          <el-form-item label="QQ" prop="studentQq">
            <el-input v-model="stuForm.studentQq"></el-input>
          </el-form-item>
          <el-radio-group v-model="stuForm.isArm" size="large" style="margin-left: 80px">
            <el-radio-button label="不在校" />
            <el-radio-button label="在校" />
          </el-radio-group>
        </el-form>
      </div>
      <template #footer>
      <span class="dialog-footer">
          <el-button type="primary" @click="clazzVisible = false">确 定</el-button>
          <el-button @click="clazzVisible = false">取 消</el-button>
      </span>
      </template>
    </el-dialog>


  </div>

</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "modify",
  data() {
    return {
      bots: [],
      accounts: [],

      clazzData: {
        page: 1,
        size: 10
      },
      currentClazz: "",
      stuData: [],

      clazzVisible: false,
      clazzInfo: {},
      clazzForm: {},
      clazzFormRules: {},

      stuVisible: false,
      stuInfo: {},
      stuFormRules: {},
      stuForm: {}
    }
  },
  methods: {
    deleteFormat(row, column, cellValue, index) {
      return cellValue === 1 ? "已开通" : "未开通"
    },
    student(row) {
      let _this = this
      _this.$axios.get("/data/list/student?class=" + row["clazzName"]).then((resp) => {
        _this.currentClazz = row["clazzName"]
        _this.stuData = resp["data"]["data"]
      })
    },
    editClazz(row) {
      this.clazzForm = this.$jq.extend({}, row)
      this.clazzVisible = true
    },
    clazzEditCommit() {
      let _this = this
      _this.$axios.post("/data/modify/class", _this.clazzForm).then((resp) => {
        _this.list()
        if (resp.data.flag) {
          ElMessage.success(resp.data.message)
        } else {
          ElMessage.error(resp.data.message)
        }
        _this.clazzVisible = false
      })
    },
    deleteClazz() {
      let _this = this
      _this.$axios.get("/data/delete/class?class=" + _this.clazzForm["clazzName"]).then((resp) => {
        if (resp.data.flag) {
          ElMessage.success(resp.data.message)
        } else {
          ElMessage.error(resp.data.message)
        }
        _this.clazzVisible = false
        _this.list()
      })
    },
    editStu(row) {
      this.stuForm = row
      this.stuVisible = true
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },

    list() {
      let _this = this
      _this.$axios.post("/data/list/class", _this.clazzData).then((resp) => {
        _this.clazzData = resp.data.data
      })
    },
  },
  created() {
    this.list()
    let _this = this
    _this.$axios.get("/bot/list").then((resp) => {
      _this.bots = resp["data"]["data"]
    })

    _this.$axios.get("/account/list").then((resp) => {
      _this.accounts = resp["data"]["data"]
    })

  },
}
</script>

<style scoped>

</style>