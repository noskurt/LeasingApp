package leasing.app.meta_data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ModelGetDto {
    private UUID id;
    private String name;
}
