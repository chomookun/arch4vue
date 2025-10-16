<template>
  <h1>Hub And Spoke Detail</h1>

  <!-- ====================================== -->
  <!-- start: detail                          -->
  <!-- ====================================== -->
  <div class="panel">
    <div class="panel-title">
      <h2>
        Detail
      </h2>
    </div>
    <div class="panel-content">
      <form onsubmit="return false;" class="display--grid grid-template-columns--12fr gap--1em">
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Name
            <v-input v-model="example.name" type="text" class="font-weight--bold"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Icon
            <div>
              <img :src="example.icon || '/static/image/icon-null.svg'" class="icon" style="width:32px; height:32px;" alt=""/>
            </div>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Number
            <v-input v-model="example.number" type="number" class="number"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            DecimalNumber
            <v-input v-model="example.decimalNumber" type="number" class="number"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            DateTime
            <v-input v-model="example.dateTime" type="datetime-local" class="date"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            InstantDateTime
            <v-input v-model="example.instantDateTime" type="datetime-local" class="date"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            ZonedDateTime
            <v-input v-model="example.zonedDateTime" type="datetime-local" class="date"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Date
            <v-input v-model="example.date" type="date" class="date"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Time
            <v-input v-model="example.time" type="time" class="date"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label>
            Enabled
            <br/>
            <v-input v-model="example.enabled" type="checkbox"/>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Type
            <v-select v-model="example.type"
                      :options="types"
                      :option-value-property="value"
                      :option-text-property="name" class="code">
              <option>...</option>
            </v-select>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Code
            <v-select v-model="example.code"
                      :options="codes"
                      :option-value-property="itemId"
                      :option-text-property="name">
              <option>...</option>
            </v-select>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            Text
            <v-textarea v-model="example.text" class="width--100"></v-textarea>
          </label>
        </div>
        <div class="grid-column--span-6 s__grid-column--span-12">
          <label class="display--flex flex-direction--column">
            CryptoText
            <v-textarea v-model="example.cryptoText"></v-textarea>
          </label>
        </div>
      </form>
      <div class="display--flex justify-content--space-between">
        <div></div>
        <div class="display--flex justify-content--end gap--1px">
          <button type="button" @click="deleteExample();">
            Delete
          </button>
          <button type="button" @click="saveExample();">
            Save
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- ====================================== -->
  <!-- end: detail                            -->
  <!-- ====================================== -->
</template>

<script setup>
import * as Vue from 'vue';
import { useRoute } from 'vue-router'
import { DataHandler } from 'vue-component';
import { VInput, VSelect, VTextarea } from 'vue-component';
import axios from "axios";

const route = useRoute()
const exampleId = route.query.exampleId
console.log("== exampleId:", exampleId);

const example = Vue.reactive({});
const types = Vue.reactive([
  {value:"HUMAN", text:"Human"}
]);
const codes = Vue.reactive([
  { itemId: 'STUDENT', name: 'Student'}
]);

// on mounted
Vue.onMounted(() => {
  window.example = example;
  getExample(exampleId);
});

/**
 * Gets current example 
 */
function getExample() {
  axios.get(`/api/examples/${exampleId}`, {})
      .then(response => {
        console.log(response.data);
        DataHandler.assign(example, response.data);
      }).catch(error => {
        console.error(error);
      });
}

/**
 * Saves current example
 */
async function saveExample() {
  if (confirm('Do you want to save?')) {
    if (exampleId) {
      axios.put(`/api/examples/${exampleId}`, example).then(response => {
        getExample();
      });
    } else {
      axios.post('/api/examples', example).then(response => {
        let url = new URL(location.pathname, location.origin);
        url.searchParams.set('exampleId', response.data.exampleId);
        location.replace(url.href);
      });
    }
  }
}

/**
 * Deletes current example
 */
function deleteExample() {
  if (confirm('Do you want to delete?'))
  axios.delete(`/api/examples/${exampleId}`).then(response => {
    alert('Delete Completed.');
    let url = new URL('/example/hub-and-spoke', location.origin);
    location.replace(url.href);
  });
}
</script>

<style scoped>
</style>