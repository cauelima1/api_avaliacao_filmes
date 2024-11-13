package plat.filmes.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepeatedCommentDTO {

    private Long commentId;
    private boolean repeated;


}
