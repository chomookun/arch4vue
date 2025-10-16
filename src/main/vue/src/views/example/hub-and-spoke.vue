<template>
  <h1>HubAndSpoke</h1>
  <form onsubmit="return false;">
    <label>
      Name
      <v-input v-model="exampleSearch.name" type="text"/>
    </label>
    <label>
      Type
      <v-select v-model="exampleSearch.type"
                :options="types"
                option-value-property="value"
                option-text-property="text">
        <option value>...</option>
      </v-select>
    </label>
    <button type="submit" @click="getExamples(0);">Search</button>
  </form>
  <hr/>
  <table>
    <thead>
    <tr>
      <th>No</th>
      <th>Name</th>
      <th>Number</th>
      <th>DecimalNumber</th>
      <th>DateTime</th>
      <th>Date</th>
      <th>Time</th>
      <th>Enabled</th>
      <th>Type</th>
      <th>Code</th>
      <th>Items</th>
    </tr>
    </thead>
    <tbody v-for="(example, index) in examples" :key="index">
    <tr>
      <td>{{ index }}</td>
      <td>{{ example.name }}</td>
      <td>{{ example.number }}</td>
      <td>{{ example.decimalNumber }}</td>
      <td>{{ example.dateTime }}</td>
      <td>{{ example.date }}</td>
      <td>{{ example.time }}</td>
      <td><v-input v-model="example.enabled" type="checkbox"/></td>
      <td>
        <v-select v-model="example.type" :options="types">
        </v-select>
      </td>
      <td>
        <v-select v-model="example.code" :options="codes" option-value-property="itemId" option-text-proeprty="name">
        </v-select>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script setup>
import * as Vue from 'vue';
import { DataHandler } from 'vue-component';
import { VInput, VSelect } from 'vue-component';
// import VTextarea from 'vue-component';
import axios from "axios";

const exampleSearch = Vue.reactive({
  name: null,
  type: null,
  _page: 0
});
const examples = Vue.reactive([]);
const types = Vue.reactive([
  {value:"HUMAN", text:"Human"}
]);
const codes = Vue.reactive([
  { itemId: 'STUDENT', name: 'Student'}
]);

console.log(DataHandler);

// for debug
Vue.onMounted(() =>{
  window.examples = examples;
});

function getExamples(page) {
  exampleSearch._page = page;
  axios.get('/api/examples', {
    params: exampleSearch
  }).then(response => {
    console.log(response);
    DataHandler.assign(examples, response.data);
  }).catch(error => {
    console.error(error);
  });
}
</script>

<style scoped>
</style>