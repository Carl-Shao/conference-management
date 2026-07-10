<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-microphone"></i> AI会议服务控制台</span>
          </div>

          <!-- 健康检查和状态区域 -->
          <el-row :gutter="20">
            <el-col :span="12">
              <div class="info-section">
                <h4><i class="el-icon-info"></i> 服务状态</h4>
                <div class="status-info">
                  <el-tag :type="healthStatusTag" size="medium">{{ healthStatusText }}</el-tag>
                  <el-button
                    type="primary"
                    size="mini"
                    @click="checkHealth"
                    :loading="checkingHealth"
                    style="margin-left: 10px;">
                    {{ checkingHealth ? '检查中...' : '刷新状态' }}
                  </el-button>
                </div>

                <!-- 服务详细状态 -->
                <div v-if="serviceStatus" class="status-details">
                  <p><strong>ASR工作进程:</strong> {{ serviceStatus.asrWorkerCount }} ({{ serviceStatus.asrWorkers ? serviceStatus.asrWorkers.join(', ') : 'none' }})</p>
                  <p><strong>纪要生成进程:</strong> {{ serviceStatus.minutesWorkerCount }} ({{ serviceStatus.minutesWorkers ? serviceStatus.minutesWorkers.join(', ') : 'none' }})</p>
                  <p><strong>录音房间 (音频):</strong> {{ serviceStatus.recordingRooms && serviceStatus.recordingRooms.audio ? serviceStatus.recordingRooms.audio.join(', ') : '无' }}</p>
                  <p><strong>录音房间 (视频):</strong> {{ serviceStatus.recordingRooms && serviceStatus.recordingRooms.video ? serviceStatus.recordingRooms.video.join(', ') : '无' }}</p>
                </div>
              </div>
            </el-col>

            <el-col :span="12">
              <div class="info-section">
                <h4><i class="el-icon-setting"></i> 会议控制面板</h4>
                <div class="control-panel">
                  <el-form :model="controlForm" label-width="100px">
                    <el-form-item label="房间ID:">
                      <el-input v-model="controlForm.roomId" placeholder="请输入房间ID" />
                    </el-form-item>
                    <el-form-item label="RTSP地址:">
                      <el-input v-model="controlForm.rtspUrl" placeholder="请输入RTSP流地址" />
                    </el-form-item>
                    <el-form-item>
                      <el-button-group>
                        <el-button
                          type="success"
                          icon="el-icon-video-play"
                          @click="startMeeting"
                          :disabled="!controlForm.roomId || !controlForm.rtspUrl || meetingStatus === 'recording'"
                          :loading="startingMeeting">
                          {{ meetingStatus === 'recording' ? '会议录制中...' : '开始会议录制' }}
                        </el-button>
                        <el-button
                          type="warning"
                          icon="el-icon-video-pause"
                          @click="stopMeeting"
                          :disabled="meetingStatus !== 'recording'"
                          :loading="stoppingMeeting">
                          停止会议录制
                        </el-button>
                      </el-button-group>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 转录文本显示区域 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-chat-line-square"></i> 会议转录文本</span>
            <el-button
              style="float: right; padding: 3px 0"
              type="text"
              icon="el-icon-refresh"
              @click="fetchTranscript"
              :loading="fetchingTranscript"
              :disabled="!controlForm.roomId">
              刷新转录
            </el-button>
          </div>

          <div class="transcript-container">
            <el-input
              type="textarea"
              :rows="8"
              placeholder="会议转录文本将在此显示..."
              v-model="transcriptText"
              readonly>
            </el-input>
            <div style="margin-top: 10px;">
              <el-button
                type="primary"
                size="small"
                icon="el-icon-document-add"
                @click="generateMinutes"
                :disabled="!controlForm.roomId">
                生成会议纪要
              </el-button>
              <el-button
                type="success"
                size="small"
                icon="el-icon-download"
                @click="downloadTranscript"
                :disabled="!transcriptText">
                下载转录文本
              </el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 会议纪要区域 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-timer"></i> 纪要生成状态</span>
            <el-button
              style="float: right; padding: 3px 0"
              type="text"
              icon="el-icon-refresh"
              @click="fetchMinutesStatus"
              :loading="fetchingMinutesStatus"
              :disabled="!controlForm.roomId">
              刷新状态
            </el-button>
          </div>

          <div class="status-container">
            <div v-if="minutesStatusData">
              <p><strong>状态:</strong>
                <el-tag
                  :type="getStatusTag(minutesStatusData.status)"
                  size="medium">
                  {{ getStatusText(minutesStatusData.status) }}
                </el-tag>
              </p>
              <p><strong>房间ID:</strong> {{ minutesStatusData.roomId }}</p>
              <p><strong>进度:</strong> {{ minutesStatusData.progress || 'N/A' }}</p>
              <p><strong>消息:</strong> {{ minutesStatusData.message || 'N/A' }}</p>
            </div>
            <p v-else class="no-data">暂无纪要生成状态信息</p>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-document-checked"></i> 会议纪要</span>
            <el-button
              style="float: right; padding: 3px 0"
              type="text"
              icon="el-icon-refresh"
              @click="fetchMinutes"
              :loading="fetchingMinutes"
              :disabled="!controlForm.roomId">
              刷新纪要
            </el-button>
          </div>

          <div class="minutes-container">
            <el-input
              type="textarea"
              :rows="8"
              placeholder="会议纪要将在此显示..."
              v-model="minutesText"
              readonly>
            </el-input>
            <div style="margin-top: 10px;">
              <el-button
                type="primary"
                size="small"
                icon="el-icon-refresh"
                @click="generateMinutes"
                :disabled="!controlForm.roomId">
                重新生成纪要
              </el-button>
              <el-button
                type="success"
                size="small"
                icon="el-icon-download"
                @click="downloadMinutes"
                :disabled="!minutesText">
                下载纪要
              </el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 自动刷新设置 -->
    <el-dialog title="自动刷新设置" :visible.sync="refreshDialogVisible" width="500px">
      <el-form :model="refreshSettings" label-width="120px">
        <el-form-item label="转录刷新间隔(秒)">
          <el-slider
            v-model="refreshSettings.transcriptInterval"
            :min="5"
            :max="120"
            show-input>
          </el-slider>
        </el-form-item>
        <el-form-item label="状态刷新间隔(秒)">
          <el-slider
            v-model="refreshSettings.statusInterval"
            :min="10"
            :max="300"
            show-input>
          </el-slider>
        </el-form-item>
        <el-form-item label="启用自动刷新">
          <el-switch v-model="refreshSettings.enabled"></el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="refreshDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveRefreshSettings">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  healthCheck,
  startMeeting,
  stopMeeting,
  getTranscript,
  generateMinutes,
  getMinutes,
  getMinutesStatus
} from "@/api/huiyi/aiService";

