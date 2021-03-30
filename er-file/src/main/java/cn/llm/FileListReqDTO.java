package cn.llm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileListReqDTO implements Serializable {
    private static final long serialVersionUID = -830315084965817371L;
    private List<FileReqBO> fileReqBOList;
    private String zipFileName;
}
