<template>
  <div class="test">
    <h2>Test 화면입니다</h2>
    <p>이 컴포넌트는 Vue에서 새로 추가된 테스트 화면입니다.</p>
  </div>
  <XInput v-model="myData.email" label="Email" />
  <hr/>
  <XTable :columns="3">
    <div v-for="(user, index) in myArray" :key="index">
      <XInput v-model="user.name" :label="`Name ${index + 1}`" />
      <XInput v-model="user.email" :label="`Email ${index + 1}`" />
    </div>
  </XTable>
</template>

<script>
import { XInput, XTable } from 'vuejs-component';

export default {
  name: 'Test',
  components: {
    XInput,
    XTable
  },
  data() {
    return {
      myData: {
        email: 'test'
      },
      myArray: [
        {name: 'name1', email:'email1@xxx.com'},
        {name: 'name2', email:'email2@xxx.com'}
      ]
    }
  },
  watch: {
    'myData.email'(newVal, oldVal) {
      console.log(`email changed from ${oldVal} to ${newVal}`);
    },
    myArray: {
      handler(newVal, oldVal) {
        console.log('myArray changed:', oldVal, newVal);
      },
      deep: true
    }
  }
};
</script>

<style scoped>
.test {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
}
</style>