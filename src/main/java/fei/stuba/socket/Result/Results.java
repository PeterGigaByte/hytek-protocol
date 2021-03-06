package fei.stuba.socket.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Results {
    private String idRace;
    private String ord;
    private String line;
    private String bib;
    private String wind;
    private String time;


}
