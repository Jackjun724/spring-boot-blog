let vue = new Vue({
    el: '#app',
    data: {
        /* 页面效果 每个页面都有 */
        beforeScroll: 0,
        pageDown: false,
        top: false,

        /* 页面数据 */
        noteList: [],
        parentTagName: ''
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
            el.style.marginTop = '20px'
        },
        enter(el, done) {
            const delay = 250;
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 1, marginTop: '0'},
                    {complete: done}
                )
            }, delay)
        },
        leave(el, done) {
            const delay = 250;
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
        openNote(id) {
            window.location = `/note/${id}`
        },
        dateFormat(nS) {
            if (nS) {
                let str = nS.toString();
                return str.substring(2, str.length - 3)
            }
            return ''
        }
    }
    ,
    mounted() {
        //向下翻隐藏导航
        window.addEventListener("scroll", this.scrollChange);
        this.noteList.push(...dataJson.note);
        this.parentTagName = dataJson.tagName
    }
});