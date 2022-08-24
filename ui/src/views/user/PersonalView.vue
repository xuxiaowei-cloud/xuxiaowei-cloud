<template>
  <el-header></el-header>
  <el-container>
    <el-form :model="cloudForm" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="cloudForm.username" readonly/>
      </el-form-item>

      <el-form-item label="用户昵称">
        <el-input v-model="cloudForm.nickname" :readonly="nicknameReadonly">
          <template #append>
            <el-button @click="nicknameEdit">{{ nicknameButton }}</el-button>
          </template>
        </el-input>
      </el-form-item>

      <el-form-item label="性别">
        <el-input v-if="sexReadonly" v-model="cloudForm.sexLabel" :readonly="sexReadonly">
          <template #append>
            <el-button @click="sexEdit">编辑</el-button>
          </template>
        </el-input>

        <el-select v-if="!sexReadonly" v-model="cloudForm.sex" clearable placeholder="Select sex">
          <el-option v-for="item in sexOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
        <el-button v-if="!sexReadonly" @click="sexSave">保存</el-button>
      </el-form-item>

      <el-form-item label="出生日期">
        <el-input v-if="birthdayReadonly" v-model="cloudForm.birthday" :readonly="birthdayReadonly">
          <template #append>
            <el-button @click="birthdayEdit">编辑</el-button>
          </template>
        </el-input>
        <el-date-picker v-if="!birthdayReadonly" v-model="cloudForm.birthday" type="date" placeholder="Select birthday"
                        value-format="YYYY-MM-DD" format="YYYY-MM-DD"/>
        <el-button v-if="!birthdayReadonly" @click="birthdaySave">保存</el-button>
      </el-form-item>

      <el-form-item label="地址">
        <el-input v-if="addressReadonly" v-model="cloudForm.address" :readonly="addressReadonly">
          <template #append>
            <el-button @click="addressEdit">编辑</el-button>
          </template>
        </el-input>

        <div v-if="!addressReadonly">
          <el-select clearable filterable v-model="cloudForm.provinceCode" placeholder="请选择省份">
            <el-option v-for="item in provinceOptions" :key="item.provinceCode" :value="item.provinceCode"
                       :label="item.provinceName"/>
          </el-select>
          <el-select clearable filterable v-model="cloudForm.cityCode" placeholder="请选择城市"
                     :disabled="cloudForm.provinceCode === undefined || cloudForm.provinceCode === ''">
            <el-option v-for="item in cityOptions" :key="item.cityCode" :value="item.cityCode" :label="item.cityName"/>
          </el-select>
          <el-select clearable filterable v-model="cloudForm.countyCode" placeholder="请选择区/县"
                     :disabled="cloudForm.cityCode === undefined || cloudForm.cityCode === ''">
            <el-option v-for="item in countyOptions" :key="item.countyCode" :value="item.countyCode"
                       :label="item.countyName"/>
          </el-select>
          <el-select clearable filterable v-model="cloudForm.townCode" placeholder="请选择镇"
                     :disabled="cloudForm.countyCode === undefined || cloudForm.countyCode === ''">
            <el-option v-for="item in townOptions" :key="item.townCode" :value="item.townCode" :label="item.townName"/>
          </el-select>
          <el-select clearable filterable v-model="cloudForm.villageCode" placeholder="请选择居委会"
                     :disabled="cloudForm.townCode === undefined || cloudForm.townCode === ''">
            <el-option v-for="item in villageOptions" :key="item.villageCode" :value="item.villageCode"
                       :label="item.villageName"/>
          </el-select>
          <el-input v-model="cloudForm.detailedAddress" placeholder="请输入详细地址">
            <template #append>
              <el-button @click="addressSave">保存</el-button>
            </template>
          </el-input>
        </div>
      </el-form-item>

    </el-form>
  </el-container>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { page as provincePage } from '../../api/master-data/province-handle'
import { page as cityPage } from '../../api/master-data/city-handle'
import { page as countyPage } from '../../api/master-data/county-handle'
import { page as townPage } from '../../api/master-data/town-handle'
import { page as villagePage } from '../../api/master-data/village-handle'

const cloudForm = reactive({
  username: null,
  nickname: null,
  sex: null,
  sexLabel: null,
  birthday: null,
  address: null,

  provinceCode: null,
  provinceName: null,
  cityCode: null,
  cityName: null,
  countyCode: null,
  countyName: null,
  townCode: null,
  townName: null,
  villageCode: null,
  villageName: null,

  detailedAddress: null
})

