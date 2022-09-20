<template>
    <div style="text-align: center">
        <h1>每类视频播放量统计</h1>
        <div id="c1"></div>
    </div>
</template>

<script>
    export default {
        name: "dataCentre",
    }
</script>
<script setup>
    import { Chart } from '@antv/g2';
    import { onMounted, ref } from "vue";
    import request from '../../http/Request.js'

    let data = [
        { type: '游戏', views: 5 },
        { type: '生活', views: 3 },
    ];
    onMounted(() => {
        // Step 1: 创建 Chart 对象
        const chart = new Chart({
            container: 'c1', // 指定图表容器 ID
            width: 500,
            height: 500, // 指定图表高度
        });
        request.get("/data/typeViews").then(rs => {
            console.log(rs)
            data = rs.data.data
            console.log(data)
            // Step 2: 载入数据源
            chart.data(data);
            chart.legend({
                custom: true,
                position: "bottom",
                flipPage: false,
                items: [
                    {
                        name: "播放量",
                        marker: {
                            symbol: "square",
                            style: {
                                fill: "#6395f9",
                            },
                            clickable: false,
                        },
                    },
                ],
            })
            // Step 3: 创建图形语法，绘制柱状图
            chart.interval().position('type*views');
            // Step 4: 渲染图表
            chart.render();
        })

    })
</script>

<style scoped>
    h1{
        transform: translateY(42px);
    }
</style>