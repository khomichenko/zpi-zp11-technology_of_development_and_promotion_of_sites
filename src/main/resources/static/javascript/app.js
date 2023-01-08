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
        },
        {
            name: 'my',
            path: '/my',
            component: My
        }
    ]
})
const app = Vue.createApp({
    i18n: {
        messages: {
            'en-US': {
                'menu_reserve': 'Reserve',
                'menu_my': 'My reservations',
                'menu_about': 'About us'
            },
            'uk-UA': {
                'menu_reserve': 'Забронювати',
                'menu_my': 'Мої квитки',
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
    debugger
    var menuButton = document.querySelector('.menu-button');
    var openMenu = function () {
        swiper.slidePrev();
    };
    var swiper = new Swiper('.swiper', {
        slidesPerView: 'auto',
        initialSlide: 1,
        resistanceRatio: 0,
        slideToClickedSlide: true,
        on: {
            slideChangeTransitionStart: function () {
                var slider = this;
                if (slider.activeIndex === 0) {
                    menuButton.classList.add('cross');
                    // required because of slideToClickedSlide
                    menuButton.removeEventListener('click', openMenu, true);
                } else {
                    menuButton.classList.remove('cross');
                }
            },
            slideChangeTransitionEnd: function () {
                var slider = this;
                if (slider.activeIndex === 1) {
                    menuButton.addEventListener('click', openMenu, true);
                }
            },
        },
    });
},0);

window.myapp = app
