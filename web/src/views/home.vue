<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
          :openKeys="openKeys"
      >
        <!--        <a-menu-item key="welcome">-->
        <!--          <MailOutlined />-->
        <!--          <span>欢迎</span>-->
        <!--        </a-menu-item>-->
        <a-menu-item key="">
          <a-icon type="appstore"/>
          <span>全部</span>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title>
            <span><user-outlined/>{{ item.name }}</span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined/>
            <span>{{ child.name }}</span>
          </a-menu-item>
        </a-sub-menu>
        <a-menu-item key="tip" :disabled="true">
          <span>以上菜单在分类管理配置</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout style="padding: 0 24px 24px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>Home</a-breadcrumb-item>
        <a-breadcrumb-item>List</a-breadcrumb-item>
        <a-breadcrumb-item>App</a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <div class="welcome" v-show="isShowWelcome">
          <the-welcome></the-welcome>
        </div>
        <a-list item-layout="vertical" size="large" :data-source="ebooks" :grid="{ gutter: 20, column: 3 }">
          <template #renderItem="{ item }">
            <a-list-item key="item.name">
              <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component :is="type" style="margin-right: 8px"/>
            {{ text }}
          </span>
              </template>
              <a-list-item-meta :description="item.description">
                <template #title>
                  <router-link :to="'/doc?ebookId=' + item.id">
                      {{ item.name }}
                  </router-link>
<!--                  <a :href="item.href">{{ item.name }}</a>-->
                </template>
                <template #avatar>
                  <a-avatar :src="item.cover"/>
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, onMounted, reactive, ref, toRef} from 'vue';
import axios from 'axios';
import {Tool} from "@/util/tool";
import {message} from 'ant-design-vue';
import TheWelcome from '@/components/the-welcome.vue';

const listData: Record<string, string>[] = [];

export default defineComponent({
  name: 'HomeView',
  components: {
    TheWelcome
  },
  setup() {
    const ebooks = ref();
    const level1 = ref();
    let categorys: any;
    const openKeys = ref();
    const ebooks1 = reactive({
      books: []
    });
    /**
     * 查询所有分类
     **/
    const handleQueryCategory = () => {
      axios.get("/category/all").then((response) => {
        const data = response.data;
        if (data.success) {
          categorys = data.content;
          console.log("原始数组：", categorys);

          // 加载完分类后，将侧边栏全部展开
          openKeys.value = [];
          for (let i = 0; i < categorys.length; i++) {
            openKeys.value.push(categorys[i].id)
          }

          level1.value = [];
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构：", level1.value);

          // 加载完后再加载电子书，否则如果分类树加载过慢，电子书加载不了
          handleQuery();
        } else {
          message.error(data.message);
        }
      });
    };

    const pagination = {
      onChange: (page: number) => {
        console.log(page);
      },
      pageSize: 3,
    };
    const actions: Record<string, string>[] = [
      {type: 'StarOutlined', text: '156'},
      {type: 'LikeOutlined', text: '156'},
      {type: 'MessageOutlined', text: '2'},
    ];

    const isShowWelcome = ref(true);
    let categoryId2 = 0;
    const handleQueryEbook = () => {
      axios.get("/ebook/list", {
        params: {
          page: 1,
          size: 1000,
          categoryId2: categoryId2
        }
      }).then((response) => {
        const data = response.data;
        ebooks.value = data.content.list;
        // ebooks1.books = data.content;
      });
    };

    const handleClick = (value: any) => {
      // console.log("menu click", value)
      if (value.key === 'welcome') {
        isShowWelcome.value = true;
      } else {
        categoryId2 = value.key;
        isShowWelcome.value = false;
        handleQueryEbook();
      }
      // isShowWelcome.value = value.key === 'welcome';
    };

    function handleQuery() {
      axios.get('/ebook/list').then(res => {
        const data = res.data;
        ebooks.value = data.content.list;
        ebooks1.books = data.content;
      });
    }

    onMounted(() => { // onMounted is a Vue.js lifecycle hook (onMounted 是一个 Vue.js 生命周期钩子)
      handleQueryCategory();
      // handleQuery();
    });
    return {
      ebooks,
      books: toRef(ebooks1, "books"),
      listData,
      pagination,
      actions,
      handleClick,
      openKeys,
      level1,
      isShowWelcome
    }
  }
});
</script>

<style scoped>
.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}

.ant-layout.ant-layout-has-sider {
  height: 86vh;
}
</style>