import Vue from 'vue'
Vue.filter("formatDate", function formatDate(value) {
  let date = new Date(value);
  let year = date.getFullYear();
  let month = date.getMonth() + 1;
  let day = date.getDate();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day;
});

Vue.filter("formatDateTime", function formatDateTime(value) {
  let date = new Date(value);
  let year = date.getFullYear();
  let month = date.getMonth() + 1;
  let day = date.getDate();
  let hours = date.getHours();
  let minutes = date.getMinutes();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
});

Vue.filter("ellipsis", function ellipsis (value) {
  if (!value) return ''
  if (value.length > 30) {
    return value.slice(0,30) + '...'
  }
  return value
})
Vue.filter("ellipsisMax", function ellipsis (value) {
  if (!value) return ''
  if (value.length > 30) {
    return value.slice(0,120) + '...'
  }
  return value
})

