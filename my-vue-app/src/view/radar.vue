<template>
    <div id="main">

    </div>
</template>

<script setup>
import { onMounted, getCurrentInstance, onBeforeUnmount, inject, ref, reactive } from "vue";
import axios from 'axios';

let echarts = inject("$echarts");


onMounted(() => {
    show();
})

const show = () => {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    // 从后端读取数据
    let dataBJ = ref([]);
    let dataGZ = ref([]);
    let dataSH = ref([]);

    axios.get("/api/data/radarData").then(
        res => {
            dataBJ = res.data.data[0];
            dataGZ = res.data.data[1];
            dataSH = res.data.data[2];

            const lineStyle = {
                width: 1,
                opacity: 0.5
            };
            option = {
                backgroundColor: '#161627',
                title: {
                    text: 'AQI - Radar',
                    left: 'center',
                    textStyle: {
                        color: '#eee'
                    }
                },
                legend: {
                    bottom: 5,
                    data: ['Beijing', 'Shanghai', 'Guangzhou'],
                    itemGap: 20,
                    textStyle: {
                        color: '#fff',
                        fontSize: 14
                    },
                    selectedMode: 'single'
                },
                radar: {
                    indicator: [
                        { name: 'AQI', max: 100 },
                        { name: 'PM2.5', max: 100 },
                        { name: 'PM10', max: 100 },
                        { name: 'CO', max: 100 },
                        { name: 'NO2', max: 100 },
                        { name: 'SO2', max: 100 }
                    ],
                    shape: 'circle',
                    splitNumber: 5,
                    axisName: {
                        color: 'rgb(238, 197, 102)'
                    },
                    splitLine: {
                        lineStyle: {
                            color: [
                                'rgba(238, 197, 102, 0.1)',
                                'rgba(238, 197, 102, 0.2)',
                                'rgba(238, 197, 102, 0.4)',
                                'rgba(238, 197, 102, 0.6)',
                                'rgba(238, 197, 102, 0.8)',
                                'rgba(238, 197, 102, 1)'
                            ].reverse()
                        }
                    },
                    splitArea: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'rgba(238, 197, 102, 0.5)'
                        }
                    }
                },
                series: [
                    {
                        name: 'Beijing',
                        type: 'radar',
                        lineStyle: lineStyle,
                        data: dataBJ,
                        symbol: 'none',
                        itemStyle: {
                            color: '#F9713C'
                        },
                        areaStyle: {
                            opacity: 0.1
                        }
                    },
                    {
                        name: 'Shanghai',
                        type: 'radar',
                        lineStyle: lineStyle,
                        data: dataSH,
                        symbol: 'none',
                        itemStyle: {
                            color: '#B3E4A1'
                        },
                        areaStyle: {
                            opacity: 0.05
                        }
                    },
                    {
                        name: 'Guangzhou',
                        type: 'radar',
                        lineStyle: lineStyle,
                        data: dataGZ,
                        symbol: 'none',
                        itemStyle: {
                            color: 'rgb(238, 197, 102)'
                        },
                        areaStyle: {
                            opacity: 0.05
                        }
                    }
                ]
            };

            option && myChart.setOption(option);
        }
    )
    // Schema:
    // date,AQIindex,PM2.5,PM10,CO,NO2,SO2


}

</script>

<style scoped>
#main {
    height: 100vh;
    width: 100vw;
}
</style>