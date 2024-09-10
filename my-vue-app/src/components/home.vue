<template>
	<div id="abc">
		<router-link replace to="/echartsTest">
			<span>图表页</span>
		</router-link>
		<hr>
		<button @click="test">测试</button>
		<hr>
		<router-link replace to="/radar">
			<span>雷达图</span>
		</router-link>
		<hr>
		<router-link replace to="/elemTest">
			<span>elem测试</span>
		</router-link>
		<hr>
		<el-button @click="removeLocalToken()">
			退出登录
		</el-button>
	</div>
</template>

<script setup>
import { onMounted, getCurrentInstance, onBeforeUnmount, inject, ref, reactive } from "vue";
import axios from 'axios';
import { setLocalToken,removeLocalToken } from "../common/token.js";

const test = () => {
	axios.get('/api/data/test').then(
		res => {
			console.log(res.data.data)
		},
		err => {
			console.log("没通");
		}
	)
}

</script>



<style scoped>
.abc{
	color: #fff;
	height: 20vh;
	width: 10vw;
}
</style>
