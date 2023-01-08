const Reserve = {
    i18n: {
        messages: {
            'en-US': {
                'hello': 'Airline booking at Zhulyany airport',
                'chosen': 'Chosen',
                'place': 'Place',
                'username': 'Full name',
                'passport': 'Passport',
                'comment': 'Comment',
                'price': 'price',
                'grn': 'grn',
                'free': 'free',
                'reserve': 'Reserve {count} tickets on {flight} at {departure}',
                'reserved': 'Reserved, thank you',
                'search': 'Search'
            },
            'uk-UA': {
                'hello': 'Бронювання авіаквитків в аеропорту Жуляни',
                'chosen': 'Вибрано',
                'place': 'Місце',
                'username': 'Прізвище, ім\'я, по-батькові',
                'passport': 'Паспорт',
                'comment': 'Коментарій',
                'price': 'Ціна',
                'grn': 'грн',
                'free': 'вільно',
                'reserve': 'Забронювати {count} білети на {flight} на {departure}',
                'reserved': 'Заброньовано, дякуємо',
                'search': 'Пошук'
            }
        }
    },
    data: function() {
        return {
            IP: '',
            flights: [],
            selectedFlights: {},
            selectedFlightEvents: {},
            selectedFlightEventSeats: [], // ['7B']
            inputFlightEventSeats: {} // { '7B':{'username':'Alex','passport':'DB-123456','comment':'test'} }
        }
    },
    watch: {
        //search: function(n,o) {
        //    this.getFlights()
        //}
    },
    computed: {
        moment: () => moment
    },
    methods: {
        getFlights (callback) {
            let data = {
                search: this.search
            }
            BackendService.post("/flights/list", data,(r)=>{
                this.flights = r
                callback && callback()
            })
        },
        toggleFlight(flight) {
            this.selectedFlights[flight.id] = this.selectedFlights[flight.id]==undefined ? flight : undefined

        },
        toggleEvent(event) {
            this.selectedFlightEvents[event.id] = this.selectedFlightEvents[event.id]==undefined ? event : undefined
        },
        isTaken(event,sId) {
            return ((event.seats.filter((s)=>{return s.sid==sId}) || [])[0] || {}).username!=undefined
        },
        onClickOnSeat(event,sId) {
            if (this.isTaken(event,sId)!=true) {
                this.selectedFlightEventSeats[event.id] ||= []
                if (this.selectedFlightEventSeats[event.id].indexOf(sId)==-1) {
                    this.selectedFlightEventSeats[event.id].push(sId)
                } else {
                    this.selectedFlightEventSeats[event.id] = this.selectedFlightEventSeats[event.id].filter((item)=>{ return item !== sId })
                }
            } else {
                // nothing
            }
            this.inputFlightEventSeats[sId] = {}
        },
        isSeatSelected(event,sId) {
            return (this.selectedFlightEventSeats[event.id] || []).indexOf(sId)>-1
        },
        getSeat(event,sId) {
            return (event.seats.filter((x)=>{return x.sid==sId}) || [])[0]
        },
        reserve(flight, event) {
            let seats = this.selectedFlightEventSeats[event.id]
            BackendService.post("/flights/reserve",{
                fid: flight.fid,
                eventId: event.id,
                seats: seats.map((s)=>{
                    return {
                        seatId: s,
                        username: this.inputFlightEventSeats[s].username,
                        passport: this.inputFlightEventSeats[s].passport,
                        comment: this.inputFlightEventSeats[s].comment,
                        ip: this.IP
                    }
                })
            },(r)=>{
                alert(this.$t('reserved'))
                this.getFlights(()=>{
                    this.$router.go(0);
                })
            })
        }
    },
    created: function() {
        $.get('https://www.cloudflare.com/cdn-cgi/trace', (data)=>{
            data = data.trim().split('\n').reduce((obj, pair)=>{
                pair = pair.split('=')
                return obj[pair[0]] = pair[1], obj
            }, {})
            this.IP = data.ip
        });
        this.getFlights()
    },
    template:
        `
<div class="h-100" style="padding-top: 4rem;">    
    <div class="row">
        <div class="col-8">
            <div v-html="$t('hello')" class="fs-4"></div>
        </div>
        <div class="col-4">
            <div class="input-group flex-nowrap py-1 w-100">
              <span class="input-group-text" id="addon-wrapping">{{$t('search')}}</span>
              <input v-model="search" @change="getFlights()" type="text" class="form-control" placeholder="" aria-label="" aria-describedby="addon-wrapping">
            </div>
        </div>
    </div>
    <div class="row overflow-auto h-75">
        <div class="col">
            <ul class="list-group pb-5">
                <li v-for="(flight,index) in flights" class="list-group-item list-group-item-action ">
                    <div @click="(event)=>toggleFlight(flight)" class="reserve-toggle d-flex justify-content-between align-items-center">                
                        <span>
                            <i class="bi bi-check2-square" v-if="selectedFlights[flight.id]!=undefined"></i>
                            <i class="bi " v-if="selectedFlights[flight.id]==undefined"></i>
                            {{flight.to}}
                        </span>
                        <span class="text-black-50">{{flight.fid}}</span>
                    </div>
                    <div v-if="selectedFlights[flight.id]!=undefined" v-for="(event,index) in flight.events" > 
                        <div @click="(e)=>toggleEvent(event)" class="reserve-toggle py-1 ps-3">
                            <span class="bi-airplane"></span>
                            {{moment(event.departure).locale($root.locale).format("LLL")}} 
                            - 
                            {{moment(event.arrival).locale($root.locale).format("LLL")}}
                        </div>
                        <div v-if="selectedFlightEvents[event.id]!=undefined" class="table-responsive">
                            <table class="table" >
                                <tr v-for="ch in ['F','E','D',' ','C','B','A']">
                                    <td v-for="index in 19" :key="index">
                                        <span :class="{
                                                'reserve-event-seat-busy': isTaken(event,''+index+ch)==true,
                                                'reserve-event-seat-free': isTaken(event,''+index+ch)!=true,
                                                'reserve-event-seat-selected':isSeatSelected(event,''+index+ch)
                                              }"
                                              @click="(ev)=>onClickOnSeat(event,''+index+ch)"
                                              :title="(' ' + (getSeat(event,''+index+ch) || {}).price+' '+$t('grn') + ' (' + ((getSeat(event,''+index+ch) || {}).username || $t('free')) + ') ' ).trim()">
                                            <span v-if="ch!=' '"> {{index}} </span>
                                            <span>{{ch}} </span>
                                        </span>                                
                                    </td>
                                </tr>
                                <tr v-if="(selectedFlightEventSeats[event.id] || []).length>0">
                                    <td colspan="19" class="px-3 pt-1">
                                        {{$t('chosen')}}
                                        <div class="row">
                                            <div class="col-1 text-center">№</div>
                                            <div class="col-1 text-center">{{$t('place')}}</div>
                                            <div class="col-3 text-center">{{$t('username')}}</div>
                                            <div class="col-2 text-center">{{$t('passport')}}</div>
                                            <div class="col text-center">{{$t('comment')}}</div>
                                            <div class="col-1 text-center">{{$t('price')}}</div>
                                        </div>
                                        <div class="row" v-for="(seat,i) in selectedFlightEventSeats[event.id]">
                                            <div class="col-1 text-center">{{i+1}}</div>
                                            <div class="col-1 text-center reserve-event-seat-selected" @click="(ev)=>onClickOnSeat(event,''+seat)">{{seat}}</div>
                                            <div class="col-3 text-center">
                                                <input v-model="inputFlightEventSeats[seat].username" class="form-control form-control-sm text-center" type="text" placeholder="" />
                                            </div>
                                            <div class="col-2 text-center">
                                                <input v-model="inputFlightEventSeats[seat].passport" class="form-control form-control-sm text-center" type="text" placeholder="" /> 
                                            </div>
                                            <div class="col text-center">
                                                <input v-model="inputFlightEventSeats[seat].comment" class="form-control form-control-sm text-center" type="text" placeholder="" />
                                            </div>
                                            <div class="col-1 text-center">{{getSeat(event,seat).price}} {{$t('grn')}}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 text-end">
                                                <button type="button" class="btn btn-outline-success" @click="(ev)=>reserve(flight,event)">
                                                    {{$t('reserve',{count:(selectedFlightEventSeats[event.id] || []).length,flight:flight.fid,departure:moment(event.departure).locale($root.locale).format("LLL")})}}
                                                </button> 
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>                    
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <router-view :key="$route.path"></router-view>
</div>  
        `
}