<template>

  <el-select clearable filterable v-model="province" placeholder="请选择省份" size="large">
    <el-option v-for="item in provinceOptions" :key="item.provinceCode" :value="item.provinceCode"
               :label="item.provinceName"/>
  </el-select>

  <el-select clearable filterable v-model="city" placeholder="请选择城市" size="large"
             :disabled="province === undefined || province === ''">
    <el-option v-for="item in cityOptions" :key="item.cityCode" :value="item.cityCode" :label="item.cityName"/>
  </el-select>

  <el-select clearable filterable v-model="county" placeholder="请选择区/县" size="large"
             :disabled="city === undefined || city === ''">
    <el-option v-for="item in countyOptions" :key="item.countyCode" :value="item.countyCode" :label="item.countyName"/>
  </el-select>

  <el-select clearable filterable v-model="town" placeholder="请选择镇" size="large"
             :disabled="county === undefined || county === ''">
    <el-option v-for="item in townOptions" :key="item.townCode" :value="item.townCode" :label="item.townName"/>
  </el-select>

  <el-select clearable filterable v-model="village" placeholder="请选择居委会" size="large"
             :disabled="town === undefined || town === ''">
    <el-option v-for="item in villageOptions" :key="item.villageCode" :value="item.villageCode"
               :label="item.villageName"/>
  </el-select>

</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { page as provincePage } from '../../api/master-data/province-handle'
import { page as cityPage } from '../../api/master-data/city-handle'
import { page as countyPage } from '../../api/master-data/county-handle'
import { page as townPage } from '../../api/master-data/town-handle'
import { page as villagePage } from '../../api/master-data/village-handle'

// 省
const province = ref()

interface ProvinceOption {
  year: number;
  provinceCode: number;
  provinceName: string;
}

const provinceOptions = ref<ProvinceOption[]>()

// 一共31个省
provincePage({ size: 40 }).then(response => {
  provinceOptions.value = response.data.records
})

// 市
const city = ref()

interface CityOption {
  provinceCode: number;
  cityCode: number;
  cityName: string;
}

const cityOptions = ref<CityOption[]>()

watch(() => province.value, (newValue, oldValue) => {
  city.value = undefined
  if (newValue == null || newValue === '') {
    cityOptions.value = []
  } else {
    // 省份管辖下，最多有21个城市
    cityPage({ provinceCode: newValue, size: 30 }).then(response => {
      cityOptions.value = response.data.records
    })
  }
})

// 区/县
const county = ref()

interface CountyOption {
  cityCode: number;
  countyCode: number;
  countyName: string;
}

const countyOptions = ref<CountyOption[]>()

watch(() => city.value, (newValue, oldValue) => {
  county.value = undefined
  if (newValue == null || newValue === '') {
    countyOptions.value = []
  } else {
    // 城市管辖下，最多有26个县
    countyPage({ cityCode: city.value, size: 30 }).then(response => {
      countyOptions.value = response.data.records
    })
  }
})

// 镇
const town = ref()

interface TownOption {
  countyCode: number;
  townCode: number;
  townName: string;
}

const townOptions = ref<TownOption[]>()

watch(() => county.value, (newValue, oldValue) => {
  town.value = undefined
  if (newValue == null || newValue === '') {
    townOptions.value = []
  } else {
    // 县管辖下，最多有52个镇
    townPage({ countyCode: county.value, size: 60 }).then(response => {
      townOptions.value = response.data.records
    })
  }
})

// 居委会
const village = ref()

interface VillageOption {
  townCode: number;
  villageCode: number;
  villageName: string;
  villageTypeCode: string;
}

const villageOptions = ref<VillageOption[]>()

watch(() => town.value, (newValue, oldValue) => {
  village.value = undefined
  if (newValue == null || newValue === '') {
    villageOptions.value = []
  } else {
    // 镇管辖下，最多有168居委会
    villagePage({ townCode: town.value, size: 170 }).then(response => {
      villageOptions.value = response.data.records
      console.log(villageOptions.value)
    })
  }
})

</script>

<style scoped>

</style>
