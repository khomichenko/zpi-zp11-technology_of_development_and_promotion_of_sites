package application.controllers.params;

import lombok.Data;

import java.util.List;

@Data
public class ReserveParams {
    @Data public static class ReserveSeatParams {
        private String seatId;
        private String username;
        private String passport;
        private String comment;
        private String ip;
    }
    private String fid;
    private Long eventId;
    private List<ReserveSeatParams> seats;

}
