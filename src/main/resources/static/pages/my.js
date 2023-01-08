const My = {
    i18n: {
        messages: {
            'en-US': {
                'hello': 'These are your booked flight tickets',
                'grn': 'grn',
                'ticket': 'Ticket',
            },
            'uk-UA': {
                'hello': 'Це є ваші заброньовані авіаквитки',
                'grn': 'грн',
                'ticket': 'Білет'
            }
        }
    },
    data: function() {
        return {
            IP: '',
            flights: []
        }
    },
    methods: {
        getFlights(callback) {
            BackendService.post("/flights/my", { ip: this.IP }, (r) => {
                this.flights = r
                callback && callback()
            })
        }
    },
    computed: {
        moment: () => moment
    },
    created: function() {
        $.get('https://www.cloudflare.com/cdn-cgi/trace', (data)=>{
            data = data.trim().split('\n').reduce((obj, pair)=>{
                pair = pair.split('=')
                return obj[pair[0]] = pair[1], obj
            }, {})
            this.IP = data.ip
            this.getFlights()
        });
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
    <div class="row overflow-auto h-75">
        <div class="col">
            <ul class="list-group pb-5 pt-4">
                <li v-for="(x,index) in flights" class="list-group-item list-group-item-action ">
                    <div class="d-flex justify-content-between align-items-center">                
                        <span>
                            <i class="bi bi-check2-square"></i>
                            {{x.flight.to}}
                            (
                                {{moment(x.event.departure).locale($root.locale).format("LLL")}}
                                -
                                {{moment(x.event.arrival).locale($root.locale).format("LLL")}}
                            )
                            
                        </span>
                        <span class="text-black-50">{{x.flight.fid}}</span>
                    </div>
                    <div>
                        {{x.seat.sid}} - {{x.seat.username}} - {{x.seat.price}} {{$t('grn')}}
                        <span class="float-end">{{$t('ticket')}} <b>{{x.seat.ticket}}</b></span>
                    </div>
                </li>
            </ul>
            </div>
    </div>
    <router-view :key="$route.path"></router-view>
</div>  
        `
}