import axios from 'axios'
import useUser from './stores/user.js'

const request=axios.create({
  baseURL: '/api',
  timeout: 30000,
})

request.interceptors.request.use((config)=>{
      const user=useUser()
  //检查用户是否登录
  if (user.token){
    //如果用户已登录，则将用户的令牌放到请求头中，并传递给服务器
    config.headers['level3-token']=user.token
  }
  return config;
},error =>{

} )

export default request