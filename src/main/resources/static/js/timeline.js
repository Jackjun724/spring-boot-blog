let vue = new Vue({
    el: '#app',
    data: {
        /* 页面效果 每个页面都有 */
        beforeScroll: 0,
        pageDown: false,
        top: false,

        /*页面数据*/
        timeLineData: [],
        isLoading: false,
        dateMapping: {},
        pageNum: 1,
        hasMore: true
    },
    methods: {
        /* 页面效果 每个页面都有 */
        scrollChange() {
            if (document.body.scrollTop + document.body.clientHeight === document.body.scrollHeight && this.hasMore) {
                this.loadMore()
            }

            let afterScroll = document.body.scrollTop
            if (afterScroll <= 90) {
                this.pageDown = false
                this.top = false
            } else if (afterScroll !== this.beforeScroll) {
                this.top = true
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
            el.style.opacity = 0
            el.style.marginTop = '20px'
        },
        enter: function (el, done) {
            var delay = 250
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 1, marginTop: '0'},
                    {complete: done}
                )
            }, delay)
        },
        leave: function (el, done) {
            var delay = 250
            setTimeout(function () {
                Velocity(
                    el,
                    {opacity: 0, marginTop: '20px'},
                    {complete: done}
                )
            }, delay)
        },

        /*自动加载下一页*/
        loadMore: function () {
            this.isLoading = true
            let _this = this
            this.pageNum++
            axios.get(`/api/loadTimeline.do?page=${_this.pageNum}`).then(resp => {
                resp.data.data.forEach(e => {
                    _this.insertData(e)
                })
                if (resp.data.page.count < resp.data.page.pageNum * resp.data.page.pageSize) {
                    _this.hasMore=false
                }
                _this.isLoading = false
            }).catch(reason => {
                console.log(reason)
                _this.isLoading = false
            })

        },

        dateFormat: function (nS) {
            return new Date(parseInt(nS)).toLocaleString('zh-CN', {
                hour12: false,
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit'
            })
        },
        openNote: function (id, time) {
            window.location = `/note/${id}/${time}`
        },
        insertData: function (e) {
            let _this = this
            if (_this.dateMapping.hasOwnProperty(e.date)) {
                _this.timeLineData[_this.dateMapping[e.date]].note.push(e)
            } else {
                _this.dateMapping[e.date] = _this.timeLineData.length
                _this.timeLineData.push({
                    id: e.id,
                    date: e.date,
                    note: [
                        e
                    ]
                })
            }
        }
    },
    mounted() {
        window.addEventListener("scroll", this.scrollChange)

        let _this = this
        dataJson.forEach(e => {
            _this.insertData(e)
        })

    }
})