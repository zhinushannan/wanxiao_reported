<template>

  <div>

    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 机器人管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">

      <el-row :gutter="10">

        <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14">
          <h4>机器人列表</h4>

          <el-table
              style="width: 100%"
              :data="botData['data']"
          >
            <el-table-column prop="botId" align="center" width="100" label="机器人账号"/>
            <el-table-column prop="port" align="center" width="100" label="机器人端口"/>
            <el-table-column prop="status" align="center" width="150" label="机器人状态">
              <template #default="scope">
                <el-tag type="success" v-if="scope.row.status === 1">正常</el-tag>
                <span v-if="scope.row.status === 0"><el-tag type="danger">停止</el-tag>&nbsp;&nbsp;<el-button circle size="small" type="danger" plain @click="restart(scope.row.port)" icon="el-icon-refresh" title="启动机器人"></el-button></span>

              </template>
            </el-table-column>
            <el-table-column prop="clazz" align="center" label="班级">
              <template #default="scope">

                <el-popover placement="right" :width="400" trigger="hover">
                  <template #reference>
                    <el-button size="small">悬浮查看</el-button>
                  </template>
                  <span v-for="(value, key) in scope.row.clazz">
                    {{ value }}&nbsp;&nbsp;
                    <br v-if="(key + 1) % 6 === 0">
                  </span>
                </el-popover>
              </template>
            </el-table-column>

            <el-table-column label="操作" align="center" width="100" fixed="right">
              <template #default="scope">
                <el-button type="primary" size="small" @click="groupList(scope.row)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>

        </el-col>

        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
          <h4>{{ currentBot }}群列表</h4>

          <el-table
              style="width: 100%"
              :data="groups"
          >
            <el-table-column prop="groupId" align="center" label="群号"/>
            <el-table-column prop="clazzName" align="center" label="班级"/>
            <el-table-column prop="isMember" align="center" label="是否已加入">
              <template #default="scope">
                <el-tag v-if="scope.row.isMember">已加入</el-tag>
                <el-tag v-if="!scope.row.isMember" type="danger">未加入</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="mark" align="center" label="标记"/>
          </el-table>
        </el-col>

      </el-row>

    </div>

  </div>

</template>

<script>
export default {
  name: "manager",
  data() {
    return {
      botData: {
        page: 1,
        size: 10,
        total: 0,
        data: []
      },
      currentBot: "",
      groups: [],
    }
  },
  methods: {
    list() {
      let _this = this
      _this.$axios.post("/bot/list", _this.botData).then((resp) => {
        _this.botData = resp["data"]["data"]
        console.log(_this.botData)
      })
    },
    groupList(row) {
      let _this = this
      _this.$axios.get("/bot/groupList?botId=" + row["botId"] + "&port=" + row["port"]).then((resp) => {
        _this.currentBot = row["botId"] + ":" + row["port"]
        _this.groups = resp["data"]["data"]
      })
    },
    restart(port) {
      let _this = this
      _this.$axios.get("/bot/restart?port=" + port).then((resp) => {
        console.log(resp)
      })
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