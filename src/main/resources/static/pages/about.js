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
<div>    
    <div v-html="$t('hello')"></div>
    <router-view :key="$route.path"></router-view>
</div>  
        `
}