export default {
  name: "AIService",
  data() {
    return {
      // 健康状态
      healthStatus: null,
      healthStatusTag: 'info',
      healthStatusText: '未知',
      checkingHealth: false,
      serviceStatus: null,

      // 控制表单
      controlForm: {
        roomId: '',
        rtspUrl: ''
      },

      // 会议录制状态
      meetingStatus: '', // 'recording' 或空
      startingMeeting: false,
      stoppingMeeting: false,

      // 转录文本
      transcriptText: '',
      fetchingTranscript: false,

      // 会议纪要
      minutesText: '',
      fetchingMinutes: false,

      // 会议纪要状态
      minutesStatusData: null,
      fetchingMinutesStatus: false,

      // 自动刷新设置
      refreshDialogVisible: false,
      refreshSettings: {
        transcriptInterval: 30,
        statusInterval: 60,
        enabled: false
      },
      transcriptTimer: null,
      statusTimer: null
    };
  },

  created() {
    // 页面加载时检查一次健康状态
    this.checkHealth();

    // 设置自动刷新
    this.setupAutoRefresh();
  },

  destroyed() {
    // 清理定时器
    this.clearTimers();
  },

  methods: {
    // 检查健康状态
    async checkHealth() {
      this.checkingHealth = true;
      try {
        const response = await healthCheck();

        if (response.code === 200) {
          this.healthStatus = response.data.data;
          this.healthStatusTag = 'success';
          this.healthStatusText = '服务正常';

          // 更新服务状态信息
          this.serviceStatus = response.data.data;

          // 更新会议录制状态
          if (this.serviceStatus && this.serviceStatus.recordingRooms) {
            const audioRooms = this.serviceStatus.recordingRooms.audio || [];
            const videoRooms = this.serviceStatus.recordingRooms.video || [];
            const allRooms = [...new Set([...audioRooms, ...videoRooms])];

            // 如果当前房间在录制列表中，则标记为录制中
            if (allRooms.includes(this.controlForm.roomId)) {
              this.meetingStatus = 'recording';
            }
          }
        } else {
          this.healthStatusTag = 'warning';
          this.healthStatusText = '服务异常';
        }
      } catch (error) {
        console.error('健康检查失败:', error);
        this.healthStatusTag = 'danger';
        this.healthStatusText = '服务不可达';
      } finally {
        this.checkingHealth = false;
      }
    },

    // 开始会议录制
    async startMeeting() {
      if (!this.controlForm.roomId || !this.controlForm.rtspUrl) {
        this.$message.warning('请填写房间ID和RTSP地址');
        return;
      }

      this.startingMeeting = true;
      try {
        const response = await startMeeting({
          roomId: this.controlForm.roomId,
          rtspUrl: this.controlForm.rtspUrl
        });

        if (response.code === 200) {
          this.$message.success('会议录制已开始');
          this.meetingStatus = 'recording';
          // 刷新状态
          setTimeout(() => {
            this.checkHealth();
          }, 1000);
        } else {
          this.$message.error(response.msg || '开始会议录制失败');
        }
      } catch (error) {
        console.error('开始会议录制失败:', error);
        this.$message.error('开始会议录制失败: ' + (error.message || '网络错误'));
      } finally {
        this.startingMeeting = false;
      }
    },

    // 停止会议录制
    async stopMeeting() {
      if (!this.controlForm.roomId) {
        this.$message.warning('请填写房间ID');
        return;
      }

      this.stoppingMeeting = true;
      try {
        const response = await stopMeeting(this.controlForm.roomId);

        if (response.code === 200) {
          this.$message.success('会议录制已停止');
          this.meetingStatus = '';
          // 刷新状态
          setTimeout(() => {
            this.checkHealth();
          }, 1000);
        } else {
          this.$message.error(response.msg || '停止会议录制失败');
        }
      } catch (error) {
        console.error('停止会议录制失败:', error);
        this.$message.error('停止会议录制失败: ' + (error.message || '网络错误'));
      } finally {
        this.stoppingMeeting = false;
      }
    },

    // 获取转录文本
    async fetchTranscript() {
      if (!this.controlForm.roomId) {
        this.$message.warning('请填写房间ID');
        return;
      }

      this.fetchingTranscript = true;
      try {
        const response = await getTranscript(this.controlForm.roomId);

        if (response.code === 200) {
          if (response.data && response.data.transcript) {
            this.transcriptText = response.data.transcript;
            this.$message.success('转录文本获取成功');
          } else {
            this.transcriptText = '';
            this.$message.info('暂无转录文本');
          }
        } else {
          this.$message.error(response.msg || '获取转录文本失败');
        }
      } catch (error) {
        console.error('获取转录文本失败:', error);
        this.$message.error('获取转录文本失败: ' + (error.message || '网络错误'));
      } finally {
        this.fetchingTranscript = false;
      }
    },

    // 获取会议纪要
    async fetchMinutes() {
      if (!this.controlForm.roomId) {
        this.$message.warning('请填写房间ID');
        return;
      }

      this.fetchingMinutes = true;
      try {
        const response = await getMinutes(this.controlForm.roomId);

        if (response.code === 200) {
          if (response.data && response.data.minutes) {
            this.minutesText = response.data.minutes;
            this.$message.success('会议纪要获取成功');
          } else {
            this.minutesText = '';
            this.$message.info('暂无会议纪要');
          }
        } else {
          this.$message.error(response.msg || '获取会议纪要失败');
        }
      } catch (error) {
        console.error('获取会议纪要失败:', error);
        this.$message.error('获取会议纪要失败: ' + (error.message || '网络错误'));
      } finally {
        this.fetchingMinutes = false;
      }
    },

    // 获取会议纪要状态
    async fetchMinutesStatus() {
      if (!this.controlForm.roomId) {
        this.$message.warning('请填写房间ID');
        return;
      }

      this.fetchingMinutesStatus = true;
      try {
        const response = await getMinutesStatus(this.controlForm.roomId);

        if (response.code === 200) {
          if (response.data) {
            this.minutesStatusData = response.data;
            this.$message.success('纪要状态获取成功');
          } else {
            this.minutesStatusData = null;
            this.$message.info('暂无纪要状态信息');
          }
        } else {
          this.$message.error(response.msg || '获取纪要状态失败');
        }
      } catch (error) {
        console.error('获取纪要状态失败:', error);
        this.$message.error('获取纪要状态失败: ' + (error.message || '网络错误'));
      } finally {
        this.fetchingMinutesStatus = false;
      }
    },

    // 生成会议纪要
    async generateMinutes() {
      if (!this.controlForm.roomId) {
        this.$message.warning('请填写房间ID');
        return;
      }

      try {
        const response = await generateMinutes(this.controlForm.roomId);

        if (response.code === 200) {
          this.$message.success('会议纪要生成任务已提交，正在后台处理');
          // 稍后刷新状态
          setTimeout(() => {
            this.fetchMinutesStatus();
          }, 2000);
        } else {
          this.$message.error(response.msg || '提交纪要生成任务失败');
        }
      } catch (error) {
        console.error('提交纪要生成任务失败:', error);
        this.$message.error('提交纪要生成任务失败: ' + (error.message || '网络错误'));
      }
    },

    // 下载转录文本
    downloadTranscript() {
      if (!this.transcriptText) {
        this.$message.warning('没有可下载的转录文本');
        return;
      }

      const blob = new Blob([this.transcriptText], { type: 'text/plain;charset=utf-8' });
      const filename = `transcript_${this.controlForm.roomId}_${new Date().toISOString().slice(0, 19).replace(/:/g, '-')}.txt`;
      const link = document.createElement('a');
      link.href = URL.createObjectURL(blob);
      link.download = filename;
      link.click();
    },

    // 下载会议纪要
    downloadMinutes() {
      if (!this.minutesText) {
        this.$message.warning('没有可下载的会议纪要');
        return;
      }

      const blob = new Blob([this.minutesText], { type: 'text/plain;charset=utf-8' });
      const filename = `minutes_${this.controlForm.roomId}_${new Date().toISOString().slice(0, 19).replace(/:/g, '-')}.txt`;
      const link = document.createElement('a');
      link.href = URL.createObjectURL(blob);
      link.download = filename;
      link.click();
    },

    // 获取状态标签类型
    getStatusTag(status) {
      switch (status) {
        case 'completed':
          return 'success';
        case 'processing':
          return 'warning';
        case 'pending':
          return 'info';
        case 'failed':
          return 'danger';
        default:
          return 'info';
      }
    },

    // 获取状态文本
    getStatusText(status) {
      switch (status) {
        case 'completed':
          return '已完成';
        case 'processing':
          return '处理中';
        case 'pending':
          return '待处理';
        case 'failed':
          return '失败';
        case 'not_found':
          return '未找到';
        default:
          return status;
      }
    },

    // 设置自动刷新
    setupAutoRefresh() {
      // 清理旧的定时器
      this.clearTimers();

      if (this.refreshSettings.enabled) {
        // 设置转录刷新定时器
        if (this.refreshSettings.transcriptInterval > 0) {
          this.transcriptTimer = setInterval(() => {
            if (this.controlForm.roomId) {
              this.fetchTranscript();
            }
          }, this.refreshSettings.transcriptInterval * 1000);
        }

        // 设置状态刷新定时器
        if (this.refreshSettings.statusInterval > 0) {
          this.statusTimer = setInterval(() => {
            if (this.controlForm.roomId) {
              this.fetchMinutesStatus();
              this.fetchMinutes();
            }
          }, this.refreshSettings.statusInterval * 1000);
        }
      }
    },

    // 清理定时器
    clearTimers() {
      if (this.transcriptTimer) {
        clearInterval(this.transcriptTimer);
        this.transcriptTimer = null;
      }
      if (this.statusTimer) {
        clearInterval(this.statusTimer);
        this.statusTimer = null;
      }
    },

    // 保存刷新设置
    saveRefreshSettings() {
      this.refreshDialogVisible = false;
      this.setupAutoRefresh();
    }
  }
};
</script>

<style lang="scss" scoped>
.app-container {
  padding: 20px;
}

.mb8 {
  margin-bottom: 8px;
}

.box-card {
  width: 100%;
}

.info-section h4 {
  margin: 0 0 15px 0;
  font-size: 16px;
  color: #606266;
}

.status-info {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.status-details p {
  margin: 5px 0;
  font-size: 14px;
  color: #606266;
}

.control-panel {
  padding: 10px 0;
}

.transcript-container,
.minutes-container {
  width: 100%;
}

.status-container {
  padding: 10px 0;
}

.status-container p {
  margin: 8px 0;
  font-size: 14px;
}

.no-data {
  color: #909399;
  font-style: italic;
}

.el-button-group {
  margin-right: 10px;
}

.dialog-footer {
  text-align: right;
}
</style>