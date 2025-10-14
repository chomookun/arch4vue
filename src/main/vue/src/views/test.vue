<template>
  <div class="test">
    <h2>Test 화면입니다</h2>
    <p>이 컴포넌트는 Vue에서 새로 추가된 테스트 화면입니다.</p>
  </div>
  <v-input v-model="myData.email" label="Email" />
  <hr/>
  testfdsafdafdsa
  <hr/>

  <table>
    <thead>
    <tr>
      <th>No</th>
      <th>Name</th>
      <th>Email</th>
    </tr>
    </thead>
    <tbody v-for="(user, index) in myArray" :key="index">
    <tr>
      <td>
        {{index}}
      </td>
      <td>
        <v-input v-model="user.name" :label="`Name ${index + 1}`" />
      </td>
      <td>
        <v-input v-model="user.email" :label="`Email ${index + 1}`" />
      </td>
    </tr>
    </tbody>
  </table>

  <hr/>

    <div v-for="(user, index) in myArray" :key="index">
      <v-input type="text" v-model="user.name" disabled/>
      <v-input type="number" v-model="user.email"/>
    </div>
</template>

<script>
import { VInput } from 'vue-component';

export default {
  name: 'Test',
  components: {
    VInput
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
  mounted() {
    window.app = this
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