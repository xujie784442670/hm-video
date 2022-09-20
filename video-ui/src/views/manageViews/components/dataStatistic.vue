<template>
    <div style="display: flex">
        <div id="user">
            <h1>用户统计</h1>
            <div id="userView"></div>
        </div>
        <div id="type">
            <data-centre/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "dataStatistic",
    }
</script>
<script setup>
    import { Chart } from '@antv/g2';
    import { onMounted, ref } from 'vue'
    import { getUserView } from '../Controller.js'
    import { ElMessage } from 'element-plus'
    import dataCentre from '../../CreativeCenter/dataCentre.vue'
    import DataCentre from '../../CreativeCenter/dataCentre.vue'

    const data = ref([]);

    let lastItem;
    onMounted(() => {
        const chart = new Chart({
            container: 'userView',
            autoFit: true,
            height: 500,
        });
        getUserView().then(rs => {
            if (rs.data.code === 0) {
                data.value = rs.data.data
                // 新建一个 view 用来单独渲染Annotation
                const innerView = chart.createView();

                function drawTotal () {
                    const total = ref(0)
                    data.value.filter((item) => {
                        total.value += item.count
                    })
                    innerView.annotation().text({
                        position: ['50%', '50%'],
                        content: '总用户人数',
                        style: {
                            fontSize: 20,
                            fill: '#8c8c8c',
                            textAlign: 'center',
                        },
                        offsetY: -20,
                    }).text({
                        position: ['50%', '50%'],
                        content: total.value,
                        style: {
                            fontSize: 28,
                            fill: '#8c8c8c',
                            textAlign: 'center',
                        },
                        offsetX: -15,
                        offsetY: 20,
                    }).text({
                        position: ['50%', '50%'],
                        content: '人',
                        style: {
                            fontSize: 20,
                            fill: '#8c8c8c',
                            textAlign: 'center',
                        },
                        offsetY: 20,
                        offsetX: 25,
                    });
                    innerView.render()
                }

                chart.coordinate('theta', {
                    radius: 0.75,
                    innerRadius: 0.5,
                });

                chart.data(data.value);

                chart.scale('percent', {
                    formatter: val => {
                        val = val * 100 + '%';
                        return val;
                    },
                });

                chart.tooltip(false);

                // 声明需要进行自定义图例字段： 'item'
                chart.legend('item', {
                    // 配置图例显示位置
                    position: 'bottom',
                    layout: 'horizontal',
                    // 关键字段，告诉 G2，要使用自定义的图例
                    custom: true,
                    items: data.value.map((obj, index) => {
                        return {
                            // 对应 itemName
                            name: obj.item,
                            // 对应 itemValue
                            value: {
                                count: obj.count,
                                percent: obj.percent,
                            },
                            // marker 配置
                            marker: {
                                // marker 的形状
                                symbol: 'square',
                                style: {
                                    // marker 图形半径
                                    r: 5,
                                    // marker 颜色，使用默认颜色，同图形对应
                                    fill: chart.getTheme().colors10[index],
                                },
                            },
                        };
                    }),
                    maxItemWidth: 400,
                    itemName: {
                        style: {
                            fontSize: 20,
                        },
                    },
                    // 配置 itemValue 样式
                    itemValue: {
                        style: {
                            fill: '#999',
                            fontSize: 20,
                        },
                        // 格式化 itemValue 内容
                        formatter: val => `${val.count}人 占 ` + `${val.percent * 100}%`,
                    },
                });

                chart.interval().adjust('stack').position('percent').color('item').style({
                    fillOpacity: 1,
                }).state({
                    active: {
                        style: element => {
                            const shape = element.shape;
                            return {
                                lineWidth: 10,
                                stroke: shape.attr('fill'),
                                strokeOpacity: shape.attr('fillOpacity'),
                            };
                        },
                    },
                });

                // 移除图例点击过滤交互
                chart.removeInteraction('legend-filter');
                chart.interaction('element-active');
                chart.render();
                drawTotal()

                // 监听 element 上状态的变化来动态更新 Annotation 信息
                chart.on('element:statechange', (ev) => {
                    const { state, stateStatus, element } = ev.gEvent.originalEvent;
                    // 本示例只需要监听 active 的状态变化
                    if (state === 'active') {
                        const data = element.getData();
                        if (stateStatus) {
                            // 更新 Annotation
                            updateAnnotation(data);
                        } else {
                            // 隐藏 Annotation
                            clearAnnotation();
                        }
                    }
                });

                // 绘制 annotation
                function updateAnnotation (data) {
                    if (data.item !== lastItem) {
                        innerView.annotation().clear(true);
                        innerView.annotation().text({
                            position: ['50%', '50%'],
                            content: data.item,
                            style: {
                                fontSize: 20,
                                fill: '#8c8c8c',
                                textAlign: 'center',
                            },
                            offsetY: -20,
                        }).text({
                            position: ['50%', '50%'],
                            content: data.count,
                            style: {
                                fontSize: 28,
                                fill: '#8c8c8c',
                                textAlign: 'center',
                            },
                            offsetX: -10,
                            offsetY: 20,
                        }).text({
                            position: ['50%', '50%'],
                            content: '人',
                            style: {
                                fontSize: 20,
                                fill: '#8c8c8c',
                                textAlign: 'center',
                            },
                            offsetY: 20,
                            offsetX: 20,
                        });
                        innerView.render(true);
                        lastItem = data.item;
                    }
                }

                // 清空 annotation
                function clearAnnotation () {
                    innerView.annotation().clear(true);
                    innerView.render(true);
                    lastItem = null;
                    drawTotal()
                }
            } else {
                ElMessage.error({
                    message: rs.data.message,
                    grouping: true,
                })
            }
        }).catch((e) => {
            ElMessage.error({
                message: e.data.message,
                grouping: true,
            })
        })
    })
</script>

<style scoped>
    #user {
        width: 50%;
        height: 100%;
        text-align: center;
    }

    h1 {
        transform: translateY(40px);
    }
</style>