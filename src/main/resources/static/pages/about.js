const About = {
    i18n: {
        messages: {
            'en-US': {
                'hello': 'This is a system for booking air tickets'
            },
            'uk-UA': {
                'hello': 'Це є система бронювання авіа квитків'
            }
        }
    },
    template:
        `
<div class="h-100" style="padding-top: 4rem;">  
    <div class="row">
        <div class="col-8">
            <div v-html="$t('hello')" class="fs-4"></div>
        </div>
        <div class="col-4">
            
        </div>
    </div>
    <router-view :key="$route.path"></router-view>
</div>  
        `
}