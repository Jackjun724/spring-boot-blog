let vue = new Vue({
    el: '#app',
    data: {
        /* 页面效果 每个页面都有 */
        beforeScroll: 0,
        pageDown: false,
        top: false,

        /* 页面数据 */
        name: '标签',
        tags: []
    },
    methods: {
        /* 页面效果 每个页面都有 */
        scrollChange() {
            let afterScroll = window.scrollY;
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
            el.style.marginLeft = '15px'
        },
        enter: function (el, done) {
            let delay = el.dataset.index * 50;
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 1, marginLeft: '5px'},
                    {complete: done}
                )
            }, delay)
        },
        leave: function (el, done) {
            let delay = el.dataset.index * 50;
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 0, marginLeft: '15px'},
                    {complete: done}
                )
            }, delay)
        },
        randomColors: function () {
            let colors = ['red', '#3181ff', '#ff2949', '#ffcc3b', '#5d5dff', '#5bff0f'];
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
        window.addEventListener("scroll", this.scrollChange);

        this.tags.push(
            ...dataJson
        )
    }
});