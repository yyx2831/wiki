import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import * as Icons from '@ant-design/icons-vue'

const app = createApp(App);
app.use(store).use(router).use(antd).mount('#app')

// 全局使用图标
const icons: any = Icons;
for (const key in icons) {
    app.component(key, icons[key]);
}