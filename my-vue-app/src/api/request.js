import axios from "axios";
import { ElMessage } from "element-plus";
const service = axios.create({
  timeout: 5000,
});

service.interceptors.request.use(
  (response) => {
    // 将data值取出
    const data = response.data;
    // 判断状态 0为成功 -1失败
    if (data.code === 200) {
      // 成功
      return data;
    } else {
      // 失败，提示错误信息
      ElMessage.error(data.message);
      return Promise.reject(new Error(data.message));
    }
  },
  (error) => {
    // 对请求错误做些什么
    error.response && ElMessage.error(error.response.data);
    return Promise.reject(new Error(error.response.data));
  }
);

export default service;