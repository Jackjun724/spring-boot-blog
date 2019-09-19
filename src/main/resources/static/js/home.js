let vue = new Vue({
    el: '#app',
    data: {
        /* 页面效果 每个页面都有 */
        beforeScroll: 0,
        pageDown: false,
        top: false,

        /* 页面数据 */
        name: '首页',
        notes: [],
        total: 0,
        page: 1
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
            el.style.marginTop = '80px'
        },
        enter(el, done) {
            const delay = el.dataset.index * 150;
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 1, marginTop: '60px'},
                    {complete: done}
                )
            }, delay)
        },
        leave(el, done) {
            const delay = el.dataset.index * 16.66667 * 10;
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 0, marginTop: '80px'},
                    {complete: done}
                )
            }, delay)
        },

        dateFormat(nS) {
            if (nS) {
                let str = nS.toString();
                return str.substring(2, str.length - 3)
            }
            return ''
        },
        lenFormat(len) {
            //保留一位小数
            return parseFloat(Math.ceil(len / 100) / 10) + 'k';
        },
        openNote(id) {
            window.location = `/note/${id}`
        },
        pageChange(page) {
            window.location = `/index/${page}`
        }
    },
    mounted() {
        //向下翻隐藏导航
        window.addEventListener("scroll", this.scrollChange);
        this.notes.push(...dataJson['data'])
    },
    created() {
        this.total = dataJson['total'];
        this.page = dataJson['page']
    }
});