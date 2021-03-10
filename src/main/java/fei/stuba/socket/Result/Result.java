package fei.stuba.socket.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    private String idRace;
    private String ord;
    private String lane;
    private String bib;
    private String wind;
    private String time;
}
