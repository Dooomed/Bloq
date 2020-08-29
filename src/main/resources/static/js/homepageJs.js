
var app = new Vue({
    el: '#app',
    data:{
        message:'Hello wrld'
    }
});

var app2 = new Vue({
    el: '#app2',
    data:{
        message: 'You loaded this on' + new Date().toLocaleString()
     }
});