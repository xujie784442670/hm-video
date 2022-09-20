class Barrage{
    constructor(options,ctx) {
        this.ctx=ctx;
        this.isInit=false;
        console.log(options,'===options')
        Object.assign(this,options)
    }
    getWidth(){
        const span=document.createElement('span');
        console.log(this.content)
        span.innerText= this.content;
        span.style.font=this.fontSize+'px Microsoft YaHei';
        span.style.position='absolute';
        document.body.appendChild(span)
        let width = span.clientWidth;
        document.body.removeChild(span)
        return width
    }
    init(){
        console.log("弹幕初始化")
        //初始化弹幕
        this.color=this.color||this.ctx.color;
        this.fontSize=this.fontSize||this.ctx.fontSize;
        this.speed=this.speed||this.ctx.speed;
        this.width=this.getWidth();
        this.x=this.ctx.canvas.width;
        this.y=Math.floor(this.ctx.canvas.height*Math.random());
        if (this.y > this.ctx.height-this.fontSize){
            this.y=this.ctx.height-this.fontSize
        }
        if (this.y <this.fontSize){
            this.y=this.fontSize
        }
    }
    render() {
        this.ctx.context.font=this.fontSize+'px microsoft YaHei';
        this.ctx.context.fillStyle=this.color;
        this.ctx.context.fillText(this.content,this.x,this.y)
    }
}

class Barrages{
    constructor(options) {
        if (!options.canvas ||!options.video){
            return;
        }
        const dafaultOptions={
            fontSize: 26,
            color : '#fff',
            data:[],
            speed: 1,
        }
        //将属性归并到this上
        Object.assign(this,dafaultOptions,options)
        console.log(this.canvas)
        let height = this.video.offsetHeight;
        let width = this.video.offsetWidth;
        this.isPlay=true
        console.log()
        //初始化canvas
        this.canvas.width=width
        this.canvas.height=height
        this.context=this.canvas.getContext('2d')
        //存储弹幕数据
        this.barrages=this.data.map(item =>new Barrage(item,this))
    }
    clear(){
        //清空画布
        this.context.clearRect(0,0,this.canvas.width,this.canvas.height )
    }
    render(){
        //清空canvas
        this.clear()
        //绘制新画布
        this.renderBarrages()
        if (this.isPlay){
            requestAnimationFrame((this.render.bind(this)))
        }

    }
    //绘制弹幕
    renderBarrages(){
        //获取当前视频的时间
        // console.log('render')
        const time = this.video.currentTime;
        this.barrages.forEach(item=>{
            if (item.time <= time){
                if (!item.isInit){
                    item.init()
                    item.isInit=true;
                }
                item.x=item.x-this.speed
                item.render()
            }
        })
    }
    pause(){
        this.isPlay=false
        console.log('pause')
    }
    //插入弹幕
    setBarrage(data){
        this.data.push(data)
        this.barrages.push(new Barrage(data,this))
    }
}
export default Barrages