let vue = new Vue({
    el: '#app',
    data: {
        /* 页面效果 每个页面都有 */
        beforeScroll: 0,
        pageDown: false,
        top: false,

        /* 页面数据 */
        name: '文章',
        markdownText: '',
        tocHtml: '',
        isChange: false,
        click: 0,
        len: 0,
        note: {}
    },
    methods: {
        /* 页面效果 每个页面都有 */
        scrollChange() {
            let afterScroll = document.body.scrollTop;
            if (afterScroll <= 90) {
                this.pageDown = false;
                this.top = false
            } else if (afterScroll !== this.beforeScroll) {
                this.top = true;
                this.pageDown = afterScroll > this.beforeScroll;
            }
            this.beforeScroll = afterScroll
        },
        toTop() {
            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });
            this.beforeScroll = 0
        },

        /* 页面初始化效果 */
        beforeEnter: function (el) {
            el.style.opacity = 0;
            el.style.marginTop = '20px'
        },
        enter: function (el, done) {
            var delay = 250;
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 1, marginTop: '0'},
                    {complete: done}
                )
            }, delay)
        },
        leave: function (el, done) {
            var delay = 250;
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 0, marginTop: '20px'},
                    {complete: done}
                )
            }, delay)
        },
        randomIcon: function () {
            let random = ['1', '2', ''];
            return random[parseInt(Math.random() * 3)]
        },
        openNote: function (id, time) {
            window.location = `/note/${id}/${time}`
        },
        dateFormat: function (nS) {
            return nS
        },
        lenFormat: function () {
            //保留一位小数
            return parseFloat(Math.ceil(this.len / 100) / 10) + 'k';
        },
        randomColors: function () {
            let colors = ['red', '#3181ff', '#ff2949', '#ffba16', '#5d5dff', '#24c5ff'];
            let count = Math.round(Math.random() * (colors.length - 1));
            return colors[count]
        },
        openTag: function (id) {
            window.location = `/tags/${id}`
        }
    }
    ,
    mounted() {
        //向下翻隐藏导航
        window.addEventListener("scroll", this.scrollChange)
    }
});