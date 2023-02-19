package cn.tomisme.domain.model.response.cloud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StdFolderContentDto {
    private int id;
    private String type;
    private String name;
    private Date date;
    private String mimeType;
    private String hash;
    private long size;
}
