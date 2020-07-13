import Vue from 'vue'
import Router from 'vue-router'
import index from '../views/index'
import archive from '../views/archive'
import type from '../views/type'
import tag from '../views/tag'
import photo from '../views/photo'
import about from '../views/about'
import comment from '../views/comment'
import search from '../views/search'
import login from '../views/login'
import chart from '../views/admin/chart'
import category from '../views/admin/category'
import photos from '../views/admin/atlas'
import tags from '../views/admin/tags'
import person from "../views/admin/person"
import blogs from '../views/admin/blogs'
import comments from '../views/admin/comments'
import messages from '../views/admin/messages'
import system from '../views/admin/system'
import link from '../views/admin/link'
import blog from '../views/admin/blog'
import detail from "../views/detail"
import typeList from "../views/typeList"
import tagList from "../views/tagList"
import photoList from "../views/photoList"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/index'
    },
    {
      path: '/index',
      name: 'index',
      component: index,
      hidden: true
    },
    {
      path: '/detail',
      name: 'detail',
      component: detail,
      hidden: true
    },
    { path: '/archive',
      name: 'archive',
      component: archive,
      hidden: true
    },
    {
      path: '/type',
      name: 'type',
      component: type,
      hidden: true
    },
    {
      path: '/typeList',
      name: 'typeList',
      component: typeList,
      hidden: true
    },
    {
      path: '/tag',
      name: 'tag',
      component: tag,
      hidden: true
    },
    {
      path: '/tagList',
      name: 'tagList',
      component: tagList,
      hidden: true
    },
    {
      path: '/photo',
      name: 'photo',
      component: photo,
      hidden: true
    },
    {
      path: '/photoList',
      name: 'photoList',
      component: photoList,
      hidden: true
    },
    {
      path: '/about',
      name: 'about',
      component: about,
      hidden: true
    },
    {
      path: '/comment',
      name: 'comment',
      component: comment,
      hidden: true
    },
    {
      path: '/search',
      name: 'search',
      component: search,
      hidden: true
    },
    {
      path: '/login',
      name: 'login',
      component: login,
      hidden: true
    },
    {
      path: '/admin_person_list',
      name: 'person',
      meta: { requireAuth: true },
      component: person,
      hidden: true
    },
    { path: '/admin_chart',
      name: 'chart',
      meta: { requireAuth: true },
      component: chart,
      hidden: true
    },
    {
      path: '/admin_category_list',
      name: 'category',
      meta: { requireAuth: true },
      component: category, hidden: true
    },
    {
      path: '/admin_photos_list',
      name: 'photos',
      meta: { requireAuth: true },
      component: photos,
      hidden: true
    },
    {
      path: '/admin_tags_list',
      name: 'tags',
      meta: { requireAuth: true },
      component: tags,
      hidden: true
    },
    {
      path: '/admin_blogs_list',
      name: 'blogs',
      meta: { requireAuth: true },
      component: blogs,
      hidden: true
    },
    {
      path: '/admin_comments_list',
      name: 'comments',
      meta: { requireAuth: true },
      component: comments,
      hidden: true
    },
    {
      path: '/admin_messages_list',
      name: 'messages',
      meta: { requireAuth: true },
      component: messages,
      hidden: true
    },
    {
      path: '/admin_system_list',
      name: 'system',
      meta: { requireAuth: true },
      component: system,
      hidden: true
    },
    {
      path: '/admin_link_list',
      name: 'link',
      meta: { requireAuth: true },
      component: link,
      hidden: true
    },
    {
      path: '/admin_blog_input',
      name: 'blog',
      meta: { requireAuth: true },
      component: blog,
      hidden: true
    }
  ],
  mode: 'history',
  base: process.env.BASE_URL,
})