const nicknameReadonly = ref<boolean>(true)
const nicknameButton = ref('编辑')
const nicknameEdit = () => {
  if (nicknameReadonly.value) {
    nicknameReadonly.value = false
    nicknameButton.value = '保存'
  } else {
    nicknameReadonly.value = true
    nicknameButton.value = '编辑'
  }
}

const sexReadonly = ref<boolean>(true)
// 个人基本信息分类与代码 第1部分:人的性别代码 https://openstd.samr.gov.cn/bzgk/gb/newGbInfo?hcno=0FC942D542BC6EE3C707B2647EF81CD8
const sexOptions = [
  {
    value: 0,
    label: '未知的性别'
  },
  {
    value: 1,
    label: '男性'
  },
  {
    value: 2,
    label: '女性'
  },
  {
    value: 9,
    label: '未说明的性别'
  }
]
const sexEdit = () => {
  sexReadonly.value = !sexReadonly.value
}
const sexSave = () => {
  sexReadonly.value = !sexReadonly.value
}
watch(() => cloudForm.sex, (newValue, oldValue) => {
  if (newValue != null) {
    for (const i in sexOptions) {
      const item = sexOptions[i]
      if (newValue === item.value) {
        // @ts-ignore
        cloudForm.sexLabel = item.label
      }
    }
  }
})

const birthdayReadonly = ref<boolean>(true)
const birthdayEdit = () => {
  birthdayReadonly.value = !birthdayReadonly.value
}
const birthdaySave = () => {
  birthdayReadonly.value = !birthdayReadonly.value
}

const addressReadonly = ref<boolean>(true)
const addressEdit = () => {
  addressReadonly.value = !addressReadonly.value
}

interface ProvinceOption {
  year: number;
  provinceCode: number;
  provinceName: string;
}

const provinceOptions = ref<ProvinceOption[]>([])

// 一共31个省
provincePage({ size: 40 }).then(response => {
  provinceOptions.value = response.data.records
})

interface CityOption {
  provinceCode: number;
  cityCode: number;
  cityName: string;
}

const cityOptions = ref<CityOption[]>()

watch(() => cloudForm.provinceCode, (newValue, oldValue) => {
  cloudForm.cityCode = null
  if (newValue == null || newValue === '') {
    cityOptions.value = []
    cloudForm.provinceName = null
  } else {
    // 省份管辖下，最多有21个城市
    cityPage({ provinceCode: newValue, size: 30 }).then(response => {
      cityOptions.value = response.data.records
    })
  }
})

interface CountyOption {
  cityCode: number;
  countyCode: number;
  countyName: string;
}

const countyOptions = ref<CountyOption[]>()

watch(() => cloudForm.cityCode, (newValue, oldValue) => {
  cloudForm.countyCode = null
  if (newValue == null || newValue === '') {
    countyOptions.value = []
  } else {
    // 城市管辖下，最多有26个县
    countyPage({ cityCode: cloudForm.cityCode, size: 30 }).then(response => {
      countyOptions.value = response.data.records
    })
  }
})

interface TownOption {
  countyCode: number;
  townCode: number;
  townName: string;
}

const townOptions = ref<TownOption[]>()

watch(() => cloudForm.countyCode, (newValue, oldValue) => {
  cloudForm.townCode = null
  if (newValue == null || newValue === '') {
    townOptions.value = []
  } else {
    // 县管辖下，最多有52个镇
    townPage({ countyCode: cloudForm.countyCode, size: 60 }).then(response => {
      townOptions.value = response.data.records
    })
  }
})

interface VillageOption {
  townCode: number;
  villageCode: number;
  villageName: string;
  villageTypeCode: string;
}

const villageOptions = ref<VillageOption[]>()

watch(() => cloudForm.townCode, (newValue, oldValue) => {
  cloudForm.villageCode = null
  if (newValue == null || newValue === '') {
    villageOptions.value = []
  } else {
    // 镇管辖下，最多有168居委会
    villagePage({ townCode: cloudForm.townCode, size: 170 }).then(response => {
      villageOptions.value = response.data.records
      console.log(villageOptions.value)
    })
  }
})

const addressSave = () => {
  addressReadonly.value = !addressReadonly.value
}

</script>

<style scoped>

</style>
