package fei.stuba.socket.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteResult {
    private String idRace;
    private String bib;
    private String lane;
}
