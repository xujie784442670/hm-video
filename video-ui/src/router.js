import { createRouter, createWebHistory } from "vue-router";
import useUser from './http/stores/user.js'

const routes = [


  {
    path: '/setting',
    name: 'setting',
    component: import('./views/personalCenter/setting.vue'),
    children: [

      {
        path: '/showinfo',
        name: 'showinfo',
        component: import('./views/personalCenter/showinfo.vue')
      },
      {
        path: '/infoeditor',
        name: 'infoeditor',
        component: import('./views/personalCenter/infoeditor.vue')
      },
      {
        path: '/countcontrol',
        name: 'countcontrol',
        component: import('./views/personalCenter/countcontrol.vue')
      },
      {
        path: '/imageUp',
        name: 'imageUp',
        component: import('./views/personalCenter/imageUp.vue')
      },

    ]
  },


  {
    path: '/manage',
    name: 'manage',
    component: import('./views/manageViews/index.vue'),
    children: [
      {
        path: '/videoManage',
        name: 'videoManage',
        component: import('./views/manageViews/components/manageVideo.vue'),
      },
      {
        path: '/reportManage',
        name: 'reportManage',
        component: import('./views/manageViews/components/manageReport.vue'),
      },
      {
        path: '/advertiseManage',
        name: 'advertiseManage',
        component: import('./views/manageViews/components/manageAdvertise.vue'),
      },
      {
        path: '/dataStatistic',
        name: 'dataStatistic',
        component: import('./views/manageViews/components/dataStatistic.vue'),
      },
    ],
  },
  {
    path: '/login',
    name: 'login',
    component: import('./views/Login.vue'),
  },
  {
    path: '/register',
    name: 'register',
    component: import('./views/register.vue'),
  },
  {
    path: '/home',
    name: 'home',
    component: import('./views/home/Index.vue'),
  },
  {
    path: '/videoPlay/:videoUrl/:videoId',
    name: 'videoPlay',
    component: import('./views/video/index.vue'),
    props: true,
  },

  {
    path: '/heCenter/:userId',
    name: 'heCenter',
    component: import('./views/personalCenter/heCenter.vue'),
    props: true

  },
  {
    path: '/fileUpload',
    name: 'fileUpload',
    component: import('./views/personalCenter/fileUpload/fileUpload.vue'),

  },
  {
    path: '/personalCenter',
    name: 'personalCenter',
    component: import('./views/personalCenter/personalCenter.vue'),
    children: [
      {
        path: '/home',
        component: import('./views/personalCenter/home.vue'),
      },
      {
        path: '/submissionVideo',
        component: import('./views/personalCenter/submissionVideo.vue'),
      },
      {
        path: '/information',
        name: 'information',
        component: import('./views/personalCenter/messageCenter/index.vue'),
      },
      {
        path: '/collect',
        component: import('./views/personalCenter/collect.vue'),
      },
      {
        path: '/setting',
        name: 'setting',
        component: import('./views/personalCenter/setting.vue'),
        children: [

          {
            path: '/showinfo',
            name: 'showinfo',
            component: import('./views/personalCenter/showinfo.vue'),
          },
          {
            path: '/infoeditor',
            name: 'infoeditor',
            component: import('./views/personalCenter/infoeditor.vue'),
          },
          {
            path: '/countcontrol',
            name: 'countcontrol',
            component: import('./views/personalCenter/countcontrol.vue'),
          },
          {
            path: '/imageUp',
            name: 'imageUp',
            component: import('./views/personalCenter/imageUp.vue'),
          },

        ],
      },
      {
        path: '/focusList',
        name: 'FocusList',
        component: import('./views/personalCenter/FocusList.vue'),
      },
      {
        path: '/fansList',
        name: 'fansList',
        component: import('./views/personalCenter/FansList.vue'),
      },
      {
        path: '/importantPerson2',
        name: 'importantPerson2',
        component: import('./views/personalCenter/importantPerson2.vue'),
      },
      {
        path: '/importantPerson',
        name: 'importantPerson',
        component: import('./views/personalCenter/importantPerson.vue'),
      },
    ],
  },
  {
    path: "/videoList",
    name: "videoList",
    component: import('./views/videoList/index.vue'),
  },
  {
       path: "/videoList",
       name: "videoList",
        component: import('./views/videoList/index.vue')
    },

  {
    path: '/create',
    name:'create',
    component: import('./views/CreativeCenter/index.vue'),
    children: [
       {
         path: '/main',
         component: import('./views/CreativeCenter/createMain/main.vue'),
       },
       {
         path: '/contManage',
         name:'contManage',
         component: import('./views/CreativeCenter/contManage.vue'),
       },
       {
         path: '/commentMa',
         component: import('./views/CreativeCenter/commentMa.vue'),
       },
       {
         path: '/dateCentre',
         component: import('./views/CreativeCenter/dataCentre.vue'),
       },
       {
         path: '/barrageManagement',
         component: import('./views/CreativeCenter/barrageManagement.vue'),

       },
      {
        path: '/fileUpload',
        name: 'fileUpload',
        component: import('./views/personalCenter/fileUpload/fileUpload.vue'),

      }
     ]
  }

]

const router = createRouter({
  routes,
  history: createWebHistory(),
})
router.beforeEach((to, from, next) => {
  const user = useUser()
  if (to.path === '/') {
    next({ name: 'home' })
  }
  if (to.name === 'login' || to.name === 'home' || to.name === 'register' ||
    to.name === 'videoPlay' || to.name === 'videoList') {
    next()
  }
  if (!user.token) {
    next({ name: 'home' })
  } else (
    next()
  )
})
export default router;