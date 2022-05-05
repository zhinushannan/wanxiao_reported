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
            <el-table-column
                fixed
                prop="clazzName"
                align="center"
                label="班级">
            </el-table-column>
            <el-table-column
                prop="teacherName"
                align="center"
                label="导员姓名">
            </el-table-column>
            <el-table-column
                prop="deptId"
                align="center"
                label="班级ID">
            </el-table-column>
            <el-table-column
                prop="groupId"
                align="center"
                label="班级群号">
            </el-table-column>
            <el-table-column
                prop="botId"
                align="center"
                label="机器人">
            </el-table-column>
            <el-table-column label="操作" align="center" width="160">
              <template #default="scope" style="height: 400px">
                <el-button size="mini" type="danger" icon="el-icon-document-copy" @click="student(scope.row)">学生</el-button>
              </template>
            </el-table-column>
        </el-table>
        </el-col>

        <el-col :xs="6" :sm="8" :md="8" :lg="8" :xl="8">
          <h4>{{ currentClazz }}学生信息</h4>
          <el-table
              :data="stuData"
              style="width: 100%">
            <el-table-column
                fixed
                prop="studentName"
                align="center"
                label="姓名">
            </el-table-column>
            <el-table-column
                prop="studentQq"
                align="center"
                label="QQ号">
            </el-table-column>
            <el-table-column label="操作" align="center" width="160">
              <template #default="scope" style="height: 400px">
                <el-button size="mini" type="danger" icon="el-icon-document-copy" @click="student(scope.row)">学生</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>

      </el-row>
    </div>

  </div>

</template>

<script>
export default {
  name: "modify",
  data() {
    return {
      clazzData: {
        page: 1,
        size: 10
      },
      currentClazz: "",
      stuData: []
    }
  },
  methods: {
    student(row) {
      let _this = this
      _this.$axios.get("/data/list/student?class=" + row["clazzName"]).then((resp) => {
        _this.currentClazz = row["clazzName"]
        _this.stuData = resp["data"]["data"]
      })
    }
  },
  created() {
    let _this = this
    _this.$axios.post("/data/list/class", _this.clazzData).then((resp) => {
      _this.clazzData = resp.data.data
    })
  }
}
</script>

<style scoped>

</style>