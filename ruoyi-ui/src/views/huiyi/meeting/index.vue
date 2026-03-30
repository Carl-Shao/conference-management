<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会议主题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入会议主题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会议室 ID" prop="roomId">
        <el-input
          v-model="queryParams.roomId"
          placeholder="请输入会议室 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会议日期" prop="meetingDate">
        <el-date-picker clearable
          v-model="queryParams.meetingDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择会议日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-time-picker clearable
          v-model="queryParams.startTime"
          value-format="HH:mm:ss"
          placeholder="请选择开始时间">
        </el-time-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-time-picker clearable
          v-model="queryParams.endTime"
          value-format="HH:mm:ss"
          placeholder="请选择结束时间">
        </el-time-picker>
      </el-form-item>
      <el-form-item label="组织者工号" prop="organizerNo">
        <el-input
          v-model="queryParams.organizerNo"
          placeholder="请输入组织者工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['huiyi:meeting:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['huiyi:meeting:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['huiyi:meeting:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['huiyi:meeting:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="meetingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="会议 ID" align="center" prop="id" />
      <el-table-column label="会议主题" align="center" prop="title" />
      <el-table-column label="会议室 ID" align="center" prop="roomId" />
      <el-table-column label="会议日期" align="center" prop="meetingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.meetingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.startTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.endTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="组织者工号" align="center" prop="organizerNo" />
      <el-table-column label="参会人员工号数组" align="center" prop="participantNos" />
      <el-table-column label="会议描述" align="center" prop="description" />
      <el-table-column label="会议状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['huiyi:meeting:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['huiyi:meeting:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改会议信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会议主题" prop="title">
          <el-input v-model="form.title" placeholder="请输入会议主题" />
        </el-form-item>
        <el-form-item label="会议室 ID" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入会议室 ID" />
        </el-form-item>
        <el-form-item label="会议日期" prop="meetingDate">
          <el-date-picker clearable
            v-model="form.meetingDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择会议日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-time-picker clearable
            v-model="form.startTime"
            value-format="HH:mm:ss"
            placeholder="请选择开始时间">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-time-picker clearable
            v-model="form.endTime"
            value-format="HH:mm:ss"
            placeholder="请选择结束时间">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="组织者工号" prop="organizerNo">
          <el-input v-model="form.organizerNo" placeholder="请输入组织者工号" />
        </el-form-item>
        <el-form-item label="参会人员工号" prop="participantNos">
          <el-input 
            v-model="form.participantNos" 
            type="textarea" 
            :rows="3"
            placeholder="请输入参会人员工号，多个用工号逗号分隔，例如：E001,E002,E003" />
        </el-form-item>
        <el-form-item label="会议描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMeeting, getMeeting, delMeeting, addMeeting, updateMeeting } from "@/api/huiyi/meeting"

export default {
  name: "Meeting",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 会议信息表格数据
      meetingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        roomId: null,
        meetingDate: null,
        startTime: null,
        endTime: null,
        organizerNo: null,
        participantNos: null,
        description: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "会议主题不能为空", trigger: "blur" }
        ],
        roomId: [
          { required: true, message: "会议室 ID不能为空", trigger: "blur" }
        ],
        meetingDate: [
          { required: true, message: "会议日期不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "结束时间不能为空", trigger: "blur" }
        ],
        organizerNo: [
          { required: true, message: "组织者工号不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询会议信息列表 */
    getList() {
      this.loading = true
      listMeeting(this.queryParams).then(response => {
        this.meetingList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        roomId: null,
        meetingDate: null,
        startTime: null,
        endTime: null,
        organizerNo: null,
        participantNos: null,
        description: null,
        status: null,
        createTime: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加会议信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getMeeting(id).then(response => {
        this.form = response.data
        // 将 JSON 数组转换为逗号分隔的字符串，方便编辑
        if (this.form.participantNos && this.form.participantNos.startsWith('[')) {
          try {
            const nos = JSON.parse(this.form.participantNos)
            this.form.participantNos = nos.join(',')
          } catch (e) {
            // 如果不是有效的 JSON，保持原样
          }
        }
        this.open = true
        this.title = "修改会议信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 处理参会人员工号数组，将逗号分隔的字符串转为 JSON 数组
          if (this.form.participantNos && this.form.participantNos.trim()) {
            // 支持中文逗号和英文逗号分割，去除空格和空项
            const nos = this.form.participantNos
              .split(/[,,]/)  // 支持中英文逗号
              .map(s => s.trim())  // 去除每项的空格
              .filter(s => s.length > 0);  // 过滤空项
            
            // 确保生成的 JSON 数组格式正确
            this.form.participantNos = JSON.stringify(nos);
            
            console.log('转换前的输入:', this.form.participantNos);
            console.log('转换后的 JSON:', this.form.participantNos);
          }
          
          if (this.form.id != null) {
            updateMeeting(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addMeeting(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除会议信息编号为"' + ids + '"的数据项？').then(function() {
        return delMeeting(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('huiyi/meeting/export', {
        ...this.queryParams
      }, `meeting_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
