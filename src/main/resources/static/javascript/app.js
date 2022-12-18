const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    linkActiveClass: "active",
    routes: [
        {
            name: 'about',
            path: '/about',
            component: About
        },
        {
            name: 'reserve',
            path: '/reserve',
            component: Reserve
        }
    ]
})
const app = Vue.createApp({
    i18n: {
        messages: {
            'en-US': {
                'menu_reserve': 'Reserve',
                'menu_about': 'About us'
            },
            'uk-UA': {
                'menu_reserve': 'Забронювати',
                'menu_about': 'Про нас'
            }
        }
    },
    data: ()=> {
        return {
            locale: null, updating: false
        }
    },
    watch: {
        'locale' (v,o) {
            localStorage['locale'] = v
            this.$i18n.locale = localStorage['locale']
            $('select.language').selectpicker('val', v)
        }
    },
    mounted: function() {
        this.locale = localStorage['locale'] || window.navigator.language
        $('select.language').selectpicker({
            caretIcon: 'fa fa-angle-down'
        })
    },
})

app.use(router)
app.use(VueI18n.createI18n({
    fallbackLocale: 'en'
}))

setTimeout(function(){
    app.mount('#app')
},0);

window.myapp = app