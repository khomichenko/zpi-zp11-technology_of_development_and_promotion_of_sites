const Reserve = {
    i18n: {
        messages: {
            'en-US': {
                'hello': 'Airline booking at Zhulyany airport'
            },
            'uk-UA': {
                'hello': 'Бронювання авіаквитків в аеропорту Жуляни'
            }
        }
    },
    data: function() {
        return {
            flights: []
        }
    },
    methods: {
        getFlights () {
            BackendService.get("/flights/",(r)=>{
                this.flights = r
            })
        }
    },
    created: function() {
        this.getFlights()
    },
    template:
        `
<div>    
    <div v-html="$t('hello')" class="pt-1 pb-3"></div>
    <ul class="list-group pb-5">
        <li v-for="(flight,index) in flights" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
            <span>{{flight._to}}</span>
            <span class="text-black-50">{{flight._fid}}</span>
        </li>
    </ul>
    <router-view :key="$route.path"></router-view>
</div>  
        `
}